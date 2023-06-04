package com.bullish.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bullish.api.model.Product;
import com.bullish.api.model.Purchase;

@Service
public class StoreService {
	
	private static List<Purchase> basket = new ArrayList<Purchase>();
	private static List<Product> catalog = new ArrayList<Product>();
	
	private Product findProductCatalogById(String id) {
		for  (Product product:catalog) {
			if (product.getId().equals(id)) {
				return product;
			}
		}
		return null; 
	}
	private Purchase findPurchaseBasketById(String id) {
		for  (Purchase purchase:basket) {
			if (purchase.getId().equals(id)) {
				return purchase;
			}
		}
		return null; 
	}
	private Purchase findPurchaseBasketByProductId(String pid) {
		for  (Purchase purchase:basket) {
			if (purchase.getProduct().getId().equals(pid)) {
				return purchase;
			}
		}
		return null; 
	}	
	public Product addToCatalog(String name, double price) {
		Product product = new Product(Integer.toString(catalog.size()), name, true, price);
		catalog.add(product);
		return product;
	}
	public Product removeFromCatalog(String id) {
		Product product = findProductCatalogById(id);
		if (product != null) {
			catalog.get(catalog.indexOf(product)).setIsAvailable(false);
			return product;
		} else {
			return null;
		}
	}
	public Product addDeal(String id, String description, long discountLot, double discountRate) {
		Product product = findProductCatalogById(id);
		if (product != null) {
			catalog.get(catalog.indexOf(product)).setDealDescription(description);
			catalog.get(catalog.indexOf(product)).setDiscountLot(discountLot);
			catalog.get(catalog.indexOf(product)).setDiscountRate(discountRate);
			return product;
		} else {
			return null;
		}
	}
	
	public Purchase addToBasket(String productId, long amount) {
		Product product = findProductCatalogById(productId);
		Purchase outputPurchase =  null;
		if (product != null) {
			outputPurchase = findPurchaseBasketByProductId(productId);
			if ( outputPurchase != null) { 
				outputPurchase.setAmount(outputPurchase.getAmount() + amount);
			} else {
				outputPurchase = new Purchase(Integer.toString(basket.size()), product, amount);
				basket.add(outputPurchase);
			}
		}
		return outputPurchase;
	}
	public Purchase removeFromBasket(String purchaseId) {
		Purchase purchaseItem = findPurchaseBasketById(purchaseId);
		if (purchaseItem != null) {
			basket.remove(purchaseItem);
			return purchaseItem;
		} else {
			return null;
		}
	}
	public String getReceipt() {
		String receipt = "";
		double subTotal = 0;
		double total = 0;
		if (basket != null) { 
			for (Purchase purchase:basket) {
				subTotal = 0;
				// check for promotion
				double discountedLotAmount = Math.floor(purchase.getAmount() / purchase.getProduct().getDiscountLot());
				long remainderAmount = purchase.getAmount() % purchase.getProduct().getDiscountLot();
				subTotal += discountedLotAmount * purchase.getProduct().getDiscountLot() * purchase.getProduct().getPrice() * purchase.getProduct().getDiscountRate() 
							+ remainderAmount * purchase.getProduct().getPrice();
				total += subTotal;
				receipt += String.format("Item: %s, Amount: %s, Price: %s, Subtotal %s \n", purchase.getProduct().getName(), purchase.getAmount(),purchase.getProduct().getPrice(), subTotal);
				if ((purchase.getProduct().getDiscountRate() < 1) && (discountedLotAmount > 0)) {
					receipt += String.format("Discount %s \n", purchase.getProduct().getDealDescription());
				}
			}
			receipt += String.format("Total %,.2f", total);
		}
		return receipt;
	}
}
