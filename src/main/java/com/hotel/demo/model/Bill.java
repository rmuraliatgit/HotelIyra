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
	double unitCost;
	double amount;
	double discount;
	int quantity;
	double totalAmount; 
	String date; 
	String time;
	@OneToMany(cascade = CascadeType.ALL)
	List<Menu> menu;
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="UserList" , referencedColumnName="id")
	User user;

	public Bill() {
		super();
	}

	public Bill(double unitCost, double amount, double discount, int quantity, double totalAmount, String date,
			String time, List<Menu> menu, User user) {
		super();
		this.unitCost = unitCost;
		this.amount = amount;
		this.discount = discount;
		this.quantity = quantity;
		this.totalAmount = totalAmount;
		this.date = date;
		this.time = time;
		this.menu = menu;
		this.user = user;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public double getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Bill [billId=" + billId + ", unitCost=" + unitCost + ", amount=" + amount + ", discount=" + discount
				+ ", quantity=" + quantity + ", totalAmount=" + totalAmount + ", date=" + date + ", time=" + time
				+ ", menu=" + menu + ", user=" + user + "]";
	}

}
