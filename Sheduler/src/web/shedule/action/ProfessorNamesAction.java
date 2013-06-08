package web.shedule.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

import web.shedule.dao.ProfessorsDao;
import web.shedule.model.Professors;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
public class ProfessorNamesAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4330429721980090828L;
	private List<Professors> profs;
	private ProfessorsDao professorsDao = new ProfessorsDao();
	
	public String execute() {
		profs = professorsDao.getAll();
		return SUCCESS;
	}

	public List<Professors> getProfs() {
		return profs;
	}
}
