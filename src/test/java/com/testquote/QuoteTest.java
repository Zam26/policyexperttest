package com.testquote;
import com.test.excel.Excel_Read;
import com.test.hooks.*;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;


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
		Thread.sleep(10000);
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

	public boolean testAboutYou(int testNo) throws InterruptedException, Exception
	{
	
		System.out.println("Testing AboutYou Page");		
		// Based on the data from XL
		Excel_Read.read(testNo);
		
	
		selectRadioButton(Excel_Read.Title);
		
		// Fill First name and Last name
		setTextToInput("first_name", Excel_Read.Firstname);
		setTextToInput("last_name", Excel_Read.Lastname);
		
		// Fill Date of birth
		setTextToInput("policyholder_date_of_birth.day", Excel_Read.DD);		
		//driver.findElement(By.xpath("//input[@id='policyholder_date_of_birth.day")).sendKeys("23");		
		setTextToInput("policyholder_date_of_birth.month", Excel_Read.MM);
		setTextToInput("policyholder_date_of_birth.year", Excel_Read.YYYY);
		
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
		selectRadioButton(Excel_Read.Mstatus);
		
		// Fill Ocupation
		setTextToDropDown("occupation", Excel_Read.Occupation,  "1");		
		
		selectRadioButton(Excel_Read.AnotherOCC);
		if(Excel_Read.AnotherOCC.equals("Yes")) {
			setTextToDropDown("occupation_part_time", Excel_Read.OCCDetail,  "1");
		}

		// Input Mobile Number.
		setTextToInput("primary_phone_number", Excel_Read.MainPhone);
		setTextToInput("customer_email", Excel_Read.Email);		
		
		String nxt = "//button[contains(text(), 'Next')]";
		driver.findElement(By.xpath(nxt)).click();
		
		return true;
	}
	
	/*public boolean testSecondScreen()
	{
		
		return true;
	}*/
	

// Test to explore(exploratory testing) radio button selection
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

  
  // Test to read from XL sheet 
  @Test
  public void testExcelRead() throws InterruptedException, Exception {
	  Excel_Read.read(1);
  }

  // Data driven test (Positive )
 @Test
  public void testQuote() throws InterruptedException, Exception {
	 	Assert.assertTrue(testAboutYou(1));
	 	// testSecondScreen();

       }
  

//Data driven test (Failed case )
 @Test
 public void testQuoteFail() throws InterruptedException, Exception {
	 	Assert.assertFalse(testAboutYou(2));
	 	// testSecondScreen();

      }
 
 
  @BeforeMethod
  public void beforeMethod() throws Exception {
	  System.out.println("BEFORE METHOD");
	  launchBrowser();
	  // Load XL Data
  }

  @AfterMethod
  public void afterMethod() throws InterruptedException {
	  // close and quit the browser
	  System.out.println("After METHOD");
	  EndTest();
	  
  }

}
