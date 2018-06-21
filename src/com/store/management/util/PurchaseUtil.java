package com.store.management.util;

import java.util.Date;

import com.store.management.domain.Purchase;
import com.store.management.dto.BuyDTO;

public class PurchaseUtil {
	
	public static Purchase createPurchase(BuyDTO purchaseDTO) {
		Purchase purchase = new Purchase();
		purchase.setIdUser(purchaseDTO.getIdUser());
		purchase.setIdProduct(purchaseDTO.getIdProduct());
		purchase.setAmount(purchaseDTO.getAmount());
		purchase.setDate(new Date());
		return purchase;
	}
}
