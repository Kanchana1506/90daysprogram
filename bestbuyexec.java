//order an item and verify error msg in sign-in screen 
package bestBuy;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class bestbuyexec extends BBbaseclass {

	static String price_1, price_2;
	
	@Test
	public void bestbuytest() throws Exception
	{
		//1) Launch the app "https://www.bestbuy.com/"
		driver.get("https://www.bestbuy.com/");
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(90));
		
		//select country as UnitedStates
		driver.findElementByXPath("//a[@class='us-link']").click();
		
		//to close 'black friday shopping' pop up
		try 
		{wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//span[text()='Close'])[4]"))).click();}
		catch(Exception e)
		{System.out.println("trying to close popup with another xpath2");
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='widgets-view-email-modal-mount']/div/div/div/div/div/div/div/button"))).click();}
	
		//2) Click on Products
		driver.findElementByXPath("//button[text()='Products']").click();
		
		//3) Click on Cell Phones
		driver.findElementByXPath("//button[text()='Cell Phones']").click();
		
		//4) Click on iPhones
		driver.findElementByXPath("//div[text()='iPhone']").click();
		
		//5) Click on iPhone SE
		driver.findElementByXPath("//a[text()='iPhone SE']").click();

		Thread.sleep(3000);
		
		//6) Click on Unlocked
		try
		{wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[@class='v-box v-p-s carrier-section esg-valign-middle esg-background-white'])[4]"))).click();	}
		catch(Exception e)
		{System.out.println("trying again to unlock with another xpath");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Unlocked']"))).click();		}
		
		Thread.sleep(3000);
		
		//7) Click on Continue
		try
		{wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-track='LP_Continue_iPhone_XS']"))).click();}
		catch(Exception e)
		{System.out.println("trying with another xpath for continue button");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='btn-section v-p-top-l']"))).click();}
		
		Thread.sleep(3000);
		
		//8) Change Internal Memory as 128GB 
		driver.executeScript("scroll(0,500);");
		driver.findElementByXPath("//span[@class='variation-dropdown-tile ']").click();
		driver.findElementByXPath("//span[@aria-label='128GB. Available']").click();		
		
	////https://www.softwaretestingmaterial.com/stale-element-reference-exception-selenium-webdriver/#:~:text=Stale%20Element%20means%20an%20old,then%20the%20StaleElementReferenceException%20is%20thrown  
 
 	//9) Click on "I'll activate service later"
	//10) Get the price (price_1), store it and print it
		Thread.sleep(3000);
		driver.executeScript("scroll(0,700);");

		price_1= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='priceView-hero-price priceView-customer-price']/span[1]"))).getText();
		System.out.println("Price of the item is : " +  price_1);
	
	//11) Click on Continue
	//12) Click on Add to Cart
	try {driver.findElementByXPath("//button[text()='Add to Cart']").click();}
	catch(Exception e)
	{ System.out.println("trying to add to cart with another xpath");
	  driver.findElementByXPath("//button[@class='btn btn-primary btn-lg btn-block btn-leading-ficon add-to-cart-button']").click(); }

	//13) Click on Go to Cart in the pop up
		try { wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='c-portal']/div/div/div/div/div/div/div[1]/div[3]"))).click();}
		catch(Exception e)
		{ System.out.println("trying again to go to cart");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='c-portal']/div/div/div/div/div/div/div[1]/div[3]"))).click();}
	
		Thread.sleep(3000);
		
	//14) Get the Total price (price_2) and print it
		price_2 = driver.findElementByClassName("price-summary__total-value").getText();
		System.out.println("total price in cart/order summary is: " + price_2);
		
	//15) Verify whether both the prices are same
		if(price_1.equals(price_2))
		System.out.println("Both the prices are equal");
		else
		System.out.println("Both prices are not equal");
		
	//16) Click on Checkout
		driver.findElementByXPath("//button[@data-track='Checkout - Top']").click();
		
	//17) Click on Sign In
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@data-track='Sign In']"))).click();
		
		System.out.println("error displayed when sign in is clicked: ");
		
	//18) Verify the error message
		List<WebElement> signinerr = driver.findElementsByXPath("//div[@class='cdi-input cdi-wrapped-text']/div/span");
		for (WebElement eachele : signinerr) {
			String error = eachele.getText();
			System.out.println(error);
}
	}
}