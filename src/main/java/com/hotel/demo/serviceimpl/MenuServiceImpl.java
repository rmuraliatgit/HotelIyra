package com.hotel.demo.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import com.hotel.demo.exception.DishNotFoundException;
import com.hotel.demo.model.Menu;
import com.hotel.demo.respository.MenuRepository;
import com.hotel.demo.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	MenuRepository menuRepository;

	@Override
	public Menu addMenu(MultipartFile file, String dishName, String description, int price)
			throws IllegalStateException, IOException {
		long append = System.nanoTime();
		String filename = append + "_" + file.getOriginalFilename();
//		System.out.println(filename);
//		System.out.println(file.getSize());
		String filetype = file.getContentType();
		Path targetLocation = Paths.get("src/main/resources/static/uploads/" + filename);
		Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
		Menu menu = new Menu(dishName, description, price, filetype, filename);
		return menuRepository.save(menu);
	}

	@Override
	public List<Menu> findAllMenus() {
		return menuRepository.findAll();
	}

	@Override
	public void deleteMenu(String dishName) {
		Menu dish = menuRepository.findByDishName(dishName);
		if (dish != null)
			menuRepository.delete(dish);
		else {
			throw new DishNotFoundException("Dish not found");
		}
	}

	@Override
	@Transactional
	public Menu updateMenu(@RequestBody Menu menu) {
		Menu updateMenu = menuRepository.findByDishName(menu.getDishName());

		if (menu.getDescription() != null) {
			updateMenu.setDescription(menu.getDescription());
		}
		if (menu.getPrice() != 0) {
			updateMenu.setPrice(menu.getPrice());
		}
		if (menu.getImageName() != null) {
			updateMenu.setImageName(menu.getImageName());
		}

		return updateMenu;
	}
}
