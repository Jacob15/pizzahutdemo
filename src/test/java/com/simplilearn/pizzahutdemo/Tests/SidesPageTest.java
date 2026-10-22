package com.simplilearn.pizzahutdemo.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.simplilearn.pizzahutdemo.TestComponents.BaseTest;
import com.simplilearn.pizzahutdemo.pages.DealsPage;
import com.simplilearn.pizzahutdemo.pages.GiftCardPage;
import com.simplilearn.pizzahutdemo.pages.MeltsPage;
import com.simplilearn.pizzahutdemo.pages.PaymentPage;
import com.simplilearn.pizzahutdemo.pages.PopupPage;
import com.simplilearn.pizzahutdemo.pages.SecurePage;
import com.simplilearn.pizzahutdemo.pages.SidesPage;

public class SidesPageTest extends BaseTest {
	@Test
	public void testSidesPage() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		Assert.assertEquals(sidesPage.selectSides(), "Cheesy Pocket 2pc");
		
	}
	@Test
	public void testSidesPageItemsVerify() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		sidesPage.verifySidesOverlay();
	}
	@Test
	public void testSidesOrder() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		Assert.assertTrue(sidesPage.cartCheckoutSides("Cheesy Pocket 2pc", "Indi Cheesy Pocket 2pc"));
		
	}
	@Test
	public void testSidesOrderUPI() throws InterruptedException{
		PopupPage popupPage=new PopupPage(driver);
		DealsPage dealsPage=popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		SecurePage securePage=sidesPage.cartCheckoutSidesUPI();
	    PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "upi");
	    Assert.assertEquals(paymentPage.upiVerification(), "UPI QR");
				
	}
	@Test
	public void testSidesOrderCards() throws InterruptedException{
		PopupPage popupPage=new PopupPage(driver);
		DealsPage dealsPage=popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		SecurePage securePage=sidesPage.cartCheckoutSidesUPI();
		PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "cards");
	    Assert.assertEquals(paymentPage.cardsVerification(), "Add a new card");
	    paymentPage.closePopup();
	}
	@Test
	public void testSidesOrderWallet() throws InterruptedException{
		PopupPage popupPage=new PopupPage(driver);
		DealsPage dealsPage=popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		SecurePage securePage=sidesPage.cartCheckoutSidesUPI();
		PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "wallet");
	    Assert.assertEquals(paymentPage.walletVerification(), "All Wallet Options");
	    paymentPage.closePopup();
	}
	@Test
	public void testSidesOrderNetBanking() throws InterruptedException{
		PopupPage popupPage=new PopupPage(driver);
		DealsPage dealsPage=popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		SecurePage securePage=sidesPage.cartCheckoutSidesUPI();
		PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "netbanking");
	    Assert.assertEquals(paymentPage.netbankingVerification(), "Suggested Banks");
	    paymentPage.closePopup();
		
	}
	@Test
	public void testSidesOrderApplyGiftCard() throws InterruptedException{
		PopupPage popupPage=new PopupPage(driver);
		DealsPage dealsPage=popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		SecurePage securePage=sidesPage.cartCheckoutSidesUPI();
		GiftCardPage giftCard=securePage.applyGiftCard();
	    Assert.assertEquals(giftCard.applyGiftCard("123456789", "123456"), "Error applying Gift Card");

	}
	@Test
	public void testSidesOrderCancelGiftCard() throws InterruptedException{
		PopupPage popupPage=new PopupPage(driver);
		DealsPage dealsPage=popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		SecurePage securePage=sidesPage.cartCheckoutSidesUPI();
		GiftCardPage giftCard=securePage.applyGiftCard();
	    giftCard.cancelGiftCard();
	}
	@Test(groups="ErrorHandling")
	public void testSidesOrderApplyCouponCode() throws InterruptedException{
		PopupPage popupPage=new PopupPage(driver);
		DealsPage dealsPage=popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		SecurePage securePage=sidesPage.cartCheckoutSidesUPI();
		GiftCardPage giftCard=securePage.applyGiftCard();
		Assert.assertEquals(giftCard.applyCouponCode("ABC1234"), "Sorry, we don’t currently support that coupon code.");
		
     }
	@Test
	public void testSidesOrderCancelCouponCode() throws InterruptedException{
		PopupPage popupPage=new PopupPage(driver);
		DealsPage dealsPage=popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		SecurePage securePage=sidesPage.cartCheckoutSidesUPI();
		GiftCardPage giftCard=securePage.applyGiftCard();
	    giftCard.cancelCouponCode();
	}
	@Test
	public void testSidesOrderSecureBack() throws InterruptedException{
		PopupPage popupPage=new PopupPage(driver);
		DealsPage dealsPage=popupPage.navigateToDealsPage("Kodungaiyur");
		SidesPage sidesPage=dealsPage.navigateToSidesPage();
		SecurePage securePage=sidesPage.cartCheckoutSidesUPI();
		String url=securePage.securePageBack();
	    Assert.assertEquals(url.contains("deals"), true);
	}
	
	
	

}
