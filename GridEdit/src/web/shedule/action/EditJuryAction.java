package web.shedule.action;

import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.hibernate.Transaction;

import web.shedule.dao.JuryDao;

import com.googlecode.s2hibernate.struts2.plugin.annotations.TransactionTarget;
import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "error", location = "messages.jsp") })
public class EditJuryAction extends ActionSupport {

	private static final long serialVersionUID = -3454448309088641394L;
	private static final Log log = LogFactory.getLog(EditJuryAction.class);

	private JuryDao juryDao = new JuryDao();

	private String oper = "edit";
	private String presidentName;
	private String secretaryName;
	private String examinerName1;
	private String examinerName2;
	private String additionalmemberName;
	private String idju;
	private String name;
	private String title;

	@TransactionTarget
	protected Transaction hTransaction;

	public String execute() throws Exception {
		System.out.println("excute :" + oper);
		log.debug("excute :" + oper);
		log.debug("president:" + presidentName);
		log.debug("secretaryName:" + secretaryName);
		log.debug("examinerName1:" + examinerName1);
		log.debug("examinerName2:" + examinerName2);
		log.debug("additionalmemberName:" + additionalmemberName);

		try {
			if (oper.equalsIgnoreCase("add")) {
				/*
				 * log.debug("Add Customer"); customer = new Customers();
				 * 
				 * int nextid = customersDao.nextCustomerNumber();
				 * log.debug("Id for ne Customer is " + nextid);
				 * customer.setCustomernumber(nextid);
				 * customer.setCustomername(customername);
				 * customer.setCountry(country); customer.setCity(city);
				 * customer.setCreditlimit(creditlimit);
				 * customer.setContactfirstname(contactfirstname);
				 * customer.setContactlastname(contactlastname);
				 * 
				 * if (salesemployee != null) {
				 * customer.setSalesemployee(employeeDao.get(salesemployee
				 * .getEmployeenumber())); }
				 * 
				 * customersDao.save(customer);
				 */
			} else if (oper.equalsIgnoreCase("edit")) {
				log.debug("Edit Jury");

			} else if (oper.equalsIgnoreCase("del")) {
				/*
				 * StringTokenizer ids = new StringTokenizer(id, ","); while
				 * (ids.hasMoreTokens()) { int removeId =
				 * Integer.parseInt(ids.nextToken());
				 * log.debug("Delete Customer " + removeId);
				 * customersDao.delete(removeId); }
				 */
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

	public JuryDao getJuryDao() {
		return juryDao;
	}

	public void setJuryDao(JuryDao juryDao) {
		this.juryDao = juryDao;
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

	public String getIdju() {
		return idju;
	}

	public void setIdju(String idju) {
		this.idju = idju;
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

}
