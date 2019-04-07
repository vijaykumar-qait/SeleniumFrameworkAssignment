package org.automationlabs.pageobject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.automationlabs.core.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ZooplaSerachResultPage {
	
	WebDriver driver;
	GenericFunctions generic;
	WebDriverWait wait;
	
	public ZooplaSerachResultPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		this.wait = new WebDriverWait(driver, 10);
	}
	
	By propertSale_Txt = By.xpath("//h1");
	By propertyPriceList_Lnk = By.xpath("//ul[contains(@class, 'listing-results')]"
			+ "//li//a[contains(@class,'listing-results-price')]");
	
	
	public String returnText_propertSale_Txt() {
		return generic.GetText(propertSale_Txt);
	}
	
	public void printPriceValueSortedOrder() {
		List<WebElement> propertyPriceList = driver.findElements(propertyPriceList_Lnk);
		List<Integer> propertyPrice = new ArrayList<Integer>();
		List<String> propertyPriceStr = new ArrayList<String>();
		for(int i=0; i<propertyPriceList.size(); i++) {
			String price = propertyPriceList.get(i).getAttribute("innerText");
			String [] amount = price.split(" ");
			propertyPriceStr.add(amount[0]);
		}
		String currency="";
		if(propertyPriceStr.size()>0) {
			currency = propertyPriceStr.get(0).substring(0,1);
		}
		
		for(int i=0; i<propertyPriceStr.size(); i++) {
			String price[] = propertyPriceStr.get(i).substring(1).split(",");
			String amount="";
			for(int j=0; j<price.length; j++) {
				amount += price[j];
			}
			propertyPrice.add(Integer.parseInt(amount));
		}
		Collections.sort(propertyPrice);
		for(int i=propertyPrice.size()-1; i>=0; i--) {
			System.out.println(currency + NumberFormat.getNumberInstance(Locale.UK).
					format(propertyPrice.get(i)));
		}
	}
	
	public void click_fifthProperty_Lnk() {
		List<WebElement> propertyPriceList = driver.findElements(propertyPriceList_Lnk);
		wait.until(ExpectedConditions.visibilityOf(propertyPriceList.get(4)));
		generic.moveToElement(propertyPriceList.get(4));
		propertyPriceList.get(4).click();
	}

}
