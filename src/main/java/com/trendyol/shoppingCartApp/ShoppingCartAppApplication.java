package com.trendyol.shoppingCartApp;

import com.trendyol.shoppingCartApp.model.Campaign;
import com.trendyol.shoppingCartApp.model.Category;
import com.trendyol.shoppingCartApp.model.Coupon;
import com.trendyol.shoppingCartApp.model.DeliveryCostCalculator;
import com.trendyol.shoppingCartApp.model.DiscountType;
import com.trendyol.shoppingCartApp.model.Product;
import com.trendyol.shoppingCartApp.model.ShoppingCart;

public class ShoppingCartAppApplication {

	public static void main(String[] args) {

		// sample creating a new category
		Category food = new Category("food");
		// products
		Product apple = new Product("Apple", 100.0, food);
		Product almond = new Product("Almonds", 150.0, food);
		// Products can be added to a shopping cart with quantity
		ShoppingCart cart = new ShoppingCart();
		cart.addItem(apple, 3);
		cart.addItem(almond, 1);

		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(1, 2, 2.99);

		// discount rules can be 20% on a category if bought more than 3 items
		Campaign campaign1 = new Campaign(food, 3, 0.2, DiscountType.Rate);
		// another campaign rule 50% on a category if bought more than 5 items
		Campaign campaign2 = new Campaign(food, 5, 0.5, DiscountType.Rate);
		// another campaign rule 5 TL amount discount on a category if bought more than
		// items Campaign campaign3 = new Campaign(category,5.0,5,DiscountType.Amount);
		// Cart should apply the maximum amount of discount to the cart.
		// cart.applyDiscounts(discount1,discount2,discount3);
		// You can also apply a coupon to a cart
		// Coupons have minimum amount. If the cart total is less than minimum amount,
		// coupon is not applicable
		// Coupon for 100 TL min purchase amount for a 10% discount
		Coupon coupon = new Coupon(100, 0.10, DiscountType.Rate);
		cart.applyDiscounts(campaign1,campaign2);

		cart.applyDiscounts(coupon);

		cart.print();
		System.out.println("Delivery Cost: " + cart.getDeliveryCost(deliveryCostCalculator));

	}

}
