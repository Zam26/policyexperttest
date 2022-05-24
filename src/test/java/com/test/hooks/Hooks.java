package com.test.hooks;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Hooks
{
	public static WebDriver driver;
	//public static RemoteWebDriver driver;
	public static FluentWait<WebDriver> waitfl;
	public static ChromeOptions options=new ChromeOptions();
	public static WebDriverWait wait;

	public static int Pass_Count=0;
	public static int Fail_Count=0;

	public static void launchBrowser() throws Exception
	{
		options.addArguments("-start-maximized");
		options.addArguments("disable-infobars");
		options.addArguments("--no-sandbox");
		System.setProperty("webdriver.chrome.whitelistedIps", "");
		System.setProperty("webdriver.chrome.driver", "ChromeDriver\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\zam\\eclipse-workspace\\MTProject\\chromedriver.exe");
		System.setProperty("webdriver.chrome.whitelistedIps", "");
		driver=new ChromeDriver(options);
		driver.get(Config.URL);
		//Wait.wait5Second();
		//wait = new WebDriverWait(driver,30);
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	
	public static void EndTest() throws InterruptedException
	{
		driver.quit();
		Wait.wait5Second();
	}

	public static void VerifyByElement(final WebElement element, final String SuccessMessage, final String ErrorMessage)
	{
		try {
		if(element.isDisplayed())
		{
			System.out.println(SuccessMessage);
		}
		else
		{
			System.out.println( ErrorMessage);
		}}catch (final Exception Exception) {
			System.out.println("Test Interrupted, Error is -->" + Exception);
		}
	}



}
