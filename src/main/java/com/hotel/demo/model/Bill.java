package com.hotel.demo.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Bill {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int billId;
	double amount;
	double discount;
	double totalAmount;
	String date;
	String time;
//	@OneToMany(cascade = CascadeType.ALL)
//	List<Menu> menu;
	@OneToOne
	Cart cart;
//	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
//	@JoinColumn(name = "UserList", referencedColumnName = "id")
//	User user;
	
	public Bill() {
		super();
	}

	public Bill(double amount, double discount, double totalAmount, String date, String time, Cart cart
			) {
		super();
		this.amount = amount;
		this.discount = discount;
		this.totalAmount = totalAmount;
		this.date = date;
		this.time = time;
		this.cart = cart;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	

	

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", amount=" + amount + ", discount=" + discount + ", totalAmount="
				+ totalAmount + ", date=" + date + ", time=" + time + "]";
	}

}
