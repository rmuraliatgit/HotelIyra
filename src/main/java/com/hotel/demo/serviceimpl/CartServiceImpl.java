package com.hotel.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.event.MenuListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.demo.model.Cart;
import com.hotel.demo.model.Menu;
import com.hotel.demo.model.Quantity;
import com.hotel.demo.model.User;
import com.hotel.demo.respository.CartRepository;
import com.hotel.demo.respository.MenuRepository;
import com.hotel.demo.respository.QuantityRepository;
import com.hotel.demo.respository.UserRepository;
import com.hotel.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	static int i = 0;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MenuRepository menuRepository;
	@Autowired
	QuantityRepository quantityRepository;

	List<Menu> menuList = new ArrayList<>();

	@Override
	public Cart addToCart(int menuId, int userId, int quantity) {

		Menu menu = menuRepository.findById(menuId);
		Cart cart;
		User user = userRepository.findById(userId);
		System.out.println(user);
		Cart cartobj = new Cart();
		System.out.println(cartRepository.findByUserId(userId));
		if (cartRepository.findByUserId(userId) != null) {
			cart = cartRepository.findByUserId(userId);
//			cart =new Cart(cart.getCartId(),user,null);
			System.out.println(menu);
			Quantity quantity2 = new Quantity(menuId,quantity,cart);
			quantityRepository.save(quantity2);
//			cart.setQuantity(quantity);
			List<Menu> obj = new ArrayList<>();
			obj = cart.getMenu();  
			obj.add(menu);
//			menuList.add(menu);
			cart.setMenu(obj);
			System.out.println(menuList);
			cartobj=cartRepository.save(cart);
		} else {
			cart = new Cart(++i,user, null);
			System.out.println("else");
			System.out.println(cart);
			cartobj=cartRepository.save(cart);
			System.out.println(cart);
			Quantity quantity2 = new Quantity(menuId,quantity,cart);
			quantityRepository.save(quantity2);
			menuList.add(menu);
			cartobj.setMenu(menuList);
			System.out.println(cart);
			cartobj=cartRepository.save(cartobj);			
		}
		System.out.println(cart);
		return cartobj;
	}
}
