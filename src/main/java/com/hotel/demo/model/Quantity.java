package com.hotel.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Quantity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int quantId;
	int menuId;
	int quantity;
	@ManyToOne
	Cart cart;

	public Quantity() {

	}

	public Quantity(int menuId, int quantity, Cart cart) {
		super();
		this.menuId = menuId;
		this.quantity = quantity;
		this.cart = cart;
	}

	public int getQuantId() {
		return quantId;
	}

	public void setQuantId(int quantId) {
		this.quantId = quantId;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}
	

}
