package com.simplilearn.pizzahutdemo.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
	public WebDriver driver;
	
	@BeforeMethod(alwaysRun = true)
	@Parameters({"url", "browser"})
	public void setup(@Optional String url,@Optional String browser) throws IOException {	
		Properties prop = new Properties();
		//C:\Users\Jacob.A\eclipse-workspace\saucedemo\src\test\java\com\simplilearn\saucedemo\resources\config.properties
		FileInputStream fis=new FileInputStream("C://Users//Jacob.A//eclipse-workspace//pizzahutdemo//src//test//java//com//simplilearn//pizzahutdemo//resources//config.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") :prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			
		//chrome
		// Setup code for initializing WebDriver, etc.
		/*
		 * ChromeOptions options = new ChromeOptions();
		 * 
		 * // Disable password save and leak detection pop-ups Map<String, Object> prefs
		 * = new HashMap<>(); prefs.put("credentials_enable_service", false);
		 * prefs.put("profile.password_manager_enabled", false);
		 * prefs.put("profile.password_manager_leak_detection", false);
		 * options.setExperimentalOption("prefs", prefs);
		 * 
		 * // Optional: Run headlessly or disable infobar notifications
		 * options.addArguments("--disable-save-password-bubble");
		 */
 
		 //location popup
			Map<String, Object> prefs = new HashMap<>();
	        
	        // Pass '1' to automatically Allow, or '2' to automatically Block
	        prefs.put("profile.default_content_setting_values.geolocation", 2); 
	        
	        // 2. Assign the preferences map to ChromeOptions
	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("prefs", prefs);
	        
	        // Optional arguments often used alongside preferences
	        options.addArguments("--disable-geolocation");
		driver = new ChromeDriver(options);
		}
		else if(browserName.equalsIgnoreCase("firefox")) {
			/*
			 * FirefoxProfile profile = new FirefoxProfile();
			 * 
			 * // Disable the password manager entirely (prevents "Save Password" prompts)
			 * profile.setPreference("signon.rememberSignons", false);
			 * 
			 * // Disable contextual insecure field and leaked/compromised password warnings
			 * profile.setPreference("security.insecure_field_warning.contextual.enabled",
			 * false); profile.setPreference("security.insecure_password.ui.enabled",
			 * false);
			 * 
			 * // 2. Assign the profile to FirefoxOptions FirefoxOptions options = new
			 * FirefoxOptions(); options.setProfile(profile);
			 */
             //location popup
			FirefoxOptions options = new FirefoxOptions();
	        
	        // Disable geolocation popups globally in Firefox
	        options.addPreference("geo.enabled", false); 
						
				        
				        // Disable geolocation popups globally in Firefox
				        
            driver = new FirefoxDriver(options);
		}
		else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		
		
		driver.get(prop.getProperty("url"));
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
		

	}
	public String getScreenshot(String testCaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
		
		
	}
	@AfterMethod(alwaysRun = true)
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
