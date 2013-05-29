package web.shedule.action;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	private List<String> listString;
	public String init() {
		listString=new LinkedList<String>();
		listString.add("1");
		listString.add("2");
		listString.add("3");
		return SUCCESS;
	}
	public void setListString(List<String> listString) {
		this.listString = listString;
	}
	public List<String> getListString() {
		return listString;
	}

}
