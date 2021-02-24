package com.hotel.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hotel.demo.model.Bill;
import com.hotel.demo.service.BillService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BillController {

	@Autowired
	BillService billService;
	
	@PostMapping("/bill/{id}")
	public ResponseEntity<Bill> createBill(@PathVariable("id")int id)
	{
		Bill bill=billService.createBill(id);
		return new ResponseEntity<>(bill,HttpStatus.CREATED);
	}
}
