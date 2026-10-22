package com.simplilearn.pizzahutdemo.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.simplilearn.pizzahutdemo.TestComponents.BaseTest;
import com.simplilearn.pizzahutdemo.pages.DealsPage;
import com.simplilearn.pizzahutdemo.pages.MeltsPage;
import com.simplilearn.pizzahutdemo.pages.PaymentPage;
import com.simplilearn.pizzahutdemo.pages.PizzaPage;
import com.simplilearn.pizzahutdemo.pages.PopupPage;
import com.simplilearn.pizzahutdemo.pages.SecurePage;

public class MeltsPageTest extends BaseTest {
	
	@Test
	public void testMeltsPage() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		MeltsPage meltsPage=dealsPage.navigateToMeltsPage();
		Assert.assertEquals(meltsPage.selectMelts(), "Loaded Veggie BBQ");
		
	}
	@Test
	public void testMeltsPageClose() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		MeltsPage meltsPage=dealsPage.navigateToMeltsPage();
		//meltsPage.selectMelts();
		meltsPage.closeMelt();

		
	}
	@Test
	public void testMeltsPagePlaceOrder() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		MeltsPage meltsPage=dealsPage.navigateToMeltsPage();
		Assert.assertTrue(meltsPage.cartCheckoutMelt("Loaded Veggie BBQ", "Cheezy Cheese Veg"));
		

	}
	@Test
	public void testMeltsCartPageUPI() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		MeltsPage meltsPage = dealsPage.navigateToMeltsPage();
	    SecurePage securePage=meltsPage.meltCheckoutUPI("Loaded Veggie BBQ");
	    PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "upi");
	    Assert.assertEquals(paymentPage.upiVerification(), "UPI QR");
	}
	@Test
	public void testMeltsCartPageCards() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		MeltsPage meltsPage = dealsPage.navigateToMeltsPage();
	    SecurePage securePage=meltsPage.meltCheckoutUPI("Loaded Veggie BBQ");
	    PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "cards");
	    Assert.assertEquals(paymentPage.cardsVerification(), "Add a new card");
	    paymentPage.closePopup();
	    
	}
	@Test
	public void testMeltsCartPageWallet() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		MeltsPage meltsPage = dealsPage.navigateToMeltsPage();
	    SecurePage securePage=meltsPage.meltCheckoutUPI("Loaded Veggie BBQ");
	    PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "wallet");
	    Assert.assertEquals(paymentPage.walletVerification(), "All Wallet Options");
	    paymentPage.closePopup();
	}
	@Test
	public void testMeltsCartPageNetbanking() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		MeltsPage meltsPage = dealsPage.navigateToMeltsPage();
	    SecurePage securePage=meltsPage.meltCheckoutUPI("Loaded Veggie BBQ");
	    PaymentPage paymentPage=securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "netbanking");
	    Assert.assertEquals(paymentPage.netbankingVerification(), "Suggested Banks");
	    paymentPage.closePopup();
	}
	@Test
	public void testMeltsCartPageBack() throws InterruptedException{
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		MeltsPage meltsPage = dealsPage.navigateToMeltsPage();
	    SecurePage securePage=meltsPage.meltCheckoutUPI("Loaded Veggie BBQ");
	    //securePage.placeYourOrder("jacob","8807318292", "a.jacob@gmail.com", "22 North street", "netbanking");
	    String url=securePage.securePageBack();
	    Assert.assertEquals(url.contains("deals"), true);
	    
		
	}
	
	}


