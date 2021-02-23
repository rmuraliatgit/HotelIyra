package com.hotel.demo.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.event.MenuListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.demo.model.Cart;
import com.hotel.demo.model.Menu;
import com.hotel.demo.model.User;
import com.hotel.demo.respository.CartRepository;
import com.hotel.demo.respository.MenuRepository;
import com.hotel.demo.respository.UserRepository;
import com.hotel.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	static int i=0;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	MenuRepository menuRepository;

	List<Menu> menuList = new ArrayList<>();

	@Override
	public Cart addToCart(int menuId, int userId) {
		
		Menu menu = menuRepository.findById(menuId);
		Cart cart;
		User user = userRepository.findById(userId);
		System.out.println(user);
		System.out.println(cartRepository.findByUserId(userId));
		if (cartRepository.findByUserId(userId) != null) {
			cart = cartRepository.findByUserId(userId);
			System.out.println(menu);
			List<Menu> obj = new ArrayList<>();			
			obj = cart.getMenu();
			obj.add(menu);
//			menuList.add(menu);
			cart.setMenu(obj);
			System.out.println(menuList);
		} else {
			cart=new Cart(++i,user,null);
			System.out.println("else");
			
			System.out.println(menu);
			menuList.add(menu);
			cart.setMenu(menuList);
			System.out.println(cart);
		}
		System.out.println(cart);
		return cartRepository.save(cart);
	}
}
