package com.simplilearn.pizzahutdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.simplilearn.pizzahutdemo.Base.AbstractClass;

public class SecurePage extends AbstractClass {
	WebDriver driver;
	public SecurePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="#checkout__name")
	WebElement checkoutName;
	
	@FindBy(css="#checkout__phone")
	WebElement checkoutPhone;
	
	@FindBy(css="#checkout__email")
	WebElement checkoutEmail;
	
	@FindBy(css="input[placeholder='House Number or Name']")
	WebElement houseAddress;
	
	@FindBy(xpath="//span[text()='Apply Gift Card']")
	WebElement applyGiftCardLink;
	
	@FindBy(xpath="//i[@class='mt-4']/parent::label[@data-synth='payment-method--razorpay/card-label']")
	WebElement cardsRadio;
	
	@FindBy(xpath="//i[@class='mt-4']/parent::label[@data-synth='payment-method--razorpay/wallet-label']")
	WebElement walletRadio;
	
	@FindBy(xpath="//i[@class='mt-4']/parent::label[@data-synth='payment-method--razorpay/netbanking-label']")
	WebElement netBankingRadio;
	
	@FindBy(xpath="//span[text()='Place your order']")
	WebElement placeYourOrderButton;
	
	@FindBy(xpath="//i[@class='ml-5 icon-caret-left']")
	WebElement backButton;
	
	By giftcardLocator=By.xpath("//span[text()='Apply a Gift Card']");
	

    By cardsRadioLocator=By.xpath("//i[@class='mt-4']/preceding-sibling::input[@name='card']");
 
	
	
	public PaymentPage placeYourOrder(String name, String phone, String email, String address,String paymentType) {
		checkoutName.sendKeys(name);
		checkoutPhone.sendKeys(phone);
		checkoutEmail.sendKeys(email);
		houseAddress.sendKeys(address);
		if(paymentType.contentEquals("cards" )) {

			//waitForElement(cardsRadioLocator);
			cardsRadio.click();
		}
		else if(paymentType.contentEquals("wallet")){
			walletRadio.click();
		
		}
		else if(paymentType.contentEquals("netbanking")){
			
			netBankingRadio.click();
		}
		
		placeYourOrderButton.click();
		
		return new PaymentPage(driver);
	}

     public GiftCardPage applyGiftCard() {
    	 applyGiftCardLink.click();
    	 waitForElement(giftcardLocator);
    	 return new GiftCardPage(driver);
    	 
     }
     public String securePageBack() {
    	 safeClick(backButton);
    	 String url=driver.getCurrentUrl();
    	 return url;
    	 
     }




	
	

}
