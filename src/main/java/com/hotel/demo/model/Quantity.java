package com.hotel.demo.model;

import java.util.concurrent.atomic.AtomicInteger;

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
	int cartId;

	public Quantity() {

	}

	public Quantity(int menuId, int quantity, int cartId) {
		super();
		this.menuId = menuId;
		this.quantity = quantity;
		this.cartId = cartId;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
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

}
