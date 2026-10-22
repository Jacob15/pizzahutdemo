package com.simplilearn.pizzahutdemo.pages;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.simplilearn.pizzahutdemo.Base.AbstractClass;

public class GiftCardPage extends AbstractClass {
	WebDriver driver;
	public GiftCardPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css="input[name='giftCardNumber']")
	WebElement giftCardNumber;
	
	
	@FindBy(css="input[name='giftCardPin']")
	WebElement giftCardPin;
	
	@FindBy(xpath="//span[text()='Apply Gift Card']")
	WebElement giftCardbutton;
	
	@FindBy(xpath="//button[text()='Cancel']")
	WebElement giftCardcancelButton;
	
	@FindBy(xpath="//span[.='Coupon']")
	WebElement couponTab;
	
	@FindBy(css="input[name='voucherId']")
	WebElement couponCode;

	@FindBy(xpath="//button[.='Apply']")
	WebElement couponApplyButton;
	
	@FindBy(xpath="//button[.='Cancel']")
	WebElement couponCancelButton;
	
	@FindBy(xpath="//span[.='Sorry, we don’t currently support that coupon code.']")
	WebElement couponErrorMessage;
	
	@FindBy(xpath="//span[text()='Error applying Gift Card']")
	WebElement errorGiftCard;
	
	By errorGiftCardSelector = By.xpath("//span[text()='Error applying Gift Card']");
	By couponErrorMessageSelector=By.xpath("//span[.='Sorry, we don’t currently support that coupon code.']");
	
	
	public String applyGiftCard(String number, String pin) {
		giftCardNumber.sendKeys(number);
		giftCardPin.sendKeys(pin);
		giftCardbutton.click();
		waitForElement(errorGiftCardSelector);
		if(errorGiftCard.isDisplayed()) {
			System.out.println("Error while applying Gift card");
			String errorMessage=errorGiftCard.getText();
			return errorMessage;
		}
		else
		{
			System.out.println("Gift card applied successfully");
			String message=errorGiftCard.getText();
			return message;
		}
		
		
	}
	public void cancelGiftCard() {
		giftCardcancelButton.click();
	}
	public String applyCouponCode(String coupon) {
		if(!couponTab.isSelected()) {
			couponTab.click();
		}
		couponCode.sendKeys(coupon);
		couponApplyButton.click();
		waitForElement(couponErrorMessageSelector);
		if(couponErrorMessage.isDisplayed()) {
			System.out.println("Coupon Error Message is displayed");
			String errorMessage=couponErrorMessage.getText();
			return errorMessage;
		}
		else {
			System.out.println("Coupon Error Message is not displayed");
			String message=couponErrorMessage.getText();
			return message;
		}
		
	}
	public void cancelCouponCode() {
		couponCancelButton.click();
	}
	
	
}
