package shedule.web.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "professors")
public class Professors {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;

	@Column(name = "name")
	private String name;

	@Column(name = "is_hust")
	private int isHust;

	public Professors() {
	}

	public Professors(int id, String name, int isHust) {
		this.id = id;
		this.name = name;
		this.isHust = isHust;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsHust() {
		return isHust;
	}

	public void setIsHust(int isHust) {
		this.isHust = isHust;
	}

}
