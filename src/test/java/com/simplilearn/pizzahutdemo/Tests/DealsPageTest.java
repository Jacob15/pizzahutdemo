package com.simplilearn.pizzahutdemo.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.simplilearn.pizzahutdemo.TestComponents.BaseTest;
import com.simplilearn.pizzahutdemo.TestComponents.Retry;
import com.simplilearn.pizzahutdemo.pages.DealsPage;
import com.simplilearn.pizzahutdemo.pages.PopupPage;

public class DealsPageTest extends BaseTest {
	
	// Add your test methods here
	@Test(groups="Regression")
	public void testDealsPage() throws InterruptedException {
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		Thread.sleep(2000); // Wait for 2 seconds to allow the page to load
		Assert.assertTrue(dealsPage.verifyDealsPage());
	}
	@Test(groups = {"ErrorHandling"})
	public void testDealsaddedtext() throws InterruptedException {
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		Assert.assertEquals(dealsPage.dealsaddedtext(), "Deal added - Rs. 125 Off on bill value above Rs.500 Spend ₹500 on pizzas, sides and desserts to qualify.");
		
	}
	@Test(retryAnalyzer = Retry.class,groups="ErrorHandling")
	public void testDealsaddedtextBasket() throws InterruptedException {
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		Assert.assertEquals(dealsPage.dealsaddedtextBasket(), "Rs. 125 Off on bill value above Rs.500");
		Assert.assertTrue(dealsPage.dealsAddedtextBasketRemoval());
			
	}
	@Test(retryAnalyzer = Retry.class)
	public void testDealsaddedtextBasketRemoval() throws InterruptedException {
		PopupPage popupPage = new PopupPage(driver);
		DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
		
		Assert.assertFalse(dealsPage.dealsAddedtextBasketRemoval());
			
	}
	@Test(groups="Regression")
	public void testDealsaddedtextlanding() throws InterruptedException {
	    PopupPage popupPage = new PopupPage(driver);
	    DealsPage dealsPage = popupPage.navigateToDealsPage("Kodungaiyur");
	    Assert.assertTrue(dealsPage.VerifyPizzaPage());    
	}
	


}
