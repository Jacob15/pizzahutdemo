package com.simplilearn.pizzahutdemo.Tests;

import com.simplilearn.pizzahutdemo.TestComponents.BaseTest;
import com.simplilearn.pizzahutdemo.pages.DealsPage;
import com.simplilearn.pizzahutdemo.pages.GiftCardPage;
import com.simplilearn.pizzahutdemo.pages.PaymentPage;
import com.simplilearn.pizzahutdemo.pages.PopupPage;
import com.simplilearn.pizzahutdemo.pages.SecurePage;
import com.simplilearn.pizzahutdemo.pages.PizzaPage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PizzasPageTest extends BaseTest {
	// Add your test methods here
	
	@Test
	public void testPizzasPage() throws InterruptedException {
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
		Assert.assertEquals(pizzasPage.selectPizza(), "Mexican Mango Habanero");
		
		
	}
	@Test
	public void testPizzaSelectionClose() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
		pizzasPage.closePizza();
	}
	@Test
	public void testPizzaDropdownValueCheck() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
		Assert.assertTrue(pizzasPage.dropdownText());
	}
	@Test
	public void testPizzaDropdownSelection() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
		pizzasPage.dropdownSelection();

	}
	@Test
	public void testPizzaAddToCart() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
		pizzasPage.cartCheckoutItem(" Four Pepper | Olive Garden ");
		Assert.assertEquals(pizzasPage.cartCheckoutItem(" Four Pepper | Olive Garden "), "Personal Mexican Mango Habanero");
	}
	@Test
	public void testPizzaAddToCartCheckout() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
		//pizzasPage.cartCheckoutItem("Personal Mexican Mango Habanero");
		Assert.assertTrue(pizzasPage.cartSecurePage(" Four Pepper | Olive Garden "));
	
	}
	@Test
	public void testPizzaCheckoutUPI() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
	    SecurePage securePage=pizzasPage.cartSecurePageUPI(" Four Pepper | Olive Garden ");
	    PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "upi");
	    Assert.assertEquals(paymentPage.upiVerification(), "UPI QR");
	
	}
	@Test
	public void testPizzaCheckoutCards() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
	    SecurePage securePage=pizzasPage.cartSecurePageUPI(" Four Pepper | Olive Garden ");
	    PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "cards");
	    Assert.assertEquals(paymentPage.cardsVerification(), "Add a new card");
	    paymentPage.closePopup();
	
		
	}
	@Test(groups="Errorhandling")
	public void testPizzaCheckoutWallet() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
	    SecurePage securePage=pizzasPage.cartSecurePageUPI(" Four Pepper | Olive Garden ");
	    PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "wallet");
	    Assert.assertEquals(paymentPage.walletVerification(), "All Wallet Options");
	    paymentPage.closePopup();
		
	}
	@Test(groups="Regression")
	public void testPizzaCheckoutNetbanking() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
	    SecurePage securePage=pizzasPage.cartSecurePageUPI(" Four Pepper | Olive Garden ");
	    PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "netbanking");
	    Assert.assertEquals(paymentPage.netbankingVerification(), "Suggested Banks");
	    paymentPage.closePopup();
	}
	@Test(groups="ErrorHandling")
	public void testPizzaCheckoutApplyGiftCard() throws InterruptedException{
		
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
	    SecurePage securePage=pizzasPage.cartSecurePageUPI(" Four Pepper | Olive Garden ");
	    GiftCardPage giftCard=securePage.applyGiftCard();
	    Assert.assertEquals(giftCard.applyGiftCard("123456789", "123456"), "Error applying Gift Card");
	    
	}
	@Test
	public void testPizzaCheckoutCancelGiftCard() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
	    SecurePage securePage=pizzasPage.cartSecurePageUPI(" Four Pepper | Olive Garden ");
	    GiftCardPage giftCard=securePage.applyGiftCard();
	    giftCard.cancelGiftCard();
		
	}
	@Test(groups="Regression")
	public void testPizzaCheckoutCouponCode() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
	    SecurePage securePage=pizzasPage.cartSecurePageUPI(" Four Pepper | Olive Garden ");
	    GiftCardPage giftCard=securePage.applyGiftCard();
	    Assert.assertEquals(giftCard.applyCouponCode("ABC1234"), "Sorry, we don’t currently support that coupon code.");
		
	}
	@Test
	public void testPizzaCheckoutCancelCouponCode() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		PizzaPage pizzasPage = dealsPage.navigateToPizzaPage();
	    SecurePage securePage=pizzasPage.cartSecurePageUPI(" Four Pepper | Olive Garden ");
	    GiftCardPage giftCard=securePage.applyGiftCard();
	    giftCard.cancelCouponCode();
		
	}
	

}
