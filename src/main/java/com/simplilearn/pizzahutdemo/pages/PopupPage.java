package com.simplilearn.pizzahutdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.simplilearn.pizzahutdemo.Base.AbstractClass;

public class PopupPage extends AbstractClass {
	WebDriver driver;
	public PopupPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public DealsPage navigateToDealsPage(String location) {
		try {
		locationPopupClick(location);
		}
		catch(Exception e){
			 
			e.printStackTrace();
		}
		return new DealsPage(driver);
	}
	

}
