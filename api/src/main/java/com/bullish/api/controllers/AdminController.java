package com.bullish.api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bullish.api.model.Product;
import com.bullish.api.service.StoreService;

@RestController
public class AdminController {
	@Autowired
	private StoreService storeService;
	
	@PostMapping("/admin/product")
	public ResponseEntity<Product> CreateCatalogProduct(@RequestBody Map<String, String> userMap) {
		Product newProduct = storeService.addToCatalog(userMap.get("name"), Double.valueOf(userMap.get("price")));
		return ResponseEntity.status(HttpStatus.CREATED).body(newProduct);
	}
	
	@DeleteMapping("/admin/product/{pid}")
	public ResponseEntity<Product> RemoveCatalogProduct(@PathVariable String pid) {
		storeService.removeFromCatalog(pid);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}	
	
	@PostMapping("/admin/product/{pid}/discount")
	public ResponseEntity<Product> CreateDiscount(@PathVariable String pid, @RequestBody Map<String, String> userMap) {
		Product discountedProduct = storeService.addDeal(pid, userMap.get("description"), Long.valueOf(userMap.get("discountLot")), Double.valueOf(userMap.get("discountRate")));
		return ResponseEntity.status(HttpStatus.CREATED).body(discountedProduct);
	}
}