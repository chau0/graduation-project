/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package web.shedule.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import web.shedule.algorithm.NewSearch;
import web.shedule.dao.JuryDao;
import web.shedule.dao.JuryInfo;
import web.shedule.dao.ProfessorsDao;
import web.shedule.dao.RoomDao;
import web.shedule.dao.SlotDao;
import web.shedule.dao.StudentDao;
import web.shedule.model.Jury;
import web.shedule.model.Professors;
import web.shedule.model.Room;
import web.shedule.model.Slot;
import web.shedule.model.Students;
import web.shedule.util.Debug;

import com.opensymphony.xwork2.ActionSupport;

@Result(name = "success", type = "json")
public class TestDataProvider extends ActionSupport implements SessionAware {
	private static final long serialVersionUID = 5078264277068533593L;
	private static final Log log = LogFactory.getLog(TestDataProvider.class);
	private Map<String, Object> session;
	private List<Professors> listProfessors;
	private ProfessorsDao professorsDao = new ProfessorsDao();
	private List<ProfessorShedule> listProfessorShedules;
	// Your result List
	private List<JuryInfo> gridModel;
	// All Records
	private Integer records = 0;

	private JuryDao juryDao = new JuryDao();
	private StudentDao studentDao = new StudentDao();
	private SlotDao slotDao = new SlotDao();
	private RoomDao roomDao = new RoomDao();
	List<String> listProfessorNames;

