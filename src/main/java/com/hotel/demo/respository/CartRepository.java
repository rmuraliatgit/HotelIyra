package com.hotel.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.hotel.demo.model.Cart;
import com.hotel.demo.model.Menu;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

	@Query("Select c from Cart c where user.id=?1")
	Cart findByUserId(int id);
	
}
