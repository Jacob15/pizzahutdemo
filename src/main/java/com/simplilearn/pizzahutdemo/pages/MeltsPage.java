package com.simplilearn.pizzahutdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.simplilearn.pizzahutdemo.Base.AbstractClass;

public class MeltsPage extends AbstractClass {
	WebDriver driver;

	public MeltsPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[@data-synth='link--loaded-veggie-bbq-single']")
	WebElement meltsItem;
	@FindBy(xpath="//h2[@class='typography-1 capitalize single-item__title text-center lg:text-left hidden lg:block lg:px-20 lg:py-25']")
	WebElement meltsHeader;
	@FindBy(xpath="//button[@class='icon-close p-30 z-400 absolute top-0 right-0']")
	WebElement meltsClose;
	@FindBy(xpath="//button[@data-synth='button--loaded-veggie-bbq-single--one-tap']")
	WebElement meltsAddOne;
	@FindBy(xpath="//button[@data-synth='button--cheesy-cheese-popcorn-single--one-tap']")
	WebElement meltsAddTwo;
	@FindBy(xpath="//div[@data-synth='basket-item-type--side']")
	WebElement meltsBasketItem;
	@FindBy(xpath="//span[text()='Checkout']")
	WebElement checkoutButton;
	@FindBy(xpath="//span[@class='subtotal']")
	WebElement subTotalField;
	@FindBy(xpath="//span[text()='Secure Checkout']")
	WebElement secureHeader;
	@FindBy(xpath="//span[contains(text(),'Continue Shopping')]")
	WebElement continueShopping;
	
	By meltsItemLocator=By.xpath("//a[@data-synth='link--loaded-veggie-bbq-single']");
	By meltsHeaderText= By.xpath("//h2[text()='Loaded Veggie BBQ']");
	By meltsCloseLocator=By.xpath("//button[@class='icon-close p-30 z-400 absolute top-0 right-0']");
	By meltsBasketItemLocator=By.xpath("//div[@data-synth='basket-item-type--side']");
	By secureCheckout=By.xpath("//span[text()='Secure Checkout']");
	By continueShoppingLocator=By.xpath("//span[contains(text(),'Continue Shopping')]");
	public String selectMelts() {
		
			// Implement the logic to select a pizza based on the provided pizzaName
			// For example, you can locate the pizza element using its name and click on it
			waitForElement(meltsItemLocator);
			meltsItem.click();
			waitForElement(meltsHeaderText);
			//waitForElement(pizzaHeaderLocator);
			String meltsName= meltsHeader.getText();
			return meltsName;
			
		}
	 public void closeMelt() {
		 waitForElement(meltsItemLocator);
			safeClick(meltsItem);
			waitForElement(meltsCloseLocator);
			meltsClose.click();
	 }
     public Boolean cartCheckoutMelt(String meltNameOne, String meltNameTwo) {
    	 meltsAddOne.click();
    	 waitForElement(meltsBasketItemLocator);
    	 String basketItem=meltsBasketItem.getText();
    	 if(basketItem.contentEquals(meltNameOne)) {
    		 System.out.println("Melt Name "+ meltNameOne+" is present in the basket");
    	 }
    	 else {
    		 System.out.println("Melt Name "+ meltNameOne+" is not present in the basket");
    	 }
    	 String subTotalValue=subTotalField.getText();
         String[] parts = subTotalValue.split("₹");
         System.out.println(parts[1]);
         try {
    	 double value=Double.parseDouble(parts[1]);
    	 System.out.println(value);
    	 if(value<200.00) {
    		 safeClick(meltsAddTwo);
    		 checkoutButton.click();
    	 }else {
    		 checkoutButton.click();
    		 
    	 }
    	
         }
         catch(NumberFormatException e) {
        	System.out.println(e.getMessage());
         }
    	
    	 
    	 
    	 
    	 
    	 if(secureHeader.isDisplayed()) {
 			System.out.println("Secure Checkout page is displayed");
 			return true;
 		}
 		else {
 			System.out.println("Secure Checkout Page is not displayed");
 			return false;
 		}
 	
    	 
    	 
     }
     public SecurePage meltCheckoutUPI(String meltNameOne) {
    	 meltsAddOne.click();
    	 waitForElement(meltsBasketItemLocator);
    	 String basketItem=meltsBasketItem.getText();
    	 if(basketItem.contentEquals(meltNameOne)) {
    		 System.out.println("Melt Name "+ meltNameOne+" is present in the basket");
    	 }
    	 else {
    		 System.out.println("Melt Name "+ meltNameOne+" is not present in the basket");
    	 }
    	 checkoutButton.click();
    	 waitForElement(continueShoppingLocator);
    	 continueShopping.click();
    	 meltsAddTwo.click();
    	 checkoutButton.click();
    	 return new SecurePage(driver);
    	 
     }
	}
	
	


