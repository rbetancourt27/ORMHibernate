package net.codejava.hibernate;
import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
	private int id;
	private String acc_number;
	private double balance;
	public Account() {}
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAcc_number() {
		return acc_number;
	}
	public void setAcc_number(String acc_number) {
		this.acc_number = acc_number;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return getId() + " - " + getAcc_number() + " - " + getBalance();
	}
}
