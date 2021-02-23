package com.hotel.demo.service;

import com.hotel.demo.model.Cart;
import com.hotel.demo.model.Menu;

public interface CartService {

	Cart addToCart(int menuId,int UserId,int quantity);
}
