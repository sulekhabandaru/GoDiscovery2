package DiscoveryPages;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class TvShowsPage {
	
	WebDriver driver;
	JavascriptExecutor js; 
	Actions actions;
	
	public TvShowsPage(WebDriver driver){
		this.driver = driver;
		this.js= (JavascriptExecutor) this.driver;
		this.actions = new Actions(this.driver);
		
	}
	
	String favApolloshowList[] = new String[25];
	String unfavApolloshowList[] = new String[25];
	

	@FindBy(xpath="//*[@class='dscHeaderMain__iconMenu']")
	WebElement menu;
	
	@FindBy(xpath="//span[contains(text(),'My Videos')]")
	WebElement myVideos;
	
	@FindBy(xpath="//div[@class='allShowsGrid__tilesList']")
	WebElement tilesList;
	
	@FindBy(xpath="//button[@class='episodeList__showMoreButton']")	 
	WebElement showMore;
	
	@FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/section[1]/div[1]/div[1]/div[2]/div[1]/span[1]/i[1]")
	WebElement favIcon;
	
	@FindBy(xpath="//ul[@class='episodeList__list']")
	WebElement episodeList;
	
	@FindBy(xpath="//h2[contains(@class,'episodeListHeader__contentHeading')]")
	WebElement fullEpisode;
	
	@FindBy(xpath="//main[@class='allShowsLayout__container']//div[@class='allShowsGrid__tile showTileAnimation ' or @class= 'allShowsGrid__tile ']")
	List<WebElement> noOfShowTile;
	
	public String[] apolloShows(){
		
		WebElement showGrid;
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//List<WebElement> noOfShowTile = driver.findElements(By.xpath("//main[@class='allShowsLayout__container']//div[@class='allShowsGrid__tile showTileAnimation ' or @class= 'allShowsGrid__tile ']"));
		//System.out.println(noOfShowTile.size());
		
		for(int i=1,j=0,k=0;i<26;i++) {
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(i==3) {
				i++;
			}
			showGrid = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/div[3]/main[1]/section[1]/div[1]/div[2]/div["+i+"]/div[1]/a[1]"));
			String showTitle=showGrid.getAttribute("href");
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			if(showTitle.contains("apollo")) {
				//System.out.println(showTitle);
				showGrid.click();
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				String iconStatus = favIcon.getAttribute("class");
				//System.out.println(iconStatus);
				if(iconStatus.contains("plus")) {
					favIcon.click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					favApolloshowList[j]=showTitle;
					j++;
				}
				else {
					favIcon.click();
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					unfavApolloshowList[k]=showTitle;
					k++;
				}
				//driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
				driver.navigate().back();
				//System.out.println("navigated back");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
		}
		
		System.out.println("List of Apollo Shows added to favorites list : ");
		for(int j=0;j<favApolloshowList.length;j++) {
			if(favApolloshowList[j]==null) {
				break;
			}
				
			System.out.println(favApolloshowList[j]);
		}
		
		System.out.println("List of Apollo Shows added to unfavorites list : ");
		for(int j=0;j<unfavApolloshowList.length;j++) {
			if(unfavApolloshowList[j]==null)
				break;
			System.out.println(unfavApolloshowList[j]);
		}
		
		return favApolloshowList;
	}
	
	public void menu() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		js.executeScript("window.scrollTo(0,0)");
		menu.click();
		System.out.println("menu");
	}
	
	public void myVideos() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		myVideos.click();
		System.out.println("myVideos");
	}
		
	public void scrollToShowMore() {
		
		for(int i=0;i<2;i++) {
			
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			
			js.executeScript("window.scrollBy(0,100)");
			actions.moveToElement(showMore).perform();
			
			showMore.click();
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}	
	}
	
	public void getEpisodeList() throws IOException {
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		js.executeScript("window.scrollTo(0,0)");
		js.executeScript("arguments[0].scrollIntoView();", fullEpisode);

		List<WebElement> episodes = episodeList.findElements(By.tagName("li"));
		
		File file = createNewFile();
		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		
		//System.out.println(links.size());
		
		for(int i=1;i<episodes.size();i++) {
			
			WebElement episode1=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/section[2]/div[1]/ul[1]/li["+i+"]"));
			actions.moveToElement(episode1).perform();
			WebElement duration = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/section[2]/div[1]/ul[1]/li["+i+"]/div[1]/div[1]/div[1]/a[1]/div[2]/div[1]/div[1]/p[1]"));
			WebElement episodeNo = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[1]/main[1]/section[2]/div[1]/ul[1]/li["+i+"]/div[1]/div[1]/div[1]/a[1]/div[2]/p[1]"));
			String episodeNoText = episodeNo.getText();
			String durationText = duration.getText();
			js.executeScript("window.scrollTo(0,20)");
			writeToFile(episodeNoText,durationText,file,bw);
		}
		
		bw.close();
	}
	
	public File createNewFile() {
		try {
			
		     File file = new File("Files\\durationFile.txt");
	         boolean fvar = file.createNewFile();
	         
		     if (fvar){
		          System.out.println("File has been created successfully");
		     }
		     else{
		          System.out.println("File already present at the specified location");
		          file.delete();
		          System.out.println("Deleted existing file and created new file");
		          file.createNewFile();
		     }
		     return file;
	    } 
		
		catch (IOException e) {
	    		System.out.println("Exception Occurred:");
		        e.printStackTrace();
		}
		return null;
	}
	
	public void writeToFile(String episodeNo,String duration, File file,BufferedWriter bw ) {
		try {
			bw.write(episodeNo);
			bw.write(" : Duration - ");
			bw.write(duration);
			bw.newLine();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
