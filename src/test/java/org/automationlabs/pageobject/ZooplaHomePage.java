package org.automationlabs.pageobject;

import org.automationlabs.core.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZooplaHomePage {

	WebDriver driver;
	GenericFunctions generic;
	WebDriverWait wait;
	
	public ZooplaHomePage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		wait = new WebDriverWait(driver, 10);
	}
	
	By searchLocation_Input = By.id("search-input-location");
	By search_Btn = By.id("search-submit");
	
	public boolean isVisible_searchLocation_Input() {
		return generic.isVisible(searchLocation_Input);
	}
	
	public boolean isVisible_seach_Btn() {	
		return generic.isVisible(search_Btn);
	}
	
	public void fillText_searchLocation_Input(String location) {
		generic.Fill_Text(searchLocation_Input, location);
	}
	
	public void select_Location(String location) {
		By location_Lnk = By.xpath("(//ul[contains(@class,'ui-autocomplete')]"
				+ "//em[text()='"+location+"']/parent::a)[1]");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(location_Lnk));
		generic.Mouse_Hover(location_Lnk);
		generic.Click(location_Lnk);
	}
	
	public void click_search_Btn() {
		generic.Click(search_Btn);
		System.out.println("Clciked search_Btn");
	}
}
