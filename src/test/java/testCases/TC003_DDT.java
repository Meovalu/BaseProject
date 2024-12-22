package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_DDT extends BaseClass{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups = "datadriven")// getting data provider from different class
	public void verify_loginDDT(String username, String pwd, String exp) throws InterruptedException
	{
		logger.info("***** stating TC_003_LoginDDT ******");

		try {
			LoginPage lp = new LoginPage(driver);
			lp.setUsername(username);
			lp.setPassword(pwd);
			lp.clickLogin();
			HomePage hp = new HomePage(driver);
			boolean targetPage = driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html");
			
			if(exp.equalsIgnoreCase("Valid")){
				if(targetPage==true)
				{			
					hp.clickLogOutBtn();
					Assert.assertTrue(true);

				}
				else
				{
					Assert.assertTrue(false);
				}
			}

			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==true)
				{
					hp.clickLogOutBtn();
					Assert.assertTrue(false);

				}
				else
				{
					Assert.assertTrue(true);
				}
			}

		}
		catch(Exception e){
			Assert.fail();
		}
		Thread.sleep(3000);
		logger.info("***** Finished TC_003_LoginDDT ******");

	}
}
