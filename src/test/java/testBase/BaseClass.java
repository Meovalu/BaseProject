package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public WebDriver driver;
	public Logger logger;
	
	public String captureScreen(WebDriver driver, String name) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;
	}
	
	@BeforeClass(groups = {"sanity","regression","master","datadriven"})
	@Parameters({"browser","os"})
	public void setup(String br, String os, ITestContext context) throws IOException, URISyntaxException {
		
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		
		Properties properties= new Properties();
		properties.load(file);
		
		String url = properties.getProperty("appurl");	
		
		logger = LogManager.getLogger(this.getClass());
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//os
			switch(os.toLowerCase())
			{
			case "windows": capabilities.setPlatform(Platform.WIN10); break;
			case "linux": capabilities.setPlatform(Platform.LINUX); break;
			case "mac": capabilities.setPlatform(Platform.MAC); break;
			default: System.out.println("No matching OS"); return;
			}
			
			//browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default: System.out.println("No matching browser"); return;
			}
			
			driver = new RemoteWebDriver(new URI("http://localhost:4444/wd/hub").toURL(),capabilities);
		}
		
				
		if(properties.getProperty("execution_env").equalsIgnoreCase("local"))
		{

			switch(br.toLowerCase())
			{
			case "chrome" : driver=new ChromeDriver(); break;
			case "edge" : driver=new EdgeDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
			context.setAttribute("WebDriver", driver);
		}
		
		context.setAttribute("WebDriver", driver);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups = {"sanity","regression","master","datadriven"})
	public void teardown() {
		driver.quit();
	}
}
