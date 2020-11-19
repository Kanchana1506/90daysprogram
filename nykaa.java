//order an item and verify error mssg at the end 
package nykaa;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class nykaa extends NykaaBaseClass {
	
	@Test
	public void nykaaOrder() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		Actions builder = new Actions(driver);
		
	//1) Go to https://www.nykaa.com/
	driver.get("https://www.nykaa.com/");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	//2) Mouseover on Brands and Mouseover on Popular
	WebElement brand = driver.findElementByXPath("//a[text()='brands']");
	builder.moveToElement(brand).perform();
	
	WebElement popular = driver.findElementByXPath("//a[text()='Popular']");
	builder.moveToElement(popular).perform();
	
	//3) Click L'Oreal Paris
	driver.findElementByXPath("//ul[@class='l-vertical-list']/li[5]").click();
		
	//4) Go to the newly opened window and check the title contains L'Oreal Paris
	Set <String> windowhandles1 = driver.getWindowHandles();
	List<String> listhandles1 = new ArrayList<String>(windowhandles1);
	String newwindow1 = listhandles1.get(1);
	driver.switchTo().window(newwindow1);
	
	//since it is new window
	Thread.sleep(3000);
	
	//checking the window title
	String title = driver.getTitle();
	System.out.println("Title of the page is : " + title);
	if(title.contains("Paris"))
		System.out.println("title contains L'Oreal Paris");
	else
		System.out.println("title is not correct");
	
	//for scrolling
	driver.executeScript("scroll(0,500);");
	
	//5) Click sort By and select customer top rated
	driver.findElementByXPath("//span[text()='Sort By : ']").click();
	driver.findElementByXPath("//span[text()='customer top rated']").click();
	
	Thread.sleep(3000);
	
	
	//6) Click Category and click Shampoo
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Category']"))).click();
	driver.executeScript("scroll(0,800);");
	Thread.sleep(1000);
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//label[@for='chk_Shampoo_undefined'])[1]"))).click();
	
	String optionselectedname = driver.findElementByXPath("(//label[@for='chk_Shampoo_undefined'])[1]").getText();
	System.out.println("option selected in category is : "+optionselectedname);
	
	//7) check whether the Filter is applied with Shampoo
	List<WebElement> filter = driver.findElementsByXPath("//ul[@class='pull-left applied-filter-lists']/li[1]");
				
	for (WebElement eachele : filter)
	{
		String name1 = eachele.getText();
		System.out.println(name1);
		if(name1.contains(optionselectedname))
		{System.out.println(name1 + " filter is applied..which is correct");}
		else
		{System.out.println("filter is not correct");}
	}
	
	//8) Click the first listed item
	driver.findElementByXPath("//div[@class='row clearfix']/div[1]").click();
	
	//9) Go to the new window and click add to bag and print the success message
	Set <String> windowhandles2 = driver.getWindowHandles();
	List<String> listhandles2 = new ArrayList<String>(windowhandles2);
		String newwindow2 = listhandles2.get(2);
		driver.switchTo().window(newwindow2);
	
	driver.findElementByXPath("//div[@class='pull-left']/div/button").click();
	driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		
	String productHasBeen = driver.findElementByXPath("(//div[@class='mm-text']/span)[2]").getText();
	System.out.println("Success message is : " + productHasBeen);
	
	Thread.sleep(3000);

	//10) Go to Shopping Bag and click Proceed
		try 
		{driver.findElementByXPath("//div[@class='AddBagIcon']").click(); }
		catch (Exception e)
		{ System.out.println("exception: trying with another xpath");
			driver.findElementByXPath("//div[@class='cursor-hand arrowup-tooltip']/div").click(); }
	
	//below to overcome ": element click intercepted: Element is not clickable at point error" 
	WebElement proceedele = driver.findElementByXPath("//span[text()='Proceed']");
	JavascriptExecutor executor1 = (JavascriptExecutor)driver;
	executor1.executeScript("arguments[0].click();", proceedele);

	//11) Click next and print the error message
	wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//button[@type='submit']"))).click();
	
	try 
	{	wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//span[text()='This field is required']"))); 	}
	catch (NoSuchElementException e)
	{ 	System.out.println("unable to locate exception: trying with another xpath1");
			WebDriverWait wait1= new WebDriverWait(driver, Duration.ofMillis(6000));
			wait1.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//i[@class='error-icon']/following-sibling::span[1]")));
	}
	catch (Exception e) {
		System.out.println("unable to locate exception: trying with another xpath2");
		WebDriverWait wait1= new WebDriverWait(driver, Duration.ofMillis(6000));
		wait1.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[contains(@class,'form-field ')]//span)[2]")));
	}
		
		driver.manage().timeouts().implicitlyWait(16000, TimeUnit.MILLISECONDS);
		try
		{
		String error = driver.findElementByXPath("(//span[text()='This field is required']").getText();
		System.out.println("Error dispalyed in check out screen is : " + error);
		}
		catch(Exception e)
		{
			System.out.println("unable to locate element.. trying with another xpath2");
			String error = driver.findElementByXPath("(//div[contains(@class,'form-field ')]//span)[2]").getText();
			System.out.println("Error dispalyed in check out screen is : " + error);
		}
		
	//12) Close the browser
	}
}
