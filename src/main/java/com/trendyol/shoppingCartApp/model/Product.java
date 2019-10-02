package com.trendyol.shoppingCartApp.model;


public class Product {

	private String productTitle;
	private double productPrice;
	private Category category;
	
	public Product(String title, double price , Category cat) {
		
		this.productTitle = title;
		this.productPrice = price;
		this.category = cat;
		
	}
	
	
	public String getTitle() {
		return productTitle;
	}
	public void setTitle(String title) {
		this.productTitle = title;
	}
	public double getPrice() {
		return productPrice;
	}
	public void setPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	

}
