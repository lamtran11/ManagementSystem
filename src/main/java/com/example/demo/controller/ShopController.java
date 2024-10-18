package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Shop;
import com.example.demo.service.ShopService;

@RestController
@RequestMapping("/shop")
public class ShopController {

	private ShopService shopService;
	
	public ShopController (ShopService shopService) {
		this.shopService = shopService;
	}
	
	@GetMapping("/shopInfoList")
	public List<Shop> findAllShopInfo() {
		
		return shopService.findAllShop();		
	}
	
}
