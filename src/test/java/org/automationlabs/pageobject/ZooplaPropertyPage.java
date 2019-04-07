package org.automationlabs.pageobject;

import org.automationlabs.core.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZooplaPropertyPage {

	WebDriver driver;
	GenericFunctions generic;
	
	public ZooplaPropertyPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
	}
	
	By backToSearch_Lnk = By.xpath("//span[contains(text(), 'Back to search')]");
	By agentName_Txt = By.xpath("(//div[@class='ui-agent']//h4[@class='ui-agent__name'])[1]");
	By agentNumber_Lnk = By.xpath("//a[contains(@data-track-label,'Agent phone number 1')]");
	By agentLogo_Img = By.xpath("(//div[@class='ui-agent__logo']/img)[1]");
	
	public boolean isVisible_backToSearch_Lnk() {
		return generic.isVisible(backToSearch_Lnk);
	}
	
	public void isVisible_agentName_Txt() {
		generic.isVisible(agentName_Txt);
		System.out.println("Agent Name is displayed");
	}
	
	public void isVisible_agentNumber_Lnk() {
		generic.isVisible(agentNumber_Lnk);
		System.out.println("Agent nubmer is displayed");
	}
	
	public void isVisible_agentLogo_Img() {
		generic.isVisible(agentLogo_Img);
		System.out.println("Agent logo is displayed");
	}
	
	public String getText_agentName_Txt() {
		return generic.GetText(agentName_Txt);
	}
	
	public void click_agentName_Txt() {
		generic.Mouse_Hover(agentName_Txt);
		generic.Click(agentName_Txt);
		System.out.println("Clicked agentName_Txt");
	}
}
