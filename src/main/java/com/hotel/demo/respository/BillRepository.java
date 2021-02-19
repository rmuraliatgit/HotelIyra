package com.hotel.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.demo.model.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{

}
