package com.hotel.demo.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int cartId;	
	@OneToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name="user",referencedColumnName = "id")
	User user;
	@ManyToMany
	@JoinColumn(name = "menu",referencedColumnName = "cartId")
	List<Menu> menu;
	
 
	public Cart() {
		super();
	}
 
	public Cart(User user) {
		super();
		this.user = user;
	}

	public Cart(User user, List<Menu> menu) {
		super();
				
		this.user = user;
		this.menu = menu;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Menu> getMenu() {
		return menu;
	}

	public void setMenu(List<Menu> menuList) {
		this.menu = menuList;
	}

	public void addMenu(Menu menu) {
		this.menu.add(menu);
	}

	@Override
	public String toString() {
		return "Cart [ cartId=" + cartId + ", user=" + user + ", menu=" + menu + "]";
	}

}
