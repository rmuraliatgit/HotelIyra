package com.hotel.demo.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hotel.demo.model.Menu;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer>{

}
