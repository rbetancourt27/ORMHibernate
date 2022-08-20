package net.codejava.hibernate;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name="department")
public class Department {
	private int id;
	private String name;
	private Set<Employee> empleats;
	public Department() {}
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
	public Set<Employee> getEmpleats() {
		return empleats;
	}
	public void setEmpleats(Set<Employee> empleats) {
		this.empleats = empleats;
	}
	@Override
	public String toString() {
		return getId() + " - " + getName() + " - " + getEmpleats().toString();
	}
}
