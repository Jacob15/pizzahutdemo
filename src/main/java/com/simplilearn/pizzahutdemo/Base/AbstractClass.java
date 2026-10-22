package com.simplilearn.pizzahutdemo.Base;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



import org.openqa.selenium.support.ui.Select;

public class AbstractClass {
	
	WebDriver driver;
	protected WebDriverWait wait;
	
	public AbstractClass(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		PageFactory.initElements(driver, this);
		
	}
	@FindBy(css = "#react-burger-menu-btn")
	WebElement hamburgerMenuButton;
	
	@FindBy(css = "input")
	WebElement locationPopupInput;
	
	//input[starts-with(@id, 'username_')
	@FindBy(xpath = "//input[starts-with(@aria-activedescendant, 'PlacesAutocomplete__suggestion-')]")
	WebElement locationPopupSuggestionLocator;
	
	
	
	
	By locationPopupSuggestion = By.xpath("//input[starts-with(@aria-activedescendant, 'PlacesAutocomplete__suggestion-')]");
	 
	
	public void waitForElement(By locator) {
		// Implement wait logic here
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(15));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	public WebElement waitForWebElement(By locator) {
	WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
	WebElement element = wait.until(
	    ExpectedConditions.elementToBeClickable(locator)
	);
	return element;
	}
	public void waitForElementClickable(By locator) {
		// Implement wait logic here
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	public void waitForElementtoDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	public void click(WebElement element) {		
		waitForVisibility(element);
		waitForElementToBeClickable(element);
		//Log.step("Click: " + debugName(element));
		element.click();
	}
	public void waitForElementToBeClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	public void waitForVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		
	}
	
	public void safeClick(WebElement element) {
		
		try {
			click(element);
		}catch(ElementClickInterceptedException e){
			
			((JavascriptExecutor) driver).executeScript("argument[0].click();", element);	
		}
	}
	public String[] selectDropdown(WebElement locator) {
	   Select dropdown=new Select(locator);
	   List<WebElement> allOptions=dropdown.getOptions();
	   String[] options= {};
       ArrayList<String> list = new ArrayList<>(Arrays.asList(options));
       
  
	   for(WebElement Option : allOptions)
	   {
		    list.add( Option.getText());
		   
	   }
 	   options=list.toArray(options);
 	   return options;
	}
	public void selectDropdownValue(WebElement locator , String text) {
		   Select dropdown=new Select(locator);
		   dropdown.selectByVisibleText(text);

	}
	public void hamburgerMenuClick() {
		hamburgerMenuButton.click();
	}
	public void locationPopupClick(String location) {
		//locationPopupInput.click();
		//locationPopupInput.sendKeys(location);	
		Actions a = new Actions(driver);
		a.sendKeys(locationPopupInput, location).build().perform();
		waitForElement(locationPopupSuggestion);
		System.out.println("Location suggestion is displayed: " + locationPopupSuggestionLocator.isDisplayed());
		//System.out.println("Location suggestion text: " + locationPopupSuggestionLocator.getAttribute("value"));
		locationPopupSuggestionLocator.sendKeys(Keys.ARROW_DOWN);
		locationPopupSuggestionLocator.sendKeys(Keys.ARROW_DOWN);
		locationPopupSuggestionLocator.sendKeys(Keys.ENTER);
		
		}

}
