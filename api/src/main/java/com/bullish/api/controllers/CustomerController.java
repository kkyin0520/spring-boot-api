package com.bullish.api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bullish.api.model.Purchase;
import com.bullish.api.service.StoreService;

@RestController
public class CustomerController {
	@Autowired
	private StoreService storeService;
	
	@PostMapping("/basket/product/{pid}")
	public ResponseEntity<Purchase> AddBasketProduct(@PathVariable String pid, @RequestBody Map<String, String> userMap) {
		Purchase newPurchase = storeService.addToBasket(pid, Long.valueOf(userMap.get("amount")));
		return ResponseEntity.status(HttpStatus.CREATED).body(newPurchase);
		
	}
	
	@DeleteMapping("/basket/product/{pid}")
	public ResponseEntity<Purchase> RemoveBasketProduct(@PathVariable String pid) {
		storeService.removeFromBasket(pid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

	}
	
	@GetMapping("/receipt")
	public ResponseEntity<String>  GetReceipt() {
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(storeService.getReceipt());
	}
}
