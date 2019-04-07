package org.automationlabs.tests;

import java.util.concurrent.TimeUnit;

import org.automationlabs.config.ConfigFileReader;
import org.automationlabs.core.GenericFunctions;
import org.automationlabs.pageobject.ZooplaAgentPage;
import org.automationlabs.pageobject.ZooplaHomePage;
import org.automationlabs.pageobject.ZooplaPropertyPage;
import org.automationlabs.pageobject.ZooplaSerachResultPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AssignmentOneTest {
	String URL;
	String browserType;
	int timeout;
	WebDriver driver;
	ZooplaHomePage zooplaHomePage;
	ZooplaSerachResultPage zooplaSRP;
	ZooplaPropertyPage zooplaPropertyPage;
	ZooplaAgentPage zooplaAgentPage;
	GenericFunctions generic;
	String agentName;
	
	@BeforeTest
	public void BeforeTest() {
		URL = ConfigFileReader.getConfigValue("url");
		browserType = ConfigFileReader.getConfigValue("browser");
		timeout = Integer.parseInt(ConfigFileReader.getConfigValue("implicitwait"));
		generic = new GenericFunctions(driver);
		driver = generic.StartDriver(browserType);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
		zooplaHomePage = new ZooplaHomePage(driver, generic);
		zooplaSRP = new ZooplaSerachResultPage(driver, generic);
		zooplaPropertyPage = new ZooplaPropertyPage(driver, generic);
		zooplaAgentPage = new ZooplaAgentPage(driver, generic);
		agentName = "";
	}
	
	@Test
	public void TC_001_Verify_Zoopla_Home_Page() {
		driver.get(URL);
		Assert.assertTrue(zooplaHomePage.isVisible_searchLocation_Input(), "User is not on Zoopla Home Page");
		Assert.assertTrue(zooplaHomePage.isVisible_seach_Btn(), "User is not on Zoopla Home Page");
	}
	
	@Test
	public void TC_002_Verify_User_Search_Location_London() {
		zooplaHomePage.fillText_searchLocation_Input("London");
		zooplaHomePage.select_Location("London");
		zooplaHomePage.click_search_Btn();
		Assert.assertTrue(zooplaSRP.returnText_propertSale_Txt().contains("London"), "User not navigated"
				+ "Search result page");
	}
	
	@Test
	public void TC_003_Verify_User_Select_Fifth_Property() {
		zooplaSRP.printPriceValueSortedOrder();
		zooplaSRP.click_fifthProperty_Lnk();
		Assert.assertTrue(zooplaPropertyPage.isVisible_backToSearch_Lnk(), "User is not on propety page");
	}
	
	@Test
	public void TC_004_Verify_Agent_Details_And_Navigate_To_Agent_Page() {
		zooplaPropertyPage.isVisible_agentLogo_Img();
		zooplaPropertyPage.isVisible_agentName_Txt();
		zooplaPropertyPage.isVisible_agentNumber_Lnk();
		agentName = zooplaPropertyPage.getText_agentName_Txt();
		zooplaPropertyPage.click_agentName_Txt();
		Assert.assertTrue(zooplaAgentPage.isVisible_agentName_Txt(), "User is not on Agent page");
	}
	
	@Test
	public void TC_005_Verify_Agent_Details_Mentioned_In_Property_Page() {
		Assert.assertTrue(zooplaAgentPage.getText_agentName_Txt().equals(agentName), "Agent Name mis-match");
	}
	
	@AfterTest
	public void CloseBrowser() {
		driver.quit();
	}

}
