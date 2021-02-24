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

	double totalAmount;
	String date;
	String time;
	String userName;
	@OneToOne
	Cart cart;
	
	public Bill() {
		super();
	}

	public Bill(double totalAmount, String date, String time,String userName,int quantity, Cart cart
			) {
		super();
		
		this.totalAmount = totalAmount;
		this.date = date;
		this.time = time;
		this.userName = userName;
		this.cart = cart;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
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
	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId  +  ", totalAmount="
				+ totalAmount + ", date=" + date + ", time=" + time + "]";
	}

}
