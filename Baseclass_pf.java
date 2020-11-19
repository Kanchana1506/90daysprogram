package pepperfry;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

public class Baseclass_pf {

	ChromeDriver driver;
	
	@BeforeMethod
	public void open()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\User\\Desktop\\MAVEN\\PracticeProgram90days\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	public void close()
	{
		driver.quit();
	}
}
