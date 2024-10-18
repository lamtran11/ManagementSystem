package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Shop;
import com.example.demo.repository.ShopRepository;


@Service
public class ShopServiceImpl implements ShopService {
	
	private final ShopRepository shopRepository;
	
	@Autowired
	public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

	@Override
	public List<Shop> findAllShop() {
		
		return shopRepository.findAll();
	}

	
	
}
