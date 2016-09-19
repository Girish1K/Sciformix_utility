package com.sciformix.converter;

import java.io.*;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelConverter {
	static void convertXlsxToCsv(File inputFile, File outputFile) 
	{
		System.out.println("Input File: "+inputFile+"\nOutput File: "+outputFile);
	        // For storing data into CSV files
	StringBuffer cellValue = new StringBuffer();
	try 
	{
	        FileOutputStream fos = new FileOutputStream(outputFile);

	        // Get the workbook instance for XLSX file
	        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(inputFile));

	        // Get first sheet from the workbook
	        XSSFSheet sheet = wb.getSheetAt(0);

	        Row row;
	        Cell cell;

	        // Iterate through each rows from first sheet
	        Iterator<Row> rowIterator = sheet.iterator();

	        while (rowIterator.hasNext()) 
	        {
	        	row = rowIterator.next();

	        // For each row, iterate through each columns
	        	Iterator<Cell> cellIterator = row.cellIterator();
	        	while (cellIterator.hasNext()) 
	        	{
	        		cell = cellIterator.next();

	                switch (cell.getCellType()) 
	                {
	                
	                	case Cell.CELL_TYPE_BOOLEAN:
	                		cellValue.append(cell.getBooleanCellValue() + ",");
	                        break;
	                
	                	case Cell.CELL_TYPE_NUMERIC:
	                        cellValue.append(cell.getNumericCellValue() + ",");
	                        break;
	                
	                	case Cell.CELL_TYPE_STRING:
	                        cellValue.append(cell.getStringCellValue() + ",");
	                        break;

	                	case Cell.CELL_TYPE_BLANK:
	                        cellValue.append("" + ",");
	                        break;
	                        
	                	default:
	                        cellValue.append(cell + ",");

	                }
	        }
	        cellValue.append("\n");
	        }

	        fos.write(cellValue.toString().getBytes());
	        fos.close();

	} 
		catch (Exception e) 
		{
	        System.err.println("Exception :" + e.getMessage());
		}
	}

	static void convertXlsToCsv(File inputFile, File outputFile) 
	{
		System.out.println("Input File: "+inputFile+"\nOutput File: "+outputFile);
	// For storing data into CSV files
		StringBuffer cellDData = new StringBuffer();
		try 
		{
	        FileOutputStream fos = new FileOutputStream(outputFile);

	        // Get the workbook instance for XLS file
	        HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(inputFile));
	        // Get first sheet from the workbook
	        HSSFSheet sheet = workbook.getSheetAt(0);
	        Cell cell;
	        Row row;

	        // Iterate through each rows from first sheet
	        Iterator<Row> rowIterator = sheet.iterator();
	        while (rowIterator.hasNext()) 
	        {
	        	row = rowIterator.next();

	        // For each row, iterate through each columns
	        	Iterator<Cell> cellIterator = row.cellIterator();
	        	while (cellIterator.hasNext()) 
	        	{
	        		cell = cellIterator.next();

	        		switch (cell.getCellType()) 
	        		{
	        
	        		case Cell.CELL_TYPE_BOOLEAN:
	        			cellDData.append(cell.getBooleanCellValue() + ",");
	        			break;
	        
	        		case Cell.CELL_TYPE_NUMERIC:
	        			cellDData.append(cell.getNumericCellValue() + ",");
	        			break;
	        
	        		case Cell.CELL_TYPE_STRING:
	        			cellDData.append(cell.getStringCellValue() + ",");
	        			break;

	        		case Cell.CELL_TYPE_BLANK:
	        			cellDData.append("" + ",");
	        			break;
	                
	        		default:
	        			cellDData.append(cell + ",");
	        }
	        
	        }
	        cellDData.append("\n");
	        }

	        fos.write(cellDData.toString().getBytes());
	        fos.close();

	}
		catch (FileNotFoundException e) 
		{
			System.err.println("Exception" + e.getMessage());
		} 
		catch (IOException e) 
		{
	        System.err.println("Exception" + e.getMessage());
		}
	}
	
public static boolean checkNoOfArgs(String args[]){
	boolean falg=true;
	// System.out.println(args.length);
	if(args.length==0){
		System.out.println("Source input file can not found:");
		falg=false;
	}
	if(args.length==1){
		System.out.println("Destination file path can not found:");
		falg=false;
	}
		
	return falg;
}
	
	public static void main(String[] args){
		if(checkNoOfArgs(args)){
			
			File inputFile=new File(args[0]);
			if(inputFile.isFile()){
				String outputPath=args[1];		
				String fileName=inputFile.getName();
				String extension=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
				File outputFile=new File(outputPath+"\\"+fileName.substring(0, fileName.lastIndexOf(".")+1)+"csv");
				//System.out.println("extension is -->> "+extension+"\nInput file-->> "+inputFile+"\nOutput file-->> "+outputFile);
				switch(extension){
				case "xlsx":
					convertXlsxToCsv(inputFile, outputFile);
					break;
				case "xls":
					convertXlsToCsv(inputFile, outputFile);
					break;
				default:
					System.out.println("Input file: "+inputFile);
					System.out.println("Invalid input file: ");
					break;
				}	
			}else
				System.out.println("Invalid input file: ");
		}
	}
}