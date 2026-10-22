package com.simplilearn.pizzahutdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.simplilearn.pizzahutdemo.Base.AbstractClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SidesPage  extends AbstractClass{
    WebDriver driver;
	public SidesPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//a[@data-synth='link--cheesy-pocket-2pc-single']")
	WebElement sidesItem;
	@FindBy(xpath="//h2[@class='typography-1 capitalize single-item__title text-center lg:text-left hidden lg:block lg:px-20 lg:py-25']")
	WebElement sidesHeader;
	@FindBy(xpath="//p[contains(@class,'font-light typography-6')]")
	WebElement sidesText;
	@FindBy(xpath="//span[contains(@class,'typography-6')][normalize-space()='Vegetarian']")
	WebElement sidesLabel;
	@FindBy(xpath="//div[contains(@class,'flex font-light mr-10')]//p[contains(@class,'typography-6')]")
	WebElement caloriesText;
	@FindBy(xpath="//span[text()='Add to my basket']")
	WebElement addToMyBasketButton;
	@FindBy(xpath="//button[@data-synth='button--cheesy-pocket-2pc-single--one-tap']")
	WebElement sidesAddOne;
	@FindBy(xpath="//button[@data-synth='button--indi-cheesy-pocket-2pc-single--one-tap']")
	WebElement sidesAddTwo;
	@FindBy(xpath="//div[@data-synth='basket-item-type--side']")
	WebElement sidesBasketItem;
	@FindBy(xpath="//span[text()='Checkout']")
	WebElement checkoutButton;
	@FindBy(xpath="//span[@class='subtotal']")
	WebElement subTotalField;
	@FindBy(xpath="//span[text()='Secure Checkout']")
	WebElement secureHeader;
	@FindBy(xpath="//span[contains(text(),'Continue Shopping')]")
	WebElement continueShopping;
	
	

	
	By sidesItemLocator=By.xpath("//a[@data-synth='link--cheesy-pocket-2pc-single']");
	By sidesHeaderText=By.xpath("//h2[contains(text(),'Cheesy Pocket 2pc')]");
	By sidesBasketItemLocator=By.xpath("//div[@data-synth='basket-item-type--side']");
	By continueShoppingLocator=By.xpath("//span[text()='Continue Shopping']");
	By secureCheckout=By.xpath("//span[text()='Secure Checkout']");

	
	public String selectSides() {
		
		// Implement the logic to select a pizza based on the provided pizzaName
		// For example, you can locate the pizza element using its name and click on it
		waitForElement(sidesItemLocator);
		sidesItem.click();
		waitForElement(sidesHeaderText);
		//waitForElement(pizzaHeaderLocator);
		String meltsName= sidesHeader.getText();
		return meltsName;
		
	}
	public void verifySidesOverlay() {
		waitForElement(sidesItemLocator);
		sidesItem.click();
		waitForElement(sidesHeaderText);
		if(sidesText.isDisplayed()) {
			System.out.println("Sides Text is displayed");
		}
		else {
			System.out.println("Sides Text is not displayed");
		}
		if(sidesLabel.isDisplayed()) {
			System.out.println("Sides Label is displayed");
		}
		else {
			System.out.println("Sides Labell is not displayed");
		}
		if(caloriesText.isDisplayed()) {
			System.out.println("Calories Text is displayed");
		}
		else {
			System.out.println("Calories Text is not displayed");
		}
		if(addToMyBasketButton.isDisplayed()) {
			System.out.println("Add to My Basket Button is displayed");
		}
		else {
			System.out.println("Add to My Basket Button is not displayed");
		}
		
	}
	public Boolean cartCheckoutSides(String sidesNameOne, String sidesNameTwo) {
   	 sidesAddOne.click();
   	 waitForElement(sidesBasketItemLocator);
   	 String basketItem=sidesBasketItem.getText();
   	 if(basketItem.contentEquals(sidesNameOne)) {
   		 System.out.println("Melt Name "+ sidesNameOne+" is present in the basket");
   	 }
   	 else {
   		 System.out.println("Melt Name "+ sidesNameOne+" is not present in the basket");
   	 }
   	 String subTotalValue=subTotalField.getText();
        String[] parts = subTotalValue.split("₹");
        System.out.println(parts[1]);
        try {
   	 double value=Double.parseDouble(parts[1]);
   	 System.out.println(value);
   	 if(value<200.00) {
   		 safeClick(sidesAddTwo);
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
	public SecurePage cartCheckoutSidesUPI() {
		safeClick(sidesAddOne);
		safeClick(checkoutButton);
		waitForElement(continueShoppingLocator);
		safeClick(continueShopping);
		safeClick(sidesAddTwo);
		safeClick(checkoutButton);
		waitForElement(secureCheckout);
		if(secureHeader.isDisplayed()) {
			System.out.println("Secure Checkout page is displayed");
			
		}
		else {
			System.out.println("Secure Checkout Page is not displayed");
			
		}
		return new SecurePage(driver);
		
		

		
	}
   
	

}
