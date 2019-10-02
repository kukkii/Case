package com.trendyol.shoppingCartApp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.trendyol.shoppingCartApp.model.Campaign;
import com.trendyol.shoppingCartApp.model.Category;
import com.trendyol.shoppingCartApp.model.Coupon;
import com.trendyol.shoppingCartApp.model.DeliveryCostCalculator;
import com.trendyol.shoppingCartApp.model.DiscountType;
import com.trendyol.shoppingCartApp.model.Product;
import com.trendyol.shoppingCartApp.model.ShoppingCart;

public class ShoppingCartTest {

	ShoppingCart cart = new ShoppingCart();
	Category foodCategory = new Category("food");
	Category clothesCategory = new Category("clothes");
	Product product = new Product("apple", 10.0, foodCategory);
	DeliveryCostCalculator dcc = new DeliveryCostCalculator(1.0, 2.0, 2.99);

	Coupon couponAmount = new Coupon(50, 5, DiscountType.Amount);
	Coupon couponRate = new Coupon(50, 0.2, DiscountType.Rate);

	Campaign foodCampaignRate = new Campaign(foodCategory, 1, 0.2, DiscountType.Rate);
	Campaign foodCampaignAmount = new Campaign(foodCategory, 1, 5, DiscountType.Amount);

	Campaign clothesCampaignAmount = new Campaign(clothesCategory, 1, 5, DiscountType.Amount);

	HashMap<Category, HashMap<Product, Integer>> items = new HashMap<>();
	HashMap<Product, Integer> prod = new HashMap<>();

	@Before
	public void setUp() {
		
		prod.put(product, 6);
		items.put(foodCategory, prod);
	}

	@Test
	public void addItemTest() {

		cart.addItem(product, 6);
		assertEquals(items, cart.getShoppingItems());

	}

	@Test
	public void totalAmountWithoutDiscountTest() {

		cart.calculateCurrentBalanceWithoutDiscount(product, 6);
		assertTrue("true ", 60.0 - cart.getTotalAmountWithoutDiscount() == 0);

	}

	@Test
	public void couponDiscountTypeAmountTest() {
		
		cart.addItem(product, 6);
		cart.applyDiscounts(this.couponAmount);
		assertTrue("true", cart.getCouponDiscount() == 5);

	}

	@Test
	public void couponDiscountTypeRateTest() {
		cart.addItem(product, 10);
		cart.applyDiscounts(this.couponRate);
		assertTrue("true", cart.getCouponDiscount() == 20.0);

	}

	@Test
	public void minCouponPurchaseDicountTest() {

		Coupon couponMinAmount = new Coupon(100, 5, DiscountType.Amount);

		cart.addItem(product, 6);
		cart.applyDiscounts(couponMinAmount);
		cart.applyDiscounts(couponMinAmount);
		assertTrue("true", cart.getCouponDiscount() == 0);
	}

	@Test
	public void differentCouponsDiscountTest() {

		cart.addItem(product, 6);
		cart.applyDiscounts(this.couponAmount);
		cart.applyDiscounts(this.couponAmount);
		assertTrue("true", cart.getCouponDiscount() == 10);
	}

	@Test
	public void campaignDiscountAfterCouponDiscountAppliedTest() {

		Coupon coupon = new Coupon(10, 5, DiscountType.Amount);

		cart.addItem(product, 6);
		cart.applyDiscounts(this.foodCampaignAmount);
		cart.applyDiscounts(coupon);
		assertTrue("true", cart.getTotalAmountAfterDiscounts() == 50.0);

	}

	@Test
	public void campaignInvalidCategoryDiscountTest() {

		cart.addItem(product, 6);
		cart.applyDiscounts(this.clothesCampaignAmount);
		assertTrue("true", cart.getCampaignDiscount() == 0);

	}

	@Test
	public void campaignValidCategoryDiscountRateTest() {

		cart.addItem(product, 10);
		cart.applyDiscounts(this.foodCampaignRate);
		assertTrue("true", cart.getCampaignDiscount() == 20.0);

	}

	@Test
	public void campaignTwoDifferentCategoriesDiscountTest() {

		cart.addItem(product, 6);
		cart.applyDiscounts(this.foodCampaignAmount, this.clothesCampaignAmount);
		assertTrue("true", cart.getCampaignDiscount() == 5.0);

	}

	@Test
	public void deliveryCostCalculatorTest() {

		cart.addItem(product, 6);
		cart.getDeliveryCost(dcc);
		assertTrue("true", cart.getDeliveryCost(dcc) == 5.99);

	}

}
