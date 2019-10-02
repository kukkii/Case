package com.trendyol.shoppingCartApp.model;

import java.util.HashMap;
import java.util.Map.Entry;

public class Campaign {

	private Category category;
	private int purchaseCount;
	private double discountRate;
	private DiscountType discountType;

	public Campaign(Category category, int purchaseCount, double discountRate, DiscountType discountType) {
		this.category = category;
		this.purchaseCount = purchaseCount;
		this.discountRate = discountRate;
		this.discountType = discountType;
	}

	/***
	 * Applies discount according to the product category
	 * @param products
	 * @return
	 */
	public double applyCampaignDiscount(HashMap<Product, Integer> products) {
		int totalPurchCount = 0;
		double totalPurchAmount = 0;

		for (Entry<Product, Integer> entry : products.entrySet()) {
			Product product = entry.getKey();

			if (product.getCategory().equals(this.category)) {
				int quantity = entry.getValue();
				totalPurchCount += quantity;
				totalPurchAmount += product.getPrice() * quantity;
			}

		}

		if (totalPurchCount > this.purchaseCount) {
			if (this.discountType.equals(DiscountType.Rate)) {

				return totalPurchAmount * this.discountRate;

			} else {
				return  this.discountRate;
			}

		}

		return 0.0;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(int purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

}
