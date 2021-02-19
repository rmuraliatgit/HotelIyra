package com.hotel.demo.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.hotel.demo.model.Menu;
import com.hotel.demo.model.User;
import com.hotel.demo.service.MenuService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class MenuController {

	@Autowired
	MenuService menuService;

	@PostMapping("/menu")
	public ResponseEntity<String> addItem(@RequestParam("file") MultipartFile file,
			@RequestParam("description") String description, @RequestParam("price") int price,
			@RequestParam("dishName") String dishName) throws IllegalStateException, IOException {
		Menu menu = menuService.addMenu(file, dishName, description, price);
		if (menu != null) {
			return new ResponseEntity<>("Successfully Added", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("File does not exists", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/menu")
	public ResponseEntity<List<Menu>> allMenus() {
		List menuList = menuService.findAllMenus();
		if (!menuList.isEmpty()) {
			return new ResponseEntity<>(menuList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/menu")
	public Message deleteMenu(@RequestParam("dishName") String dishName)
	{
		menuService.deleteMenu(dishName);
		Message message = new Message("Successfully Deleted");
        List<Menu> menu=menuService.findAllMenus();
        message.setMenus(menu);
        return message;
	}
	
	@PutMapping("/menu")
	public ResponseEntity<Message> updateUser( @RequestBody Menu menuObj){
		Menu menu = menuService.updateMenu(menuObj);
		Message message = new Message();
		message.setText("Menu Updated");
		message.setMenu(menu);
		message.setMenus(menuService.findAllMenus());
		return new ResponseEntity<>(message, HttpStatus.CREATED);
	}	

}



