package web.shedule.dao;

public class JuryInfo {
	private String slotDescription;

	private int idju;
	private String roomName;

	private int roomId;

	private int slotId;

	private String name;

	private String title;

	private String supervisorName;

	private int supervisorId;

	private String examinerName1;

	private int examinerId1;

	private String examinerName2;

	private int examinerId2;

	private String presidentName;

	private int presidentId;

	private String secretaryName;

	private int secretaryId;

	private String additionalmemberName;

	private int additionalMemberId;

	private int idSet;

	public JuryInfo() {
	}

	public JuryInfo(int idju, String name, String title, String supervisorName,
			String examinerName1, String examinerName2, String presidentName,
			String secretaryName, String additionalmemberName, int idSet) {

		this.idju = idju;
		this.name = name;
		this.title = title;
		this.supervisorName = supervisorName;
		this.examinerName1 = examinerName1;
		this.examinerName2 = examinerName2;
		this.presidentName = presidentName;
		this.secretaryName = secretaryName;
		this.additionalmemberName = additionalmemberName;
		this.idSet = idSet;
	}

	public int getIdju() {
		return idju;
	}

	public void setIdju(int idju) {
		this.idju = idju;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(String supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getExaminerName1() {
		return examinerName1;
	}

	public void setExaminerName1(String examinerName1) {
		this.examinerName1 = examinerName1;
	}

	public String getExaminerName2() {
		return examinerName2;
	}

	public void setExaminerName2(String examinerName2) {
		this.examinerName2 = examinerName2;
	}

	public String getPresidentName() {
		return presidentName;
	}

	public void setPresidentName(String presidentName) {
		this.presidentName = presidentName;
	}

	public String getSecretaryName() {
		return secretaryName;
	}

	public void setSecretaryName(String secretaryName) {
		this.secretaryName = secretaryName;
	}

	public String getAdditionalmemberName() {
		return additionalmemberName;
	}

	public void setAdditionalmemberName(String additionalmemberName) {
		this.additionalmemberName = additionalmemberName;
	}

	public boolean juryProfessor(int pId) {
		if (pId == presidentId || pId == secretaryId
				|| pId == additionalMemberId) {
			return true;
		}
		if (pId == examinerId1) {
			return true;
		}
		if (pId == examinerId2) {
			return true;
		}
		return false;
	}

	public void setExaminerId1(int examinerId1) {
		this.examinerId1 = examinerId1;
	}

	public void setExaminerId2(int examinerId2) {
		this.examinerId2 = examinerId2;
	}

	public void setAdditionalMemberId(int additionalMemberId) {
		this.additionalMemberId = additionalMemberId;
	}

	public void setPresidentId(int presidentId) {
		this.presidentId = presidentId;
	}

	public void setSecretaryId(int secretaryId) {
		this.secretaryId = secretaryId;
	}

	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	public int getSecretaryId() {
		return secretaryId;
	}

	public int getAdditionalMemberId() {
		return additionalMemberId;
	}

	public int getExaminerId1() {
		return examinerId1;
	}

	public int getExaminerId2() {
		return examinerId2;
	}

	public int getPresidentId() {
		return presidentId;
	}

	public int getSupervisorId() {
		return supervisorId;
	}

	public void setSlotId(int slotId) {
		this.slotId = slotId;
	}

	public int getSlotId() {
		return slotId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setSlotDescription(String slotDescription) {
		this.slotDescription = slotDescription;
	}

	public String getSlotDescription() {
		return slotDescription;
	}

	public void setIdSet(int idSet) {
		this.idSet = idSet;
	}

	public int getIdSet() {
		return idSet;
	}
}
