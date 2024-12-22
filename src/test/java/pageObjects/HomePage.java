package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//button[@id='react-burger-menu-btn']") WebElement menu;
	@FindBy(xpath = "//a[@id='logout_sidebar_link']") WebElement logout_btn;
	
	public void clickAddBtn(int id) {
		WebElement btn = driver.findElement(By.xpath("//div[@class='inventory_list']//div[@class = 'inventory_item']["+id+"]//button"));
		btn.click();
	}
	
	public String readAddBtn(int id) {
		WebElement btn = driver.findElement(By.xpath("//div[@class='inventory_list']//div[@class = 'inventory_item']["+id+"]//button"));
		return btn.getText();
	}
	
	public void clickLogOutBtn() {
		menu.click();
		logout_btn.click();
	}
}
