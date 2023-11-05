package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.microsoft.schemas.office.visio.x2012.main.CellType;

public class ExcelWriter {
	
	
	//@SuppressWarnings("deprecation")
	@SuppressWarnings("deprecation")
	public static String WriteDatainExcel(Method testMethod, int rownum, int cellnum, String value) throws IOException {

		
		String testClassName = getTestClassName(testMethod);
		if (testClassName.contains(".")) {
			testClassName = testClassName.substring(testClassName.lastIndexOf(".") + 1);
		}
		Properties props = new Properties();

		
		ClassLoader loader = new DataReaderMap().getClass().getClassLoader();
		 InputStream input = loader.getResourceAsStream("config/testdata.properties");
		props.load(input);
		String dir_path = props.getProperty("userdir");
		//System.out.println(dir_path);
		String excelPath=dir_path+"/src/main/resources/testdata/"+testClassName+".xlsx";
		System.out.println("excelPath"+excelPath);
			
		System.out.println("Write in excel sheet name --" + testMethod);
		FileInputStream file;
		try {
			file = new FileInputStream(new File(excelPath));
		
		// Get the workbook instance for XLS file
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet(testMethod.getName());
		
		Row row = sheet.getRow(rownum);
		Cell cell= row.getCell(cellnum);
		
		if (value.equals(""))
		{
			if(cell.getCellType() == Cell.CELL_TYPE_FORMULA)
			{
				System.out.println("Formula is " + cell.getCellFormula());
				System.out.println("Value is"+ cell.getCachedFormulaResultType());
	         switch(cell.getCachedFormulaResultType())
	        	{	
	            case Cell.CELL_TYPE_NUMERIC:
	            	System.out.println("Cell type is numeric");
	            	float j =  (float) cell.getNumericCellValue();
	    			System.out.println(j);
	    			value = Float.toString(j);
	                System.out.println("Last evaluated as: " + cell.getNumericCellValue());
	                break;
	            case Cell.CELL_TYPE_STRING:
	            	System.out.println("Cell type is String");
	            	System.out.println("Last evaluated as \"" + cell.getRichStringCellValue() + "\"");
	                value=cell.getStringCellValue();
	                break;
	            case Cell.CELL_TYPE_ERROR:
	            	System.out.println("Error");
	                Byte b = cell.getErrorCellValue();
	                System.out.println(b.intValue());
	                value= b.toString();
	                break;
	        	}
	         workbook.setForceFormulaRecalculation(true);
			}
			else
			{
				value=cell.getStringCellValue();
				workbook.setForceFormulaRecalculation(true);
			}
			
			
				
			/*//System.out.println("inside loop");
			if(cell.getCellType()==1)
			{
				value=cell.getStringCellValue();
				System.out.println("value is "+value);
			}
			else
			{
			float j =  (float) cell.getNumericCellValue();
			System.out.println(j);
			value = Float.toString(j);
			//value = cell.getStringCellValue();
			}*/						
		}
		
		else{
		cell.setCellValue(value);
		workbook.setForceFormulaRecalculation(true);
		
		}
		//workbook.setForceFormulaRecalculation(true);
		FileOutputStream outstream = new FileOutputStream(new File(excelPath));
		workbook.write(outstream);
		outstream.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return value;
		

	}

	private static String getTestClassName(Method testMethod) {
		return testMethod.getDeclaringClass().getSimpleName();

	}
	
		
}


