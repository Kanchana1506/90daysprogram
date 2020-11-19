//placing order for table in pepperfry and get screenshot for order summary
package pepperfry;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class pepperfry extends Baseclass_pf {

	@Test
	public  void execpepperfry() throws Exception
	{
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(180));
		Actions builder= new Actions(driver);
		
		//1) Go to https://www.pepperfry.com/
		driver.get("https://www.pepperfry.com/");
		driver.manage().timeouts().implicitlyWait(6000, TimeUnit.MILLISECONDS);
						
		//2) Mouseover on Furniture and click Office Chairs under Chairs
		WebElement wfh = driver.findElementByXPath("//a[text()='Furniture']");
		builder.moveToElement(wfh).perform();
		driver.findElementByXPath("//a[text()='Office Chairs']").click();
				
		//3) click Executive Chairs
		driver.findElementByXPath("//h5[text()='Executive Chairs']").click();
		
		//4) Change the minimum Height as 50 in under Dimensions
		driver.findElementByXPath("//input[@class='clipFilterDimensionHeightValue']").clear();
		driver.findElementByXPath("//input[@class='clipFilterDimensionHeightValue']").sendKeys("50");
				
		driver.switchTo().frame("webklipper-publisher-widget-container-notification-frame");
		driver.findElementByXPath("//div[@class='close']").click();
		driver.switchTo().defaultContent();
		
		driver.findElementByXPath("//div[@class='popup-box gb-modal reg_modal']/a").click();		
		
		//5) Add "Buff High Back Executive Chair In Black Colour" chair to Wishlist
		driver.findElementByXPath("//a[@data-productname='Buff High Back Executive Chair in Black Colour']").click();
		
		//6) Mouseover on Bedroom and Click Study tables
		WebElement bedele = driver.findElementByXPath("//a[text()='Bedroom']");
		builder.moveToElement(bedele).perform();
		
		driver.findElementByXPath("(//div[text()='Tables'])[3]/ following-sibling ::div/div[3]").click();
				
		//7) Select Spacewood as Brand
		driver.executeScript("scroll(0,500);");
		driver.findElementByXPath("//label[text()='Spacewood']").click();

		Thread.sleep(3000);
		driver.executeScript("scroll(0,900);");
		
		//8) Select Price as 7000 to 8000
		try
		{ wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//label[@for='price7000-8000']"))).click(); }
		catch(Exception e)
		{ 	System.out.println("tryin with another xpath to select required price");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//li[@data-search='7000-8000']/label"))).click();	}
		
		//9) Add "Winner Hutch Table In Rigato Walnut Finish" to Wishlist
		
		try{wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//a[@data-productname='Winner Hutch Table in Rigato Walnut Finish']"))).click();}
		catch(Exception e)
		{ 	System.out.println("trying again to add item to wishlist");
			wait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("(//div[@class='clip-prd-hrt pf-col xs-2'])[1]/a"))).click(); }

		//10) Verify the number of items in Wishlist
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		String wishlist = driver.findElementByXPath("(//span[@class='count_alert'])[2]/parent::div").getText();		
		System.out.println("the number of items in Wishlist: " + wishlist);
		
		//11) Navigate to Wishlist
		driver.findElementByXPath("//a[@data-tooltip='Wishlist']").click();
		
		//12) Move Table only to Cart from Wishlist
		List<WebElement> wishlistele = driver.findElementsByXPath("//div[@class='item_card_wrapper']/div/div/div[2]/p[1]");
		
		for (WebElement eachele : wishlistele) {
			String name= eachele.getText();
			if(name.contains("Table"))
			{System.out.println("moving the table to cart");
			driver.findElementByXPath("(//a[contains(text(),'Winner Hutch Table')]/parent::p)/following-sibling::div/div[1]/a[1]").click();
			}
		}
		
		//13) Click Proceed to Pay Securely
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
		driver.findElementByXPath("//a[text()='Proceed to pay securely ']").click();
		
		Thread.sleep(3000);
		
		//14) Enter Pincode as 600028 in Delivery & Assembly Details and click Go
		driver.findElementById("pin_code").sendKeys("600028");
		driver.findElementById("pin_check").click();
		
		//14) Click Place Order
		try {driver.executeScript("scroll(0,700);");
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@class='ck-proceed-btn-wrap']/a[1]"))).click();}
		catch(Exception e)
		{ 	System.out.println("trying again");
			driver.executeScript("scroll(0,700);");
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElementByXPath("//span[@class='ck-proceed-btn-wrap']"))).click();	}
		
		
		//15) Capture a screenshot by Clicking on Order Summary
		System.out.println("Capturing screenshot of order summary");
		driver.findElementByXPath("(//span[text()='ORDER SUMMARY'])/parent::div").click();
		
		WebElement elesummary = driver.findElementById("ordersummary_accrodian");
		File src = elesummary.getScreenshotAs(OutputType.FILE);
		File dec = new File("./elesnaps/img.png");
		FileUtils.copyFile(src, dec); 
		
		//16) Close the browser
		

}
}