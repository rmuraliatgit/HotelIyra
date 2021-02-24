package com.hotel.demo.service;

import org.springframework.stereotype.Service;

import com.hotel.demo.model.Bill;

@Service
public interface BillService {

	public Bill createBill(int userId);

}
