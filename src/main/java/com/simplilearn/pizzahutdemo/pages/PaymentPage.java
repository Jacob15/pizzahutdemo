package com.simplilearn.pizzahutdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.simplilearn.pizzahutdemo.Base.AbstractClass;

public class PaymentPage extends AbstractClass{
	WebDriver driver;
	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath="//h3[text()='UPI QR']")
	WebElement upiText;
	
	@FindBy(xpath="//h3[text()='Add a new card']")
	WebElement cardText;
	
	@FindBy(xpath="//h3[text()='All Wallet Options']")
	WebElement walletText;
	
	@FindBy(xpath="//h3[text()='Suggested Banks']")
	WebElement netbankingText;
	
	@FindBy(xpath="//iframe[@class='razorpay-checkout-frame']")
	WebElement iframePopup;
	
	@FindBy(xpath="//button[@title='Close Checkout']")
	WebElement closeButton;
	
	@FindBy(xpath="//button[@data-testid='confirm-positive']")
	WebElement yesExit;
	
	By upiTextSelector = By.xpath("//h3[text()='UPI QR']");
	
    By cardTextSelector=  By.xpath("//h3[text()='Add a new card']");
    
    By walletTextSelector=By.xpath("//h3[text()='All Wallet Options']");
    
    By netbankingTextSelector=By.xpath("//h3[text()='Suggested Banks']");
    
    By yesExitSelector= By.xpath("//button[@data-testid='confirm-positive']");
	

	public String upiVerification() {
		driver.switchTo().frame(iframePopup);
		waitForElement(upiTextSelector);
		String upiheader=upiText.getText();
		return upiheader;
	}
	
	public String cardsVerification() {
		driver.switchTo().frame(iframePopup);
		waitForElement(cardTextSelector);
		String cardsLabel=cardText.getText();
		
		return cardsLabel;
		
		
	}
	public void closePopup() {
		closeButton.click();
		waitForElement(yesExitSelector);
		yesExit.click();
	}
	public String walletVerification() {
		driver.switchTo().frame(iframePopup);		
		waitForElement(walletTextSelector);
		String walletLabel=walletText.getText();
		return walletLabel;
	}
	
	public String netbankingVerification() {
        driver.switchTo().frame(iframePopup);		
		waitForElement(netbankingTextSelector);
		String netbankingLabel=netbankingText.getText();
		return netbankingLabel;
	}
	

}
