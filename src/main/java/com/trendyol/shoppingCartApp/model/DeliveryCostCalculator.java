package com.trendyol.shoppingCartApp.model;

import java.util.HashMap;
import java.util.Map.Entry;

public class DeliveryCostCalculator {

	private double costPerDelivery;
	private double costPerProduct;
	private double fixedCost;

	public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {

		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
		this.fixedCost = fixedCost;

	}

	
	/***
	 * The formula for this calculation is (CostPerDelivery * NumberOfDeliveries ) + (CostPerProduct * NumberOfProducts)+ Fixed Cost
	 * NumberOfDeliveries stands for the distinct numbers of categories
	 * NumberOfProducts stands for the sum of the distinct number of product for each categories
	 * @param cart
	 * @return
	 */
	public double calculateCostDelivery(ShoppingCart cart) {
		int numberOfDeliveries = cart.getShoppingItems().entrySet().size();
		double numberOfProducts = 0.0;

		for (Entry<Category, HashMap<Product, Integer>> prod : cart.getShoppingItems().entrySet()) {
			HashMap<Product, Integer> asd = prod.getValue();
			numberOfProducts += asd.entrySet().stream().count();
		}

		return (this.costPerDelivery * numberOfDeliveries) + (this.costPerProduct * (int) numberOfProducts) + this.fixedCost;
	}

	public double getCostPerDelivery() {
		return this.costPerDelivery;
	}

	public void setCostPerDelivery(double costPerDelivery) {
		this.costPerDelivery = costPerDelivery;
	}

	public double getCostPerProduct() {
		return costPerProduct;
	}

	public void setCostPerProduct(double costPerProduct) {
		this.costPerProduct = costPerProduct;
	}

	public double getFixedCost() {
		return fixedCost;
	}

	public void setFixedCost(double fixedCost) {
		this.fixedCost = fixedCost;
	}

}
