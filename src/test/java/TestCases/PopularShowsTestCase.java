package TestCases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DiscoveryPages.HomePage;
import DiscoveryPages.TvShowsPage;
import Utility.BrowserFactory;

public class PopularShowsTestCase {
WebDriver driver;
	
	@BeforeTest
	public void openDiscoveryPage() {
		this.driver = BrowserFactory.startBrowser("chrome","https://go.discovery.com/");		
	}

	@Test
	public void verifyPopularShows() throws IOException {
		HomePage home=PageFactory.initElements(driver, HomePage.class);
		TvShowsPage shows=PageFactory.initElements(driver,TvShowsPage.class);
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Step-1 Go To Popular Shows
		home.scrollToPopularShows();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//Step-2 Go to the last Video of Popular Shows
		home.goToLastPopularShow();
		
		//Step-3 Click on Explore the show
		home.exploreTheShow();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Step-4 Click on Show More button 2-3 times
		shows.scrollToShowMore();
		
		//Step-5 Get All the episodes of that show and write the duration of each episode in a file
		shows.getEpisodeList();
	}
	
	@AfterTest
	public void quitBrowser() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.quit();
	}


}
