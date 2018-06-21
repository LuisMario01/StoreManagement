package com.store.management.util;

import com.store.management.domain.Product;
import com.store.management.domain.ProductLog;

public class ProductLogUtil {
	public static ProductLog createProductLog(Product product, Double previousPrice) {
		ProductLog newLog = new ProductLog();
		newLog.setIdProduct(product.getIdProduct());
		newLog.setPreviousPrice(previousPrice);
		newLog.setCurrentPrice(product.getPrice());
		return newLog;
	}

}
