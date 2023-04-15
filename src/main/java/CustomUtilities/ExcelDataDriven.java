package CustomUtilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataDriven {
	static DataFormatter formatter =new DataFormatter();
	public static Object[][] getExcelDataAsTwodimensionalArray(String sheetName) throws IOException
	{
		FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\TestData.xlsx"));
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		
		int rowCount=sheet.getPhysicalNumberOfRows();
		
		XSSFRow row=sheet.getRow(0);
		
		int colCount=row.getLastCellNum();
		
//		System.out.println(rowCount);
//		System.out.println(colCount);
		
		Object[][] data=new Object[rowCount-1][colCount];
		
		for(int i=0;i<rowCount-1;i++)
		{
			row=sheet.getRow(i+1);
			
			for(int j=0;j<colCount;j++)
			{
				XSSFCell cell=row.getCell(j);
				data[i][j]=formatter.formatCellValue(cell);
			}
		}
		
		return data;
	}

	
	public static Object[][] getExcelDataAsTwodimensionalArray(String sheetName, String recordName) throws IOException
	{
		FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\TestData.xlsx"));
		XSSFWorkbook workbook=new XSSFWorkbook(fis);
		
		XSSFSheet sheet=workbook.getSheet(sheetName);
		
		int rowCount=sheet.getPhysicalNumberOfRows();
		
		XSSFRow row=sheet.getRow(0);
		
		int colCount=row.getLastCellNum();
		
//		System.out.println(rowCount);
//		System.out.println(colCount);
		
		Object[][] data=new Object[1][colCount];
		
		for(int i=0;i<rowCount-1;i++)
		{
			if(sheet.getRow(i+1).getCell(0).getStringCellValue().equalsIgnoreCase(recordName))
			{
				for(int j=0;j<1;j++)
				{
					row=sheet.getRow(i+1);
					
					for(int k=0;k<colCount;k++)
					{
						XSSFCell cell=row.getCell(k);
						data[j][k]=formatter.formatCellValue(cell);
					}
				}
				break;
			}
		}
		
		return data;
	}

}
