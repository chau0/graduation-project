package web.shedule.algorithm;

import java.util.List;


import web.shedule.dao.JuryInfo;
import web.shedule.model.Professors;
import web.shedule.model.Room;
import web.shedule.util.Debug;

public class NewSearch {
	/**
	 * @param args
	 */
	// private Data D;
	// List<Students> students;
	int nbStudents;
	VarInt[] var_slots;
	boolean[][] conflict;
	int minSlot;
	int maxSlot;
	private List<JuryInfo> listJuryInfos;
	private List<Professors> listProfessors;
	private int nbSlots;
	private int nbRooms;

	public NewSearch(List<JuryInfo> listJuryInfos,
			List<Professors> listProfessors, int nbSlots, int nbRooms) {
		this.nbRooms = nbRooms;
		this.nbSlots = nbSlots;
		this.listProfessors = listProfessors;
		this.listJuryInfos = listJuryInfos;
		nbStudents = listJuryInfos.size();
		var_slots = new VarInt[nbStudents];
		conflict = new boolean[nbStudents][nbStudents];
		int[] slots = new int[nbSlots];

		for (int i = 0; i < nbStudents; i++) {
			for (int j = 0; j < nbStudents; j++) {
				JuryInfo si = listJuryInfos.get(i);
				JuryInfo sj = listJuryInfos.get(j);
				if (i != j) {
					conflict[i][j] = conflictStudents(si, sj);
				} else {
					conflict[i][j] = false;
				}
			}
		}

		minSlot = 10000;
		maxSlot = -1;
		for (int i = 0; i < nbSlots; i++) {
			slots[i] = i;
		}
		for (int i = 0; i < nbSlots; i++) {
			if (minSlot > slots[i]) {
				minSlot = slots[i];
			}
			if (maxSlot < slots[i]) {
				maxSlot = slots[i];
			}
		}

		// maxSlot = 10;
		for (int i = 0; i < nbStudents; i++) {
			var_slots[i] = new VarInt(i, minSlot, maxSlot);
			// var_slots[i].setValue(minSlot);
		}

	}

	public boolean conflictStudents(JuryInfo si, JuryInfo sj) {
		if (si.juryProfessor(sj.getPresidentId())) {
			return true;
		}
		if (si.juryProfessor(sj.getSecretaryId())) {
			return true;
		}
		if (si.juryProfessor(sj.getAdditionalMemberId())) {
			return true;
		}
		if (si.juryProfessor(sj.getExaminerId1())) {
			return true;
		}
		if (si.juryProfessor(sj.getExaminerId2())) {
			return true;
		}

		return false;
	}

	private void assignTimeSlot(ConstraintSystem CS, int maxIter) {
		for (int i = 0; i < nbStudents - 1; i++) {
			for (int j = i + 1; j < nbStudents; j++) {
				if (conflict[i][j]) {
					VarInt vari = var_slots[i];
					VarInt varj = var_slots[j];
					CS.post(new NotEqual(vari, varj));
				}
			}
		}
		AtMost am = new AtMost(var_slots,nbRooms);
		CS.post(am);
		CS.close();

		for (int i = 0; i < nbStudents; i++) {
			var_slots[i].setValue(minSlot);
		}

		System.out.println("violations = " + CS.violations());

		int[][] tabu = new int[nbStudents][maxSlot + 1];

		for (int i = 0; i < nbStudents; i++) {
			for (int sl = minSlot; sl <= maxSlot; sl++) {
				tabu[i][sl] = -1;
			}
		}

		int tbl = 20;

		int it = 1;
		int bestV = CS.violations();
		int oldV = CS.violations();

		while (it < maxIter && bestV > 0) {
			int minDelta = 1000000;
			int sel_i = -1;
			int sel_sl = -1;
			for (int i = 0; i < nbStudents; i++) {
				for (int sl = minSlot; sl <= maxSlot; sl++) {
					int d = CS.getAssignDelta(var_slots[i], sl);
					if (CS.violations() + d < bestV || tabu[i][sl] < it) {
						if (d < minDelta) {
							minDelta = d;
							sel_i = i;
							sel_sl = sl;
						}
					}
				}
			}

			System.out.println("sel_i = " + sel_i + " sel_sl = " + sel_sl);

			CS.propagate(var_slots[sel_i], sel_sl);
			var_slots[sel_i].setValue(sel_sl);

			tabu[sel_i][sel_sl] = it + tbl;

			if (oldV + minDelta != CS.violations()) {
				System.out.println("Error, oldV = " + oldV + " delta = "
						+ minDelta + " violations = " + CS.violations());
				return;
			}
			if (!CS.verify()) {
				System.out.println("NOT verify");
				return;
			}
			oldV = CS.violations();
			if (CS.violations() < bestV) {
				bestV = CS.violations();
			}
			System.out.println("Step " + it + " : Assign var_slots[" + sel_i
					+ "] to " + sel_sl + " violations = " + CS.violations()
					+ " best = " + bestV);
			it++;
		}

		System.out.println("NewSearch::assignSlot, AtMost = " + am.violations()
				+ " CS = " + CS.violations());
	}

