package com.hotel.demo.service;

import java.io.IOException;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import com.hotel.demo.model.Menu;


public interface MenuService {

	Menu addMenu(MultipartFile file, String dishName, String description, int price)throws IllegalStateException, IOException;
	
	List<Menu> findAllMenus();
	
	void deleteMenu(String dishName);
	
	Menu updateMenu(@RequestBody Menu menu);
}
