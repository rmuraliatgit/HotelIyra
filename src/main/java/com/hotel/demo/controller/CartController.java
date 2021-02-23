package com.hotel.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.demo.model.Cart;
import com.hotel.demo.model.Menu;
import com.hotel.demo.service.CartService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CartController {

	@Autowired
	CartService cartService;
	
	@PostMapping("/cart/{menuId}")
	public ResponseEntity<Cart> addToCart(@PathVariable("menuId") int menuId, @RequestParam int userId)
	{		
		Cart cart=cartService.addToCart(menuId,userId);
		if(cart!=null)
		return new ResponseEntity<>(cart,HttpStatus.CREATED);
		else 
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
}
