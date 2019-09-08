package TestCases;

import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import DiscoveryPages.HomePage;
import DiscoveryPages.MyVideos;
import DiscoveryPages.TvShowsPage;
import Utility.BrowserFactory;

public class ApolloTestCase {
	
	WebDriver driver;
	
	@BeforeTest
	public void openDiscoveryPage() {
		this.driver = BrowserFactory.startBrowser("chrome","https://go.discovery.com/");		
	}

	@Test
	public void checkApolloShows(){
		HomePage home=PageFactory.initElements(driver, HomePage.class);//returns object
		TvShowsPage shows=PageFactory.initElements(driver,TvShowsPage.class);
		MyVideos myVideos=PageFactory.initElements(driver,MyVideos.class);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		home.shows();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		home.seeAllShows();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String[] apolloShowList = shows.apolloShows();
		shows.menu();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		shows.myVideos();
		myVideos.scrollToMyFavorites();
		String[] favShowList = myVideos.verifyFavShow();
		
		if(Arrays.equals(apolloShowList, favShowList)) {
			System.out.println("Apollo shows present in Favorite Shows List");
			for(int i=0; i<favShowList.length && apolloShowList[i]!=null && favShowList[i]!=null ;i++) {
				System.out.println("Apollo show in see all list : "+apolloShowList[i]);
				System.out.println("Apollo show in favorite show list : "+favShowList[i]);
			}
		}
		else {
			System.out.println("Apollo shows not present in Favorite Shows List");
		}
	}
	
	@AfterTest
	public void quitBrowser() {
		driver.quit();
	}

}

