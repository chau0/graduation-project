package web.shedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "slots")
public class Slot {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "des")
	private String des;

	public Slot() {
	}

	public Slot(int id, String des) {
		super();
		this.id = id;
		this.des = des;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}
	

}
