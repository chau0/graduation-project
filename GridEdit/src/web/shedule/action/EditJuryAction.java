package web.shedule.action;

import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transaction.JRun4TransactionManagerLookup;

import web.shedule.dao.JuryDao;
import web.shedule.dao.StudentDao;
import web.shedule.model.Jury;
import web.shedule.model.Students;

import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "error", location = "messages.jsp") })
public class EditJuryAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = -3454448309088641394L;
	private static final Log log = LogFactory.getLog(EditJuryAction.class);

	private JuryDao juryDao = new JuryDao();
	private StudentDao studentDao = new StudentDao();

	private String oper = "edit";
	private String presidentName;
	private String secretaryName;
	private String examinerName1;
	private String examinerName2;
	private String additionalmemberName;
	private String id;
	private String name;
	private String title;
	private Map<String, Object> session;

	@TransactionTarget
	protected Transaction hTransaction;

	public String execute() throws Exception {
		System.out.println("excute========================= :" + oper);
		log.debug("excute :" + oper);
		int idSet = (Integer) this.session.get("set");
		System.out.println("id set:" + idSet);
		System.out.println("name:" + name);
		System.out.println("title:" + title);
		System.out.println("id:" + id);
		System.out.println("president:" + presidentName);
		System.out.println("secretaryName:" + secretaryName);
		System.out.println("examinerName1:" + examinerName1);
		System.out.println("examinerName2:" + examinerName2);
		System.out.println("additionalmemberName:" + additionalmemberName);
		try {
			if (oper.equalsIgnoreCase("add")) {
				int nextStudentId = studentDao.getNextId();
				Students students = new Students(nextStudentId, name, title);
				studentDao.save(students);
				
				Jury jury = new Jury();
				jury.setAdditionalmember(Integer.parseInt(additionalmemberName));
				jury.setExaminer1(Integer.parseInt(examinerName1));
				jury.setExaminer2(Integer.parseInt(examinerName2));
				int nextJuryId=juryDao.getNextId();
				System.out.println("nextJuryId:" + nextJuryId);
				jury.setId(nextJuryId);
				jury.setSecretary(Integer.parseInt(secretaryName));
				jury.setIdsv(students.getId());
				jury.setPresident(Integer.parseInt(presidentName));
				jury.setIdset(idSet);
				jury.setSupervisor(1);
				juryDao.save(jury);
			} else if (oper.equalsIgnoreCase("edit")) {
				log.debug("Edit Jury");
				Jury jury = juryDao.get(Integer.parseInt(id));
				Students students=studentDao.get(jury.getIdsv());
				students.setName(name);
				studentDao.update(students);
				jury.setAdditionalmember(Integer.parseInt(additionalmemberName));
				jury.setExaminer1(Integer.parseInt(examinerName1));
				jury.setExaminer2(Integer.parseInt(examinerName2));
				jury.setPresident(Integer.parseInt(presidentName));
				jury.setSecretary(Integer.parseInt(secretaryName));
				juryDao.update(jury);
			} else if (oper.equalsIgnoreCase("del")) {
				juryDao.delete(Integer.parseInt(id));
			}

			// Commit changes
			hTransaction.commit();
		} catch (Exception e) {
			hTransaction.rollback();
			addActionError("ERROR : " + e.getLocalizedMessage());
			addActionError("Is Database in read/write modus?");
			e.printStackTrace();
			return "error";

		}
		return NONE;
	}

	public void setOper(String oper) {
		this.oper = oper;
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

	public String getAdditionalmemberName() {
		return additionalmemberName;
	}

	public void setAdditionalmemberName(String additionalmemberName) {
		this.additionalmemberName = additionalmemberName;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}

}
