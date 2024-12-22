package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC002_AddToCart extends BaseClass{
	@Test(groups = {"regression","master"})
	public void verifyLogin() {
		try {
			logger.info("***** Starting TC002_AddToCartTest  ****");
			
			LoginPage loginpage = new LoginPage(driver);
			logger.info("Providing user details...");
			loginpage.setUsername("standard_user");
			
			loginpage.setPassword("secret_sauce");
			
			loginpage.clickLogin();
			
			HomePage homepage = new HomePage(driver);
			logger.info("Providing user details...");
			homepage.clickAddBtn(1);
			Assert.assertEquals(homepage.readAddBtn(1), "Remove");
			
		}
		catch (Exception e)
		{
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());
		} 
		finally 
		{
			logger.info("***** Finished TC002_AddToCartTest *****");
		}
		
	}
}
