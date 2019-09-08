package DiscoveryPages;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyVideos {
	
	WebDriver driver;
	JavascriptExecutor js;
	String favShows[]=new String[25];
	
	//Constructor to initialize driver
	public MyVideos(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)this.driver;
	}
	
	@FindBy(xpath="//div[contains(text(),'Favorite Shows')]") 
	WebElement myFavourite;

		
	public void scrollToMyFavorites() { 
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//js.executeScript("arguments[0].scrollIntoView();", myFavourite);
		js.executeScript("window.scrollBy(0,250)");
		System.out.println("scrollToMyFavorites");	
	}
	
	public String[] verifyFavShow() {
		//List<WebElement> favShowList = favList.findElements(By.className("carousel-tile-wrapper carousel__tileWrapper"));
		List<WebElement> favShowList = driver.findElements(By.xpath("//section[@class='layout-section FavoriteShowsCarousel layoutSection__main ']//div[@class='carousel-tile-wrapper carousel__tileWrapper']"));
		//System.out.println(favShowList.size());
		
		for(int i=1, j=0;i<=favShowList.size();i++) {
			String str = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/main[1]/div[1]/div[1]/section[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div["+i+"]/section[1]/div[3]/a[1]")).getAttribute("href");
			if(str.contains("apollo")) {
				favShows[j]=str;
				j++;
			}
			//System.out.println(str);
		}
		
		return favShows;
	}
	
}
