package web.shedule.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import web.shedule.dao.ProfessorsDao;
import web.shedule.model.Professors;

public class ProfessorNamesAction extends ActionSupport {
	private List<String> profNames;
	private ProfessorsDao professorsDao = new ProfessorsDao();

	@Override
	public String execute() {
		profNames = professorsDao.getListProfNames();
		return SUCCESS;
	}

	public List<String> getProfNames() {
		return profNames;
	}
}
