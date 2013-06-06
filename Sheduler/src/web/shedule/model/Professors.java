package web.shedule.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "professors", schema = "shedulerdefense")
public class Professors implements java.io.Serializable {

	private static final long serialVersionUID = 6222062494710896823L;
	private int id;
	private String name;
	private int is_hust;

	public Professors() {
		// TODO Auto-generated constructor stub
	}

	public Professors(Integer id) {
		this.id = id;
	}

	public Professors(Integer id, String Aname) {
		this.id = id;
		this.name = name;

	}

	public Professors(Integer id, String name, int is_hust) {
		this.id = id;
		this.name = name;
		this.is_hust = is_hust;
	}

	@Id()
	@Column(name = "id", unique = true)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "name", length = 200)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
