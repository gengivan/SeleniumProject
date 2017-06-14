package com.nancy.testscripts;

import org.testng.annotations.Test;

import com.nancy.actions.LoginAction;
import com.nancy.commonfunction.CommonFunction;
import com.nancy.constants.ProjectConstants;
import com.nancy.util.DriverBase;
import com.nancy.util.LocatorUtil;
import com.nancy.util.Log;
import com.nancy.util.TestNGListener;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;

//@Listeners({ TestNGListener.class }) 
public class AddressManagement extends DriverBase{
//	public WebDriver driver;
//	private CommonFunction CommonFunction;
//	private LocatorUtil LocatorUtil;


	@DataProvider(name = "AddressName")
	public Object[][] GetAddressName() {

		return new Object[][] { { "HomeAddress" } };
	}

	@DataProvider(name = "AddressNameUpdate")
	public Object[][] GetUpdateAddressName() {

		return new Object[][] { { "HomeAddressUpdate" } };
	}
	@Test(dataProvider = "AddressName")
	public void AddNewAddress(String strAddressName) {
		Log.StartTestCase("Add New Address: "+strAddressName);
		CommonFunction.Click(driver, "MyAccountPage.MyAddress.Button");
		CommonFunction.Click(driver, "MyAddress.AddANewAddress.Button");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.FirstName.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.FirstName.Edit", "Zhou");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.LastName.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.LastName.Edit", "YingChun");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.Company.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.Company.Edit", "Company1");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.Address.Edit", "Address1");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddresss.Address2.Edit", "Address2");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.City.Edit", "City1");
		CommonFunction.selectByVisibleText(driver, "MyAddress.AddNewAddress.State.Select", "Arizona");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.ZipCode.Edit", "23456");
		CommonFunction.selectByVisibleText(driver, "MyAddress.AddNewAddress.Country.Select", "United States");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.HomePhone.Edit", "12345678909");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.AdditionalInformation.Edit", "additional infomation");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.AddressTitle.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.AddressTitle.Edit", strAddressName);
		CommonFunction.Click(driver, "MyAddress.AddNewAddress.Save.Button");
		LocatorUtil.setParameterValue("$AddressName$", strAddressName);
		
//		WebDriverWait driverw = new WebDriverWait(driver, 20);
//				driverw.until().presenceOfElementLocated(LocatorUtil.getLocator("MyAddress.AddressList.AddressTitle.Text")));
		try {
			CommonFunction.findElement(driver, "MyAddress.AddressList.AddressTitle.Text");
			Log.info("验证新加的Address显示在address list里面，Pass！");
			Log.EndTestCase("Add New Address: "+strAddressName);
			Assert.assertTrue(true);
		} catch (NoSuchElementException exception) {
			Log.error("验证新加的Address显示在address list里面，Fail！");
			Log.EndTestCase("Add New Address: "+strAddressName);
			Assert.fail("验证新加的Address显示在address list里面，Fail！");
		}

	}

	@Test(dataProvider = "AddressNameUpdate")
	public void UpdateNewAddress(String strAddressNameUpdate) {
		Log.StartTestCase("Update Address to "+strAddressNameUpdate);
		CommonFunction.Click(driver, "MyAddress.AddressList.Update.Button");
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.FirstName.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.FirstName.Edit", "ZhouUpdate");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.LastName.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.LastName.Edit", "YingChunUpdate");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.Company.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.Company.Edit", "Company1Update");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.Address.Edit", "Address1Update");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddresss.Address2.Edit", "Address2Update");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.City.Edit", "City1Update");
		CommonFunction.selectByVisibleText(driver, "MyAddress.AddNewAddress.State.Select", "Florida");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.ZipCode.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.ZipCode.Edit", "65432");
		CommonFunction.selectByVisibleText(driver, "MyAddress.AddNewAddress.Country.Select", "United States");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.HomePhone.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.HomePhone.Edit", "9876543210");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.AdditionalInformation.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.AdditionalInformation.Edit", "additional infomation Update");
		CommonFunction.Clear(driver, "MyAddress.AddNewAddress.AddressTitle.Edit");
		CommonFunction.sendKeys(driver, "MyAddress.AddNewAddress.AddressTitle.Edit", strAddressNameUpdate);
		CommonFunction.Click(driver, "MyAddress.AddNewAddress.Save.Button");
		LocatorUtil.setParameterValue("$AddressName$", strAddressNameUpdate);
		boolean flag =CommonFunction.IsElementExist(driver, "MyAddress.AddressList.AddressTitle.Text");
		if (flag){
			Log.info("验证update之后的Address 显示在address list里面，Pass");
			Log.EndTestCase("Update Address to "+strAddressNameUpdate);
			Assert.assertTrue(flag);
		}
		else{
				Log.error("验证update之后的Address 显示在address list里面，fail");
				Log.EndTestCase("Update Address to "+strAddressNameUpdate);
				Assert.fail("验证update之后的Address 显示在address list里面，Fail");
			}
						
		}

@Test(dataProvider = "AddressNameUpdate")
	public void deleteAddress(String strAddressNameDelete) throws InterruptedException{
	Log.StartTestCase("Delete Address "+strAddressNameDelete);
	LocatorUtil.setParameterValue("$AddressName$", strAddressNameDelete);
	CommonFunction.Click(driver, "MyAddress.AddressList.Delete.Button");
	driver.switchTo().alert().accept();
	Thread.sleep(5000L);
	boolean isDeleted = CommonFunction.IsElementExist(driver, "MyAddress.AddressList.AddressTitle.Text");
	if(!isDeleted){
		Log.info("删除Address:"+strAddressNameDelete+", 验证删除后的address没有显示在页面上，Pass!");
		Log.EndTestCase("Delete Address "+strAddressNameDelete);
		Assert.assertTrue(!isDeleted);
	}
		else{
			Log.error("删除Address:"+strAddressNameDelete+", 验证删除后的address没有显示在页面上，Fail!");
			Log.EndTestCase("Delete Address "+strAddressNameDelete);
			Assert.fail("删除Address:"+strAddressNameDelete+", 验证删除后的address没有显示在页面上，Fail!");
		}
	}

	
@BeforeClass
	public void beforeTest() {
//		System.setProperty("webdriver.chrome.driver", ProjectConstants.ChromeDrivePath);
//		driver = new ChromeDriver();
//		 System.setProperty("webdriver.firefox.marionette",ProjectConstants.FireFoxGeckoDriverPath);
//		 driver=new FirefoxDriver();
		this.setDriver("chrome");
		this.driver.get(ProjectConstants.BaseUrl);
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		LoginAction loginAction = new LoginAction(driver, ProjectConstants.LoginAccount, ProjectConstants.LoginPassword);
		loginAction.doLogin();
	}

	
	
	@AfterClass
	public void afterTest() {
//		driver.close();
//		driver.quit();
	}

}