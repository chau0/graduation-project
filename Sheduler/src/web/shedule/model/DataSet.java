package web.shedule.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "dataset", schema = "shedulerdefense")
public class DataSet {
    private int id;
    private Date date;
    private String title;
    
	public DataSet() {
		
	}

	public DataSet(int id, Date date, String title) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
	}
	@Id()
	@Column(name = "id", unique = true)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "title", length = 2000)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}


	
}
