package com.hotel.demo.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hotel.demo.model.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Integer> {
	
	@Query("select q from Quantity q where q.cartId=?1")
	List<Quantity> findByCartId(int id);
}
