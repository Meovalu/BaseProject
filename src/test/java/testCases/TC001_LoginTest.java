package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC001_LoginTest extends BaseClass{
	//Info is usually default
	@Test(groups = {"sanity","master"})
	public void verifyLogin() {
		try {
			logger.info("***** Starting TC001_LoginTest  ****");
			
			LoginPage loginpage = new LoginPage(driver);
			logger.info("Providing user details...");
			loginpage.setUsername("standard_user");
			
			loginpage.setPassword("secret_sauce");
			
			loginpage.clickLogin();
			logger.info("Validating result...");
			Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
			
		}
		catch (Exception e)
		{
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		} 
		finally 
		{
			logger.info("***** Finished TC001_LoginTest *****");
		}
		
	}
}
