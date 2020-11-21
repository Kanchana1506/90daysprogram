package honda;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class hondaclass {

	public static void main(String[] args) throws Exception {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\MAVEN\\PracticeProgram90days\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		Actions builder = new Actions(driver);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		driver.manage().window().maximize();
		
		//1) Go to https://www.honda2wheelersindia.com/
		driver.get("https://www.honda2wheelersindia.com/");
		Thread.sleep(3000);
		
		//to close advertisment pop up
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='modal-content']/div/button"))).click();
		Thread.sleep(3000);
			
			//2) Click on scooters and click dio
		try
		{wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("link_Scooter"))).click();}
		catch(Exception e) 
		{
			System.out.println("trying again to find scooter with another xpath");
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cont-nivel-1 cont-nivel-2 col-md-offset-3']/a[2]"))).click();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='scooter']/div[1]/div/div[1]"))).click();
	
		Thread.sleep(3000);
		
			//3) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='specifications-box text-center wow bounceInRight']/ul/li[2]")));
		WebElement dioengine = driver.findElementByXPath("//div[@class='specifications-box text-center wow bounceInRight']/ul/li[2]");
		builder.moveToElement(dioengine).perform();
		
		
		//4) Get Displacement value
		String diodisplacement = driver.findElementByXPath("//div[@class='engine part-2 axx']/ul/li[3]/span[2]").getText();
		//to get only numeric values
		String diodisp1= diodisplacement.replaceAll("[^0-9]", "");
		//to get only first three digits
		String diodisp2= diodisp1.substring(0, 3);
		//to convert string to integer
		int a = Integer.parseInt(diodisp2);
		//System.out.println(diodisp2);
		System.out.println("Displacement value of Dio is : " + diodisplacement);
		
			//5) Go to Scooters and click Activa 125
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='cont-nivel-1 cont-nivel-2 col-md-offset-3']/a[2]"))).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='scooter']/div[1]/div/div[4]"))).click();
		Thread.sleep(3000);
			//6) Click on Specifications and mouseover on ENGINE
		driver.findElementByXPath("//a[text()='Specifications']").click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='specifications-box col-md-4 col-sm-4 wow bounceInLeft']/ul/li[4]")));
		WebElement activaengine = driver.findElementByXPath("//div[@class='specifications-box col-md-4 col-sm-4 wow bounceInLeft']/ul/li[4]");
		builder.moveToElement(activaengine).perform();
		
		 	//7) Get Displacement value
		String activadisplacement = driver.findElementByXPath("//div[@class='engine part-4 axx']/ul/li[3]/span[2]").getText();
		//to get only numeric value
		String activadisp1 = activadisplacement.replaceAll("[^0-9]", "");
		//to get first three numbers
		String activadisp2 = activadisp1.substring(0, 3);
		//to convert string to integer
		int b = Integer.parseInt(activadisp2);
		//System.out.println(activadisp2);
		System.out.println("Displacement value of Activa125 is : " + activadisplacement);
		
			//8) Compare Displacement of Dio and Activa 125 and print the Scooter name having better Displacement.
		if(a > b)
		{
			System.out.println("Dio has better displacement");
		}
		else
		{
			System.out.println("Activa 125 has better displacement");
		}

		Thread.sleep(3000);
		
			//9) Click FAQ from Menu
		driver.findElementByXPath("//ul[@class='nav navbar-nav']/li[10]").click();
		
			//10) Click Activa 125 BS-VI under Browse By Product
		driver.executeScript("scroll(0,700);");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='force-overflow'])[2]/a[13]"))).click();
		//driver.findElementByXPath("(//div[@class='force-overflow'])[2]/a[13]").click();
		
		
			//11) Click  Vehicle Price
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='nav nav-pills tabb-design  ']/li[6]/a/i"))).click();
	
		Thread.sleep(3000);
			//12) Make sure Activa 125 BS-VI selected and click submit
			WebElement eledropdwn = driver.findElementById("ModelID6");
			Select eleselect = new Select(eledropdwn);
			WebElement frstoption = eleselect.getFirstSelectedOption();
			String name=frstoption.getText();
			
			//dropdown.getFirstSelectedOption();
			//String valueselected = option. getText();
			if(name.contains("Activa 125"))
			{System.out.println("option selected is :" + name + " .which is correct");}
			else
			{System.out.println("option selected is :" + name + " .which is incorrect");}
			
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit6"))).click();
			
			//13) click the price link
			Thread.sleep(3000);
			driver.findElementByXPath("//table[@id='tblPriceMasterFilters']/tbody/tr/td[3]").click();
			
			Thread.sleep(3000);
			
			//14) Go to the new Window and select the state as Tamil Nadu and  city as Chennai
			Set <String> windowhandles = driver.getWindowHandles();
			List<String> listhandles = new ArrayList<String>(windowhandles);
			String newwindow = listhandles.get(1);
			driver.switchTo().window(newwindow);

			
			WebElement elestate = driver.findElementById("StateID");
			//WebElement elestate = driver.findElementByXPath("//select[@id='StateID']");
			Select state = new Select(elestate);
			state.selectByVisibleText("Tamil Nadu");
			
			Thread.sleep(3000);
			
			WebElement elecity = driver.findElementById("CityID");
			Select city = new Select(elecity);
			city.selectByVisibleText("Chennai");
			
			//15) Click Search
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='col-md-4']/button[1]"))).click();
			
			Thread.sleep(3000);
			System.out.println("Details of Model and their Prices: ");
			//16) Print all the 3 models and their prices
	List<WebElement> row = driver.findElementsByXPath("//table[@id='gvshow']/tbody/tr");
			int size = row.size();
			//System.out.println(size);
			
			for (int i = 1; i <=size; i++) {
				List<WebElement> cell = driver.findElementsByXPath("//table[@id='gvshow']/tbody/tr["+i+"]/td");
				int size2 = cell.size();
				//System.out.println(size2);
						for (int j = 1; j <= size2; j++) {
							String text = driver.findElementByXPath("//table[@id='gvshow']/tbody/tr["+i+"]/td["+j+"]").getText();
							System.out.println(text);
						}
			}
					
			//17) Close all the Windows
			driver.quit();

	}
}
