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

public class Test2_BrightHorizons {

	public static void main(String[] args) throws Throwable {

		// 1) Navigate to BH home page:
		// https://www.brighthorizons.com/
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.brighthorizons.com/");

		// Wait for the pop-up and then click the "Accept All" button
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement acceptAllButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
		acceptAllButton.click();

		// 2)Click on Find a Center option (top header)

		driver.findElement(By.linkText("Find a Center")).click();
		Thread.sleep(3000);

		// 3)Verify that newly open page contains: /child-care-locator as a part of its
		// URL

		String currentUrl = driver.getCurrentUrl();
		if (currentUrl.contains("/child-care-locator")) {
			System.out.println("Pass-URL contains /child-care-locator");
		} else {
			System.out.println("Fail-URL does not contain /child-care-locator");
		}

		Thread.sleep(3000);

		// 4)Insert -- New York -- into search box and press Enter

		driver.findElement(By.id("addressInput")).sendKeys("New York");
		Thread.sleep(3000);

		// Handling Auto Suggestion
		List<WebElement> AllSuggestionsList = driver.findElements(By.xpath("(//span[@class='pac-item-query'])"));
		System.out.println("Total Auto Suggestions List :- " + AllSuggestionsList.size());

		for (WebElement e : AllSuggestionsList) {
			System.out.println(e.getText());
		}

		Thread.sleep(3000);

		for (int i = 0; i < AllSuggestionsList.size(); i++) {

			String ExpectedResult = "New York";

			if (AllSuggestionsList.get(i).getText().equalsIgnoreCase(ExpectedResult)) {
				AllSuggestionsList.get(i).click();
				System.out.println(ExpectedResult + " clicked");
				break;
			}
		}

		Thread.sleep(3000);

		// Step 5: Verify the number of found centers (if the number of centers is displayed)
		String Excepted_FirstElement = driver.findElement(By.xpath("//span[@class='resultsNumber']")).getText();
		System.out.println("First Element :- " + Excepted_FirstElement);

		Thread.sleep(3000);

		String Excepted_SecondElement = driver.findElement(By.xpath("//h3[normalize-space()='Bright Horizons at 20 Pine']")).getText();
		System.out.println("Second Element :- " + Excepted_SecondElement);

		// Check if Excepted_FirstElement is in Excepted_SecondElement
		if (Excepted_SecondElement.contains(Excepted_FirstElement)) {
			System.out.println("'" + Excepted_FirstElement + "' is found Second Element in '" + Excepted_SecondElement + "'");

		} else {
			System.out.println("'" + Excepted_FirstElement + "' is not found Second Element in '" + Excepted_SecondElement + "'");
		}

		Thread.sleep(3000);

		// 6)Click on the first center on the list

		driver.findElement(By.xpath("//div[@id='center-results-container']")).click();

		// 7)Verify if center name and address are the same (on the list and on the popup)
		

		String FirstTitleElement = driver.findElement(By.xpath("//h3[normalize-space()='Bright Horizons at TriBeCa']")).getText();
		System.out.println("First Title:- "+FirstTitleElement);		
		String FirstAddressElement = driver.findElement(By.xpath("//span[contains(text(),'129 Hudson Street')]")).getText();
		System.out.println("First Address:- "+FirstAddressElement);

		//
		Thread.sleep(3000);
		
		String Win1=driver.getWindowHandle();	
		
		//driver.findElement(By.xpath("//button[@title='Close']")).click();
		driver.switchTo().window(Win1);	
		System.out.println(Win1);
		System.out.println(driver.getTitle());
		
		Thread.sleep(3000);
		
		String SecondTitleElement = driver.findElement(By.xpath("//span[@class='mapTooltip__headline']")).getText();
		System.out.println("Second Title:- "+SecondTitleElement);		
		String SecondAddressElement = driver.findElement(By.xpath("//div[@class='mapTooltip__address']")).getText();
		System.out.println("Second Address:- "+SecondAddressElement); 
		
		Thread.sleep(3000);
		
			
		if (FirstTitleElement.trim().equals(SecondTitleElement.trim())) 
		{
			if(SecondAddressElement.trim().equals(SecondAddressElement.trim()))
			{			
			System.out.println("center Name/Title and Address Pass.");
			}
			
		}else
		{
			System.out.println("center Name/Title and Address Fail.");
		}
					
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@title='Close']")).click();
		
		driver.close();

	}
}
