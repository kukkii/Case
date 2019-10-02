package com.trendyol.shoppingCartApp.model;

import java.util.HashMap;
import java.util.Map.Entry;

public class ShoppingCart {

	HashMap<Category, HashMap<Product, Integer>> shoppingItems = new HashMap<>();
	double totalAmountWithoutDiscount;
	double couponDiscount;
	double campaignDiscount;

	/***
	 * General purpose print method that writes information about current cart items, 
	 * total purchase price and total discount applied 
	 */
	public void print() {

		shoppingItems.entrySet().forEach(category -> {
			category.getValue().entrySet().forEach(product -> {
				System.out.println(
						"Category: " + category.getKey().getTitle() + " - Product Name: " + product.getKey().getTitle()
								+ " - Quantity: " + product.getValue() + " - Unit Price: " + product.getKey().getPrice()
								+ " - Total Price: " + product.getKey().getPrice() * product.getValue());
			});
		});
		System.out.println(" Total Purchase Price: " + getTotalAmountAfterDiscounts() + "\n Total Discount Applied: "
				+ (getCouponDiscount() + getCampaignDiscount()));

	}

	/***
	 * Adds users products to the shoppingItems hashMap which is a map that holds the data group by Category -> Product, Quantity
	 * and also calculates the current shopping amount without discount
	 * @param product
	 * @param quantity
	 */
	public void addItem(Product product, int quantity) {

		if (!this.shoppingItems.containsKey(product.getCategory())) {

			HashMap<Product, Integer> categoryItem = new HashMap<>();
			categoryItem.put(product, quantity);

			this.shoppingItems.put(product.getCategory(), categoryItem);
		} else {
			HashMap<Product, Integer> productAmount = this.shoppingItems.get(product.getCategory());

			if (productAmount.containsKey(product)) {
				int currentAmount = productAmount.get(product) + quantity;
				productAmount.put(product, currentAmount);
			} else {
				productAmount.put(product, quantity);
			}

		}
		calculateCurrentBalanceWithoutDiscount(product, quantity);

	}

	/***
	 * Calculates the current balance of the shopping cart without discount 
	 * @param selectedProduct
	 * @param quantity
	 */
	public void calculateCurrentBalanceWithoutDiscount(Product selectedProduct, int quantity) {

		this.totalAmountWithoutDiscount += selectedProduct.getPrice() * quantity;
	}

	/***
	 * Campaign discount to be applied for the shopping cart.
	 * Method takes multiple campaigns to applied.
	 * @param campaigns
	 */
	public void applyDiscounts(Campaign... campaigns) {

		double currentCampaignDiscount = 0.0;
		for (Campaign campaign : campaigns) {
			if (this.shoppingItems.containsKey(campaign.getCategory())) {
				currentCampaignDiscount += campaign.applyCampaignDiscount(this.shoppingItems.get(campaign.getCategory()));
			}
		}
		this.campaignDiscount =  currentCampaignDiscount;
	}

	/***
	 * Coupon discount to be applied for the shopping cart.
	 * Method takes multiple coupon to be applied.
	 * @param coupons
	 */
	public void applyDiscounts(Coupon... coupons) {

		double campaignDiscountBalance = getTotalAmountAfterDiscounts();
		double currentCouponDiscount = 0.0;

		for (Coupon coupon : coupons) {
			currentCouponDiscount += coupon.applyCouponDiscount(campaignDiscountBalance);
		}
		this.couponDiscount += currentCouponDiscount;
	}

	/***
	 * Calculates the current amount after campaign and coupon discounts applied.
	 * @return
	 */
	public double getTotalAmountAfterDiscounts() {
		return this.totalAmountWithoutDiscount - getCampaignDiscount() - getCouponDiscount();
	}

	/***
	 * Returns the coupon discount that is made for the shopping cart.
	 * @return
	 */
	public double getCouponDiscount() {
		return this.couponDiscount;
	}

	/***
	 * Returns the campaign discount that is made for the shopping cart.
	 * @return
	 */
	public double getCampaignDiscount() {
		return this.campaignDiscount;
	}

	/***
	 * Calculates the delivery cost for shopping cart.
	 * @param dcc
	 * @return
	 */
	public double getDeliveryCost(DeliveryCostCalculator dcc) {

		return dcc.calculateCostDelivery(this);
	}

	public HashMap<Category, HashMap<Product, Integer>> getShoppingItems() {
		return this.shoppingItems;
	}

	public void setShoppingItems(HashMap<Category, HashMap<Product, Integer>> shoppingItems) {
		this.shoppingItems = shoppingItems;
	}

	public double getTotalAmountWithoutDiscount() {
		return this.totalAmountWithoutDiscount;
	}

	public void setTotalAmountWithoutDiscount(double totalAmountWithoutDiscount) {
		this.totalAmountWithoutDiscount = totalAmountWithoutDiscount;
	}

	public void setCouponDiscount(double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}

	public void setCampaignDiscount(double campaignDiscount) {
		this.campaignDiscount = campaignDiscount;
	}

}
