package Utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class BrowserFactory {
	
	static WebDriver driver;
	
	public static WebDriver startBrowser(String browserName, String baseUrl) {
		
		if(browserName.equalsIgnoreCase("chrome")){
			String exePath = "BrowserDriver/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", exePath);
			driver = new ChromeDriver();	
		}
		
		else if(browserName.equalsIgnoreCase("firefox")) {
			String exePath = "BrowserDriver/geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", exePath);	
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();

		driver.get(baseUrl);
		
		return driver;
		
		
	}

}
