package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@name='user-name']") WebElement username_txt;
	@FindBy(xpath = "//input[@name='password']") WebElement pwd_txt;
	@FindBy(xpath = "//input[@name='login-button']") WebElement login_btn;
	
	public void setUsername(String username) {
		username_txt.clear();
		username_txt.sendKeys(username);
	}
	
	public void setPassword(String pwd) {
		pwd_txt.clear();
		pwd_txt.sendKeys(pwd);
	}
	
	public void clickLogin() {
		login_btn.click();
	}

}
