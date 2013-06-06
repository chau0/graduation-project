package web.shedule.action;

import java.util.List;

import web.shedule.dao.SlotDao;
import web.shedule.model.Slot;

import com.opensymphony.xwork2.ActionSupport;

public class SlotDesAction extends ActionSupport {
	private static final long serialVersionUID = -7182635558018238651L;
	private List<Slot> slots;
	private SlotDao slotDao = new SlotDao();

	@Override
	public String execute() {
		slots = slotDao.getAll();
		return SUCCESS;
	}

	public List<Slot> getSlots() {
		return slots;
	}
}
