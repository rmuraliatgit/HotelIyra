package com.hotel.demo.serviceimpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hotel.demo.model.Bill;
import com.hotel.demo.model.Cart;
import com.hotel.demo.model.Menu;
import com.hotel.demo.model.Quantity;
import com.hotel.demo.model.User;
import com.hotel.demo.respository.BillRepository;
import com.hotel.demo.respository.CartRepository;
import com.hotel.demo.respository.MenuRepository;
import com.hotel.demo.respository.QuantityRepository;
import com.hotel.demo.respository.UserRepository;
import com.hotel.demo.service.BillService;

@Service
public class BillServiceImpl implements BillService {
	
	private static final int ArrayList = 0;
	@Autowired
	BillRepository billRepository;
	@Autowired
	CartRepository cartRepository;
	@Autowired
	QuantityRepository quantityRepository;
	@Autowired 
	MenuRepository menuRepository;
	@Autowired 
	UserRepository userRepository;
	
	
	
	public Bill createBill(int userId)
	{
		double sum=0;
		List<Menu> menuList = new ArrayList<>();
		List<Double> prices = new ArrayList<>();
		List<Integer> quantities = new ArrayList<>();
		
	    Cart cart = cartRepository.findByUserId(userId);
	    String userName = cart.getUser().getName();
	    User user=userRepository.findById(userId);
	    String date = LocalDate.now().toString();
		String time = LocalTime.now().toString();
		Bill bill = new Bill();
		bill.setUserName(user.getName());
		bill.setDate(date);
		bill.setTime(time);
		bill.setCart(cart);
	    menuList = cart.getMenu();
	    for(Quantity quantity:quantityRepository.findByCartId(cart.getCartId())){
	    	
	    	Menu menu= menuRepository.findById(quantity.getMenuId());
	    	sum+=menu.getPrice()*quantity.getQuantity();
	    }
	    bill.setTotalAmount(sum);
    	billRepository.save(bill);
		return bill;
	}
	
	

}
