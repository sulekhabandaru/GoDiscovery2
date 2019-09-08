package DiscoveryPages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage {
	
	WebDriver driver;
	Actions actions;
	JavascriptExecutor js; 
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		actions = new Actions(this.driver);
		js = (JavascriptExecutor)this.driver;
	}
	
	//Scenario 1
	
	@FindBy(xpath="//span[contains(text(),'Shows')]") 
	WebElement shows;
	
	@FindBy(xpath="//div[@id='show-drop-desktop']//a[@class='dscShowsDropContent__seeAllShows'][contains(text(),'See All Shows')]") 
	WebElement seeAllShows;
	
	
	//Scenario 2
	
	@FindBy(xpath="//h2[@class='popularShowsCarousel__header']") 
	WebElement popularShows;
	
	@FindBy(xpath="//div[@class='carousel__arrowWrapper popularShowsCarousel__controlsProp']//i[@class='icon-arrow-right']") 
	WebElement rightArrow;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/section[9]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/div[15]/div[1]/a[1]/div[1]/section[2]/div[2]/button[1]") 
	WebElement exploreTheShow;
	
	
	//Scenario 1
	public void shows(){
		shows.click();
		System.out.println("Clicked Shows");
	}
	
	public void seeAllShows() {
		seeAllShows.click();
		System.out.println("Clicked See All Shows");
	}
	
	
	//Scenario 2
	
	public void scrollToPopularShows(){
		js.executeScript("arguments[0].scrollIntoView();", popularShows);	
	}
	
	public void goToLastPopularShow() {
		for(int i=0; i<14;i++) {
			rightArrow.click();
		}
	}
	
	public void exploreTheShow() {
		actions.moveToElement(exploreTheShow).perform();
		exploreTheShow.click();
	}
}