	@SuppressWarnings("unchecked")
	public String execute() {
		listProfessors = professorsDao.getAll();
		DetachedCriteria criteria = DetachedCriteria.forClass(Professors.class);
		criteria.setProjection(null);
		criteria.setResultTransformer(Criteria.ROOT_ENTITY);
		Object oIdSet = (Integer) this.session.get(Constants.SET);
		int idSet = (oIdSet == null ? 13 : (Integer) oIdSet);
		Debug.d("id set :" + idSet);
		List<Jury> listJuries = juryDao.getListJuryByIdSet(idSet);
		this.session.put("set", idSet);
		List<Students> listStudents = studentDao.getAll();
		gridModel = new LinkedList<JuryInfo>();
		listProfessorNames = new LinkedList<String>();
		
		for (Professors professors : listProfessors) {
			listProfessorNames.add(professors.getName());
		}
		
		for (Jury jury : listJuries) {
			Students student = findStudent(jury.getIdsv(), listStudents);
			Professors supervisor = findProfessor(jury.getSupervisor(),
					listProfessors);
			Professors examiner1 = findProfessor(jury.getExaminer1(),
					listProfessors);
			Professors examiner2 = findProfessor(jury.getExaminer2(),
					listProfessors);
			Professors president = findProfessor(jury.getPresident(),
					listProfessors);
			Professors secretary = findProfessor(jury.getSecretary(),
					listProfessors);
			Professors additionalMember = findProfessor(
					jury.getAdditionalmember(), listProfessors);

			if (supervisor != null && examiner1 != null && examiner2 != null
					&& president != null && secretary != null
					&& additionalMember != null) {

				JuryInfo juryInfo = new JuryInfo(jury.getId(),
						student.getName(), student.getTitle(),
						supervisor.getName(), examiner1.getName(),
						examiner2.getName(), president.getName(),
						secretary.getName(), additionalMember.getName(),
						jury.getIdset());
				juryInfo.setSupervisorId(supervisor.getId());
				juryInfo.setAdditionalMemberId(additionalMember.getId());
				juryInfo.setPresidentId(president.getId());
				juryInfo.setSecretaryId(secretary.getId());
				juryInfo.setExaminerId1(examiner1.getId());
				juryInfo.setExaminerId2(examiner2.getId());
				gridModel.add(juryInfo);
			}
		}
		List<Room> listRooms = roomDao.getAll();
		List<Slot> listSlots = slotDao.getAll();
		NewSearch search = new NewSearch(gridModel, listProfessors,
				listSlots.size(), listRooms.size());
		search.localsearch(1000);

		for (JuryInfo juryInfo : gridModel) {
			Room room = listRooms.get(juryInfo.getRoomId());
			Slot slot = listSlots.get(juryInfo.getSlotId());
			juryInfo.setRoomName(room.getName());
			juryInfo.setSlotDescription(slot.getDes());
		}

		Map<Integer, ProfessorShedule> professorSheduleMap = new HashMap<Integer, ProfessorShedule>();
		for (int l = gridModel.size(), i = 0; i < l; i++) {
			JuryInfo juryInfo = gridModel.get(i);
			String roomName = juryInfo.getRoomName();
			int slotId = juryInfo.getSlotId();
			// examiner1
			String juryExaminerName1 = juryInfo.getExaminerName1();
			int juryExaminerId1 = juryInfo.getExaminerId1();
			updateProfessorSheduleMap(juryExaminerId1, juryExaminerName1,
					roomName, slotId, professorSheduleMap);
			// examiner2
			String juryExaminerName2 = juryInfo.getExaminerName2();
			int juryExaminerId2 = juryInfo.getExaminerId2();
			updateProfessorSheduleMap(juryExaminerId2, juryExaminerName2,
					roomName, slotId, professorSheduleMap);
			// president
			String presidentName = juryInfo.getPresidentName();
			int presidentId = juryInfo.getPresidentId();
			updateProfessorSheduleMap(presidentId, presidentName, roomName,
					slotId, professorSheduleMap);
			// secretary
			String secretaryName = juryInfo.getSecretaryName();
			int secretaryId = juryInfo.getSecretaryId();
			updateProfessorSheduleMap(secretaryId, secretaryName, roomName,
					slotId, professorSheduleMap);
			// additionalMem
			String additionalMemName = juryInfo.getAdditionalmemberName();
			int additionalMemId = juryInfo.getAdditionalMemberId();
			updateProfessorSheduleMap(additionalMemId, additionalMemName,
					roomName, slotId, professorSheduleMap);
		}

		listProfessorShedules = new LinkedList<ProfessorShedule>();
		Iterator it = professorSheduleMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			ProfessorShedule pFound = (ProfessorShedule) entry.getValue();
			pFound.mapRoomToList(listSlots.size());
			listProfessorShedules.add(pFound);
			it.remove(); //
		}
		return SUCCESS;
	}

	private void updateProfessorSheduleMap(int proId, String proName,
			String roomName, int slotId, Map<Integer, ProfessorShedule> map) {
		ProfessorShedule pFound = map.get(proId);
		if (pFound == null) {
			pFound = new ProfessorShedule();
			pFound.setId(proId);
			pFound.setName(proName);
			map.put(proId, pFound);
		}
		pFound.mapRoom(slotId, roomName);
	}

	public String getJSON() {
		return execute();
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public void setListProfessors(List<Professors> listProfessors) {
		this.listProfessors = listProfessors;
	}

	public List<Professors> getListProfessors() {
		return listProfessors;
	}

	public List<ProfessorShedule> getListProfessorShedules() {
		return listProfessorShedules;
	}

	public void setListProfessorShedules(
			List<ProfessorShedule> listProfessorShedules) {
		this.listProfessorShedules = listProfessorShedules;
	}

	private Professors findProfessor(int proId, List<Professors> listProfessors) {
		for (Professors p : listProfessors) {
			if (p.getId() == proId) {
				return p;
			}
		}
		return null;
	}

	private Students findStudent(int idstu, List<Students> listStudent) {
		for (Students p : listStudent) {
			if (p.getId() == idstu) {
				return p;
			}
		}
		return null;
	}

	public Integer getRecords() {
		return records;
	}

	/**
	 * @return an collection that contains the actual data
	 */
	public List<JuryInfo> getGridModel() {
		return gridModel;
	}

	/**
	 * @param gridModel
	 *            an collection that contains the actual data
	 */
	public void setGridModel(List<JuryInfo> gridModel) {
		this.gridModel = gridModel;
	}

	public void setListProfessorNames(List<String> listProfessorNames) {
		this.listProfessorNames = listProfessorNames;
	}

	public List<String> getListProfessorNames() {
		return listProfessorNames;
	}
}
