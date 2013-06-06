package web.shedule.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import web.shedule.dao.SlotDao;
import web.shedule.model.Slot;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport implements SessionAware {
	private SlotDao slotDao = new SlotDao();
	private List<Slot> listSlots;

	public String init() {
		listSlots = slotDao.getAll();
		return SUCCESS;
	}


	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub

	}

	public void setListSlots(List<Slot> listSlots) {
		this.listSlots = listSlots;
	}

	public List<Slot> getListSlots() {
		return listSlots;
	}

}
