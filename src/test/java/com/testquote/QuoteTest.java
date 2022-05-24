package com.testquote;
import com.test.hooks.*;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;



//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.Assert;
//import org.testng.annotations.*;


public class QuoteTest extends Hooks{
	
	// This functions select the Radio button in the webpage with string = txtofbutton
	public void selectRadioButton(String txtofbutton)
	{		
	    String tmp = "//button[contains(text(), '" + txtofbutton + "')]";
	
		driver.findElement(By.xpath(tmp)).click();
		System.out.println("Buttton clicked: Xpath " + tmp);
	}

		//This function fills the text into the given input box
	public void setTextToInput(String input, String text) 
	{
		String tmp = "//input[@id='" + input + "']";
		driver.findElement(By.xpath(tmp)).sendKeys(text);		
	}
	
	// This is written for Occupation and Occupation_part_time.
	// These Input item actually controlled by ListBox.
	// So first Enter Text and click on the  list item position from the menu
	public void setTextToDropDown(String name, String text, String pos) throws InterruptedException 
	{
		
		WebElement inputElem = driver.findElement(By.name(name));
		String id = inputElem.getAttribute("aria-controls");

		inputElem.sendKeys(text);
		Thread.sleep(5000);
		System.out.println("aria-controls: " + id);
		
		String listPath = "//ul[@id='" + id + "']/li[" + pos + "]";
		System.out.println("listPath " + listPath);

		driver.findElement(By.xpath(listPath)).click();
	}
	
	
	/*public void inputWhenRadioBtn(String radio, String input, String content)
	{
		selectRadioButton(radio);
		// Check weather input is displayed.
		setTextToInput(input, content);
	}*/

	public boolean testAboutYou() throws InterruptedException
	{
	
		System.out.println("Testing AboutYou Page");		
		// Based on the data from XL
		selectRadioButton("Mr");
		
		// Fill First name and Last name
		setTextToInput("first_name", "Zameer");
		setTextToInput("last_name", "Shaik");
		
		// Fill Date of birth
		setTextToInput("policyholder_date_of_birth.day", "15");		
		//driver.findElement(By.xpath("//input[@id='policyholder_date_of_birth.day")).sendKeys("23");		
		setTextToInput("policyholder_date_of_birth.month", "11");
		setTextToInput("policyholder_date_of_birth.year", "1979");
		
		// See weather the DOB validation success by checking the error info
		// Error info only displayed when control clicks on other items.
		try 
		{
			driver.findElement(By.xpath("//label[@id='marital_status-label']")).click();
			if (driver.findElement(By.xpath("//p[@id='policyholder_date_of_birth-error']")).isDisplayed())
			{
				System.out.println("Invalid Date of .Birth");
				// Check expected result from XL if expected result is false then  return true.
				return false;
			}
		}
		catch (NoSuchElementException e)
		{
			// Check XL if expected result is true return true.
			// else false
		}
		
		// Fill the Marital status.
		selectRadioButton("Married");
		
		// Fill Ocupation
		setTextToDropDown("occupation", "Soft",  "1");		
		
		selectRadioButton("No");
		selectRadioButton("Yes");
		setTextToDropDown("occupation_part_time", "Dri",  "2");

		// Input Mobile Number.
		setTextToInput("primary_phone_number", "07787878780");
		setTextToInput("customer_email", "temp@test.com");
		
		
		
		String nxt = "//button[contains(text(), 'Next')]";
		driver.findElement(By.xpath(nxt)).click();
		
		return true;
	}
	
	/*public boolean testSecondScreen()
	{
		
		return true;
	}*/
	

  @Test
  public void testFirstPageRandom() throws Exception{		
		
		System.out.println("RANDOM TEST START");
		selectRadioButton("Mrs");
		selectRadioButton("Dr");
		selectRadioButton("Miss");
		selectRadioButton("Ms");
		selectRadioButton("Mr");
		
		System.out.println("RANDOM TEST END");
  }
	
	
 @Test
  public void testQuote() throws InterruptedException {
	 	Assert.assertTrue(testAboutYou());
	  // testSecondScreen();

       }
  
   
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  launchBrowser();	
	  // Load XL Data
  }

  @AfterMethod
  public void afterMethod() throws InterruptedException {
	  // close and quit the browser
	  driver.close();
	  
  }

}
