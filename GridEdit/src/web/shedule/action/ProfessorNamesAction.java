package web.shedule.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import web.shedule.dao.ProfessorsDao;
import web.shedule.model.Professors;

public class ProfessorNamesAction extends ActionSupport {
	private List<Professors> profs;
	private ProfessorsDao professorsDao = new ProfessorsDao();

	@Override
	public String execute() {
		profs = professorsDao.getAll();
		return SUCCESS;
	}

	public List<Professors> getProfs() {
		return profs;
	}
}
