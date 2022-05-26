package com.test.excel;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Read {

	public static String Title;
	public static String Firstname;
	public static String Lastname;
	public static String DD;
	public static String MM;	
	public static String YYYY;
	public static String Mstatus;
	public static String Occupation;
	public static String AnotherOCC;
	public static String OCCDetail;
	public static String MainPhone;
	public static String Email;
	

	public static void read(final int RowNumber) throws Exception
	{
		//final File f=new File("TestData/TestQuoteData.xls");
		final File f=new File("TestData/TestQuoteData.xlsx");
		final FileInputStream fi= new FileInputStream(f);

		final XSSFWorkbook wb=new XSSFWorkbook(fi);
		final XSSFSheet sheet=wb.getSheetAt(0);
		
		//final HSSFWorkbook wb=new HSSFWorkbook(fi);
		//final HSSFSheet sheet=wb.getSheetAt(0);

		for(int i=RowNumber; i<=RowNumber;  i++)
		{
			try{
			System.out.println("Entered into loop");
			Title = sheet.getRow(i).getCell(0).getStringCellValue();
			Firstname =sheet.getRow(i).getCell(1).getStringCellValue();
			Lastname =sheet.getRow(i).getCell(2).getStringCellValue();
			DD = sheet.getRow(i).getCell(3).getStringCellValue();
			MM =sheet.getRow(i).getCell(4).getStringCellValue();
			YYYY =sheet.getRow(i).getCell(5).getStringCellValue();
			Mstatus = sheet.getRow(i).getCell(6).getStringCellValue();
			Occupation = sheet.getRow(i).getCell(7).getStringCellValue();
			AnotherOCC =sheet.getRow(i).getCell(8).getStringCellValue();
			OCCDetail =sheet.getRow(i).getCell(9).getStringCellValue();
			MainPhone = sheet.getRow(i).getCell(10).getStringCellValue();
			Email =sheet.getRow(i).getCell(11).getStringCellValue();
			

			System.out.println("Entering Row -> "+ i +"");
			System.out.println("Row data " + Title  + Firstname + Lastname  + DD + MM +YYYY + Mstatus + Occupation + AnotherOCC + OCCDetail + MainPhone + Email);

			}
			catch(final Exception e)
			{
				System.out.println("Excel data not reading :error is:  " + e);
			}
		}

	}

}
