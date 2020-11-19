package nykaa;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class NykaaBaseClass {

	public ChromeDriver driver; 
	
	@BeforeMethod
	public void login()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\MAVEN\\PracticeProgram90days\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@AfterMethod
	public void close()
	{
//		driver.quit();
	}
	
}
