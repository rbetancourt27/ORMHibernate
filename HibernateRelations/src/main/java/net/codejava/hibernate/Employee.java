package net.codejava.hibernate;
import javax.persistence.*;

@Entity
@Table(name="employee")
public class Employee {
	private int id;
	private String nifnie;
	private String email;
	private String first_name;
	private String last_name;
	private Account account;
	private Department department;
	public Employee() {}
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNifnie() {
		return nifnie;
	}
	public void setNifnie(String nifnie) {
		this.nifnie = nifnie;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	@OneToOne
	@JoinColumn(name="account_id")
	public Account getAcount() {
		return account;
	}
	public void setAcount(Account acount) {
		this.account = acount;
	}
	@ManyToOne 
	@JoinColumn(name="department_id")
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return getNifnie() + " - " + getFirst_name() + " , " + getLast_name() + " - " +  getAcount().getAcc_number() +" - " + getDepartment().getName();
	}
}
