package com.simplilearn.pizzahutdemo.pages;

import java.util.Arrays;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.simplilearn.pizzahutdemo.Base.AbstractClass;

public class PizzaPage extends AbstractClass {
	WebDriver driver;
	PizzaPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// Constructor
	}
	
	@FindBy(css = "a[data-synth='link--mexican-mango-habanero-pan-personal']")
	WebElement pizzaItem;
	@FindBy(css="h2[class='typography-1 capitalize single-item__title text-center lg:text-left hidden lg:block lg:px-20 lg:py-25']")
	WebElement pizzaHeader;
	@FindBy(css=".icon-close")
	WebElement pizzaClose;
	@FindBy(xpath="(//select[@class='phdv-dropdown-select bg-grey-lightest p-10 w-full typography-6 bold'])[1]")
	WebElement planDropdown;
	@FindBy(css="button[data-synth='button--mexican-mango-habanero-pan-personal--one-tap']")
	WebElement addPizza;
	@FindBy(css="div[data-synth='basket-item-type--pizza']")
	WebElement cartAddPizza;
	@FindBy(xpath="//span[text()='Checkout']")
	WebElement checkoutButton;
	@FindBy(xpath="//span[text()='Secure Checkout']")
	WebElement secureHeader;
	
	
	
	By pizzaItemLocator = By.cssSelector("a[data-synth='link--mexican-mango-habanero-pan-personal']");
	
	By pizzaHeaderText=By.cssSelector("h2[class='typography-1 capitalize single-item__title text-center lg:text-left hidden lg:block lg:px-20 lg:py-25']");
	
	By secureCheckout=By.xpath("//span[text()='Secure Checkout']");
	
	By checkoutButtonLocator=By.xpath("//a[@data-synth='link--checkout']");
	
	public String selectPizza() {
		// Implement the logic to select a pizza based on the provided pizzaName
		// For example, you can locate the pizza element using its name and click on it
		waitForElement(pizzaItemLocator);
		pizzaItem.click();
		waitForElement(pizzaHeaderText);
		
		

		//waitForElement(pizzaHeaderLocator);
		String pizzaName= pizzaHeader.getText();
		return pizzaName;
		
	}
	public void closePizza() {
		waitForElement(pizzaItemLocator);
		pizzaItem.click();
		waitForElement(pizzaHeaderText);
		pizzaClose.click();
		
	}
	public Boolean dropdownText() {
		String[] options=selectDropdown(planDropdown);
		
			
        boolean hasPersonal = Arrays.stream(options).anyMatch(s ->s.equalsIgnoreCase("LARGE Hand Stretched Crust"));
        boolean hasMedium=Arrays.stream(options).anyMatch(s ->s.equalsIgnoreCase("MEDIUM Hand Stretched Crust"));
        return hasPersonal && hasMedium;
        // Outputs: true
	}
	public void  dropdownSelection() {
		selectDropdownValue(planDropdown, "LARGE Hand Stretched Crust" ) ;
	}
	public String cartCheckoutItem(String Pizzaname) {
		selectDropdownValue(planDropdown, "LARGE Hand Stretched Crust" ) ;
		addPizza.click();
		if(cartAddPizza.getText().equalsIgnoreCase(Pizzaname))
		{
			System.out.println("Added pizza is present in cart");
			
			
		}
		else {
			System.out.println("Added Pizza is not present in cart");
		}
        return cartAddPizza.getText();
		
		
	}
	public  Boolean cartSecurePage(String Pizzaname) {
		selectDropdownValue(planDropdown, "LARGE Hand Stretched Crust" ) ;
		addPizza.click();
		if(cartAddPizza.getText().equalsIgnoreCase(Pizzaname))
		{
			System.out.println("Added pizza is present in cart");
			
			
		}
		else {
			System.out.println("Added Pizza is not present in cart");
		}
	    
		
		checkoutButton.click();
		waitForElement(secureCheckout);
		if(secureHeader.isDisplayed()) {
			System.out.println("Secure Checkout page is displayed");
			return true;
		}
		else {
			System.out.println("Secure Checkout Page is not displayed");
			return false;
		}
		
		
		
		
		
	}
	public SecurePage cartSecurePageUPI(String Pizzaname) {
		selectDropdownValue(planDropdown, "LARGE Hand Stretched Crust" ) ;
		addPizza.click();
		if(cartAddPizza.getText().equalsIgnoreCase(Pizzaname))
		{
			System.out.println("Added pizza is present in cart");
			
			
		}
		else {
			System.out.println("Added Pizza is not present in cart");
		}
	    
		
		checkoutButton.click();
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
	


