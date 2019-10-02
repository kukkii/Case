package com.trendyol.shoppingCartApp.model;

import java.util.HashMap;
import java.util.Map.Entry;

public class Coupon {

	private double minPurchaseAmount;
	private double discountRatio;
	private DiscountType discountType;

	public Coupon(double minPurchase, double discountRat, DiscountType discountType) {

		this.minPurchaseAmount = minPurchase;
		this.discountRatio = discountRat;
		this.discountType = discountType;

	}

	/***
	 * Applies discount according to the current shopping amount
	 * @param currentPurchAmount
	 * @return
	 */
	public double applyCouponDiscount(double currentPurchAmount) {

		if (currentPurchAmount > this.minPurchaseAmount) {
			if (discountType.equals(DiscountType.Rate)) {

				return currentPurchAmount * discountRatio;

			} else {
				return discountRatio;
			}
		}
		return 0.0;
	}

	public double getMinPurchaseAmount() {
		return this.minPurchaseAmount;
	}

	public void setMinPurchaseAmount(int minPurchaseAmount) {
		this.minPurchaseAmount = minPurchaseAmount;
	}

	public double getDiscountRatio() {
		return discountRatio;
	}

	public void setDiscountRatio(int discountRatio) {
		this.discountRatio = discountRatio;
	}

	public DiscountType getDiscountType() {
		return discountType;
	}

	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}

}