	private void assignContinuousSlot(ConstraintSystem csNotEqual, int maxIter) {
		ConstraintSystem csContinuous = new ConstraintSystem();
		Continuous continuous = new Continuous(listJuryInfos, listProfessors,
				var_slots);
		csContinuous.post(continuous);
		csContinuous.close();
		System.out.println("violations = " + csContinuous.violations());

		int[][] tabu = new int[nbStudents][maxSlot + 1];

		for (int i = 0; i < nbStudents; i++) {
			for (int sl = minSlot; sl <= maxSlot; sl++) {
				tabu[i][sl] = -1;
			}
		}

		int tbl = 20;

		int it = 1;
		int bestV = csContinuous.violations();
		int oldV = csContinuous.violations();

		while (it < maxIter && bestV > 0) {

			int minDelta = 1000000;
			int sel_i = -1;
			int sel_sl = -1;

			for (int i = 0; i < nbStudents; i++) {
				for (int sl = minSlot; sl <= maxSlot; sl++) {
					if (csNotEqual.getAssignDelta(var_slots[i], sl) == 0) {
						int d = csContinuous.getAssignDelta(var_slots[i], sl);
						if (csContinuous.violations() + d < bestV
								|| tabu[i][sl] < it) {
							if (d < minDelta) {
								minDelta = d;
								sel_i = i;
								sel_sl = sl;
							}
						}
					}

				}
			}
			int d = csNotEqual.getAssignDelta(var_slots[sel_i], sel_sl);
			System.out.println("AtMost::getAssignDelta(x[" + sel_i + "],"
					+ sel_sl + ") = " + d);
			System.out.println("sel_i = " + sel_i + " sel_sl = " + sel_sl);
			csContinuous.propagate(var_slots[sel_i], sel_sl);
			csNotEqual.propagate(var_slots[sel_i], sel_sl);
			var_slots[sel_i].setValue(sel_sl);

			tabu[sel_i][sel_sl] = it + tbl;

			if (oldV + minDelta != csContinuous.violations()) {
				System.out.println("Error, oldV = " + oldV + " delta = "
						+ minDelta + " violations = "
						+ csContinuous.violations());
				return;
			}

			if (!csNotEqual.verify()) {
				System.out.println("CS not verified");
				return;
			}
			if (!csContinuous.verify()) {
				System.out.println("NOT verify");
				return;
			}
			oldV = csContinuous.violations();
			if (csContinuous.violations() < bestV) {
				bestV = csContinuous.violations();
			}
			System.out.println("Step " + it + " : Assign var_slots[" + sel_i
					+ "] to " + sel_sl + " violations = "
					+ csContinuous.violations() + " best = " + bestV);
			if (it == 999) {
				continuous.printResult();
			}
			it++;
		}
		AtMost am = (AtMost) csNotEqual.getConstraint(csNotEqual.size() - 1);
		am.print();
	}

	public void localsearch(int maxIter) {
		ConstraintSystem CS = new ConstraintSystem();
		assignTimeSlot(CS, maxIter);
		Debug.d("assign continuous");
		assignContinuousSlot(CS, maxIter);
		for (int i = 0; i < listJuryInfos.size(); i++) {
			JuryInfo info = listJuryInfos.get(i);
			int slotId = var_slots[i].getValue();
			info.setSlotId(slotId);
		}

		AssignRooms ar = new AssignRooms();
		ar.assignRooms(listJuryInfos, nbRooms);
	}
}
