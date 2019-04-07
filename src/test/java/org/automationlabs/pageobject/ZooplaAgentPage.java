package org.automationlabs.pageobject;

import org.automationlabs.core.GenericFunctions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZooplaAgentPage {
	WebDriver driver;
	GenericFunctions generic;
	
	public ZooplaAgentPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
	}
	
	By agentName_Txt = By.xpath("//h1/b[1]");
	
	public boolean isVisible_agentName_Txt() {
		return generic.isVisible(agentName_Txt);
	}
	
	public String getText_agentName_Txt() {
		return generic.GetText(agentName_Txt);
	}
}
