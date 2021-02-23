package com.hotel.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.demo.model.Quantity;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Integer> {

	
}
