package web.shedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jury")
public class Jury {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int idju;
	
	@Column(name = "set_id")
	private int idset;
	
	@Column(name = "student_id")
	private int idsv;
	
	@Column(name = "supervisor")
	private int supervisor;

	@Column(name = "examiner1")
	private int examiner1;
	
	@Column(name = "examiner2")
	private int examiner2;
	
	@Column(name = "president")
	private int president;
	
	@Column(name = "secretary")
	private int secretary;
	
	@Column(name = "additionalmember")
	private int additionalmember;
	
	@Column(name = "slots")
	private int slots;
	
	@Column(name = "rooms")
	private int rooms;
	
	public Jury(){}

	public Jury(int idju, int idset, int idsv, int supervisor,
			int examiner1, int examiner2, int president, int secretary,
			int additionalmember, int slots, int rooms) {
		this.idju = idju;
		this.idset = idset;
		this.idsv = idsv;
		this.supervisor = supervisor;
		this.examiner1 = examiner1;
		this.examiner2 = examiner2;
		this.president = president;
		this.secretary = secretary;
		this.additionalmember = additionalmember;
		this.slots = slots;
		this.rooms = rooms;
	}

	public int getIdju() {
		return idju;
	}

	public void setIdju(int idju) {
		this.idju = idju;
	}

	public int getIdset() {
		return idset;
	}

	public void setIdset(int idset) {
		this.idset = idset;
	}

	public int getIdsv() {
		return idsv;
	}

	public void setIdsv(int idsv) {
		this.idsv = idsv;
	}


	public int getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(int supervisor) {
		this.supervisor = supervisor;
	}

	public int getExaminer1() {
		return examiner1;
	}

	public void setExaminer1(int examiner1) {
		this.examiner1 = examiner1;
	}

	public int getExaminer2() {
		return examiner2;
	}

	public void setExaminer2(int examiner2) {
		this.examiner2 = examiner2;
	}

	public int getPresident() {
		return president;
	}

	public void setPresident(int president) {
		this.president = president;
	}

	public int getSecretary() {
		return secretary;
	}

	public void setSecretary(int secretary) {
		this.secretary = secretary;
	}

	public int getAdditionalmember() {
		return additionalmember;
	}

	public void setAdditionalmember(int additionalmember) {
		this.additionalmember = additionalmember;
	}

	public int getSlots() {
		return slots;
	}

	public void setSlots(int slots) {
		this.slots = slots;
	}

	public int getRooms() {
		return rooms;
	}

	public void setRooms(int rooms) {
		this.rooms = rooms;
	}
	
	

}
