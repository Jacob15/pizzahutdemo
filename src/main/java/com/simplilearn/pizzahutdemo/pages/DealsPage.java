package com.simplilearn.pizzahutdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.simplilearn.pizzahutdemo.Base.AbstractClass;

public class DealsPage extends AbstractClass {
	WebDriver driver;
	
	public DealsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "(//button[@class='sc-AxheI bqJhFB'])[1]")
	WebElement customiseDealsButton;
	
	@FindBy(xpath="//div[@data-synth='notification']")
	WebElement dealsAddedNotification;
	
	@FindBy(xpath="//button[@data-synth='discount-enabled-modal']")
	WebElement discountActivatedButton;
	
	@FindBy(xpath="//a[@title='Click to make changes']")
	WebElement discountActivatedLink;
	
	@FindBy(css="div[class='bg-yellow text-black text-10 p-5 mr-5 rounded']")
	WebElement discountActivatedText;
	
	@FindBy(css="button[data-synth='basket-item-remove--undefined']")
	WebElement discountActivatedRemoveButton;
	
	@FindBy(css="a[data-synth='link--pizzas--side']")
	WebElement pizzaPageSelector;
	
	@FindBy(xpath="//a[@data-synth='link--melts--side']")
	WebElement meltsPageSelector;
	
	@FindBy(xpath="//a[@data-synth='link--sides--side']//span[contains(text(),'Sides')]")
	WebElement sidesPageSelector;
	//a[@data-synth='link--sides--side']//span[contains(text(),'Sides')]
	//a[@class='menu-link pb-10 capitalize text-white bold inline-block']//span[contains(text(),'Melts')]
	
	By dealsAddedNotificationLocator = By.xpath("(//button[@class='sc-AxheI bqJhFB'])[1]");
	By discountLocator = By.xpath("//span[text()='Discount Activated']");
	By discountActivatedRemoveButtonLocator = By.cssSelector("button[data-synth='basket-item-remove--undefined']");
	By meltsPageLocator=By.xpath("//a[@class='menu-link pb-10 capitalize text-white bold inline-block']//span[contains(text(),'Melts')]");
	
	public Boolean verifyDealsPage() {
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("deals")) {
			System.out.println("Successfully navigated to Deals page.");
			return true;
		} else {
			System.out.println("Failed to navigate to Deals page. Current URL: " + currentUrl);
			return false;
		}
	}
	public Boolean VerifyPizzaPage() {
		waitForElement(dealsAddedNotificationLocator);
		customiseDealsButton.click();
		waitForElement(discountLocator);
		discountActivatedButton.click();
		
		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("pizza")) {
			System.out.println("Successfully navigated to Pizza page.");
			return true;
		} else {
			System.out.println("Failed to navigate to Pizza page. Current URL: " + currentUrl);
			System.out.println("Navigated");
			return false;
		}
	}
	public String dealsaddedtext() {
		waitForElement(dealsAddedNotificationLocator);
		customiseDealsButton.click();
		waitForElement(discountLocator);
		discountActivatedButton.click();
		
		//driver.switchTo().alert().accept();
		String notificationText = dealsAddedNotification.isDisplayed() ? dealsAddedNotification.getText() : "Notification not displayed";
        return notificationText;		
	}
	public String dealsaddedtextBasket() {
		
		waitForElement(dealsAddedNotificationLocator);
		customiseDealsButton.click();
		waitForElement(discountLocator);
		discountActivatedButton.click();
		if(discountActivatedLink.isDisplayed()) {
			System.out.println("Deals added notification is displayed.");
		}
		else {
			System.out.println("Deals added notification is not displayed.");
		}
		
		
		String notificationText = discountActivatedText.isDisplayed() ? discountActivatedText.getText() : "Notification not displayed";
		System.out.println("Deals added notification text: " + notificationText);
		return notificationText;
	}
	
	public Boolean dealsAddedtextBasketRemoval() {
		waitForElement(dealsAddedNotificationLocator);
		customiseDealsButton.click();		
		waitForElement(discountLocator);
		discountActivatedButton.click();
		//waitForElementClickable(discountActivatedRemoveButtonLocator);
		
		discountActivatedRemoveButton.click();
		waitForElementtoDisappear(discountActivatedRemoveButtonLocator);
		boolean isElementPresent = true;
		try {
		    isElementPresent = discountActivatedText.isDisplayed();
		    return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
		    isElementPresent = false;
		    return false;
		}
		
			
	}
	public PizzaPage navigateToPizzaPage() {
		pizzaPageSelector.click();
		return new PizzaPage(driver);
		
	}
	public MeltsPage navigateToMeltsPage() {
		//waitForElement(meltsPageLocator);
		/*
		 * WebElement element=waitForWebElement(meltsPageLocator); element.click();
		 */
		safeClick(meltsPageSelector);
		return new MeltsPage(driver);
		
	}
	public SidesPage navigateToSidesPage() {
		safeClick(sidesPageSelector);
		return new SidesPage(driver);
		
	}

}
