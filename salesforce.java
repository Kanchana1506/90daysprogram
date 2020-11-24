package salesforce;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class salesforce {

	public static void main(String[] args) throws Exception
	{
		//email: "samdavid@testleaf.com"
		//password: samchennai92
	
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\MAVEN\\PracticeProgram90days\\drivers\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(90));
		
		//Launch Salesforce application "https://login.salesforce.com/"
		driver.get("https://login.salesforce.com/");
		Thread.sleep(3000);
		
		//enter the login credentials
		driver.findElementById("username").sendKeys("hari.radhakrishnan@testleaf.com");
		driver.findElementById("password").sendKeys("India$123");
		
		//login into the application
		driver.findElementById("Login").click();
		Thread.sleep(3000);
		
		//Click on Create
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Create']"))).click();
		
		//Click on User
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-id='userCreateMenuItem']"))).click();
		Thread.sleep(5000);
		
		driver.switchTo().frame(0);
		Thread.sleep(3000);
		
		//Fill the mandatory fields
		driver.findElementById("name_lastName").sendKeys("ktest");
		//driver.findElementById("Alias").sendKeys("test");
		driver.findElementById("Email").sendKeys("kbatctest1@abc.com");
		//driver.findElementById("Username").sendKeys("Jen");
		//driver.findElementById("CommunityNickname").sendKeys("Ben");
		
		WebElement elerole = driver.findElementById("role");
		Select item = new Select(elerole);
		item.selectByVisibleText("Channel Sales Team");
		
		WebElement elelicense = driver.findElementById("user_license_id");
		Select item2 = new Select(elelicense);
		item2.selectByVisibleText("Work.com Only");
		
		
		//Fill in Delegated Approver with whatever value available
		driver.findElementById("DelegatedApprover_lkwgt").click();
		driver.switchTo().defaultContent();
		
		//for window
		Set<String> windowhandles = driver.getWindowHandles();
		List<String> listhandles = new ArrayList<String>(windowhandles);
		
	
		//for loop is used to overcome error "Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 1, Size: 1"
		for (int i = 1; i < listhandles.size(); i++) {
			//System.out.println("count " + listhandles);
			String newwindow = listhandles.get(i);
				driver.switchTo().window(newwindow);	
		}
		
		Thread.sleep(6000);	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//frameset/frame[2]")));
		WebElement approverframe = driver.findElementByXPath("//frameset/frame[2]");
		driver.switchTo().frame(approverframe);
      	Thread.sleep(5000);
		 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class= ' dataCell '])[1]"))).click();
		
		Thread.sleep(3000);
		
		driver.switchTo().window(listhandles.get(0));
		driver.switchTo().frame(0);
		
		//Fill in Manager
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Manager_lkwgt"))).click();
		driver.switchTo().defaultContent();
		
		//for window
		Set<String> windowhandles1 = driver.getWindowHandles();
		List<String> listhandles1 = new ArrayList<String>(windowhandles1);
		String newwindow1 = listhandles1.get(1);
		driver.switchTo().window(newwindow1);
		
		WebElement mngrframe = driver.findElementById("resultsFrame");
		driver.switchTo().frame(mngrframe);
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//a[@class= ' dataCell '])[1]"))).click();
		
		driver.switchTo().window(listhandles.get(0));
		driver.switchTo().frame(0);
		
	//Click on save
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@name='save'])[2]"))).click();
		Thread.sleep(6000);
		
	//Verify the record created with email
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//iframe[@title='User: ktest ~ Salesforce - Developer Edition']")));
	WebElement emailframe = driver.findElementByXPath("//iframe[@title='User: ktest ~ Salesforce - Developer Edition']");	
	driver.switchTo().frame(emailframe);
	
	String eleemail = driver.findElementByXPath("//table[@class='detailList']/tbody/tr[3]/td[2]/a").getText();
	System.out.println("Record is created with email as :" + eleemail);
}
}