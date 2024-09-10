package Test1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.messages.types.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Test1_BrightHorizons {

	public static void main(String[] args) throws Throwable {

		// 1) Navigate to BH home page:
		// https://www.brighthorizons.com/
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.brighthorizons.com/");

		// Wait for the pop-up and then click the "Accept All" button
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement acceptAllButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
		acceptAllButton.click();
		
		
		//2)Click on search/loop icon (top, right corner)
		driver.findElement(By.xpath("//a[@href='#subnav-search-desktop-top']//span[@class='icon-search bhc-icon-search-rounded']")).click();
		Thread.sleep(3000);
		
		//3)Verify if search field is visible on the page
		
		
		WebElement searchBox = driver.findElement(By.xpath("//nav[@id='subnav-search-desktop-top']//input[@id='search-field']"));  
		// Verify if the search field is visible
		if (searchBox.isDisplayed()) {
		    System.out.println("Search field is visible on the page.");
		} else {
		    System.out.println("Search field is not visible on the page.");
		}
		Thread.sleep(3000);
		
		
		//4)Type: Employee Education in 2018: Strategies to Watch into the search field and click on Search button	
		
		WebElement Inputdata=driver.findElement(By.xpath("//nav[@id='subnav-search-desktop-top']//input[@id='search-field']"));
		Inputdata.sendKeys("Employee Education in 2018: Strategies to Watch");
		Thread.sleep(3000);
	    driver.findElement(By.xpath("//nav[@id='subnav-search-desktop-top']//button[@type='submit'][normalize-space()='Search']")).click();
		Thread.sleep(3000);
		
		//5)Verify if the first search result is exact match to what you typed into search 
						
		String ExpectedInputdata = "Employee Education in 2018: Strategies to Watch";
		
		Thread.sleep(3000);		
		String ActualOutputdata=driver.findElement(By.xpath("(//div[@class='col'])")).getText();
		System.out.println(ActualOutputdata);
		
		if (ActualOutputdata.contains(ExpectedInputdata)) 
		{		
			System.out.println("Test Case Pass.");
			//System.out.println("'" + ExpectedInputdata + "' is found in '" + ActualOutputdata + "'");
			
		}else
		{
			System.out.println("Test Case Fail.");
			//System.out.println("'" + ExpectedInputdata + "' is found in '" + ActualOutputdata + "'");
		}	
		
		driver.findElement(By.xpath("(//div[@class='col'])[1]")).click();
		driver.close();

	}
}
