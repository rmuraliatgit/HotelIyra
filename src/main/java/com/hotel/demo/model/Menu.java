package com.hotel.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ManyToAny;

import net.bytebuddy.dynamic.loading.ClassReloadingStrategy.Strategy;

@Entity
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int menuId;
	String dishName;
	String description;
	int price;
	String imageType;
	String imageName;
	
	
	public Menu() {
		super();
	}

	public Menu(Menu menu) {
		super();
		this.dishName = menu.dishName;
		this.description = menu.description;
		this.price = menu.price;
		this.imageType = menu.imageType;
		this.imageName = menu.imageName;
	}

	public Menu(String dishName, String description, int price, String imageType, String imageName) {
		super();
		this.dishName = dishName;
		this.description = description;
		this.price = price;
		this.imageType = imageType;
		this.imageName = imageName;
	}

	public int getMenuId() {
		return menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public String getDishName() {
		return dishName;
	}

	public void setDishName(String dishName) {
		this.dishName = dishName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", dishName=" + dishName + ", description=" + description + ", price=" + price
				+ ", image=" + imageType + ", imageName=" + imageName + "]";
	}

}
