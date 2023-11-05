package utilities;


import java.io.File;

import java.io.FileInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import static utilities.InitTests.dir_path;

public class DataRepository {
	private static ArrayList<Object[]> values=new ArrayList<Object[]>();
	@DataProvider(name="create")
	public static Object[][] createData(Method testMethod){		

		String testClassName = getTestClassName(testMethod);
		if (testClassName.contains(".")){
			testClassName=testClassName.substring(testClassName.lastIndexOf(".")+1);
		}
		List<String> parameters=getTestParameters(testMethod);
		String excelPath=dir_path+"/src/main/resources/testdata/"+testClassName+".xlsx";
	System.out.println("data from excel path "+excelPath);
		readExcel(excelPath,testMethod.getName(),parameters);
		
		Object returnValues[][]=new Object[values.size()][];
		int i=0;
		for (Object[] obj : values){
			returnValues[i]=obj;
			i++;
		}
		System.out.println("datafrom excel ready for data driven --"+returnValues[0][0]);
	return returnValues;
	}

	private static void readExcel(String fileName,String methodName, List<String> parameters){

		try{
			//TODO - Keep bellow try for Apache POI APIs
			System.out.println("in read excel sheet name --"+methodName);
			FileInputStream file = new FileInputStream(new File(fileName));	

			//Get the workbook instance for XLS file 
			XSSFWorkbook workbook = new XSSFWorkbook(fileName);

			//Get first sheet from the workbook
			//XSSFSheet sheet = workbook.getSheetAt(0);
			//System.out.println("Reading Sheet : "+methodName);
			XSSFSheet sheet = workbook.getSheet(methodName);

			

			//List<String> parameterValues=new ArrayList<String>();
			values.clear();

			//List<String> headerLine = new ArrayList<String>();
			int totRows=sheet.getPhysicalNumberOfRows();

			for(int rowNum=1;rowNum<totRows;rowNum++)
			{
				//Read all other lines as data of required parameters
				List<String> nextLine = new ArrayList<String>();
			
			for(int col=0;col<sheet.getRow(rowNum).getPhysicalNumberOfCells();col++)
			{
				XSSFCell cell=sheet.getRow(rowNum).getCell(col);
				if(cell!=null)
				{
				 switch (cell.getCellTypeEnum()) {
	                case  STRING:
	                	nextLine.add(sheet.getRow(rowNum).getCell(col).getStringCellValue());
	                    break;
	                case NUMERIC:
	                	Double d=sheet.getRow(rowNum).getCell(col).getNumericCellValue();
	                	nextLine.add(d.toString());
	                    break;
	                   
	                    case  BOOLEAN:
	                    	Boolean b=sheet.getRow(rowNum).getCell(col).getBooleanCellValue();
		                	nextLine.add(b.toString());
		                    break;
	                    default:
	                    	break;
				 }
				}
				 else {
				 System.out.println("empty cell");
				 }
					 
				
			
			}		
					values.add(nextLine.toArray());
					nextLine.clear();
					        
			}
			

		}catch(Exception e){
			
			System.out.println("Exception occured-> DataRepository -> readExcel() -> ");
			e.printStackTrace();
		}		
	}

	private static List<String> getTestParameters(Method testMethod){
		Annotation[] a= testMethod.getAnnotations();
		List<String> parameters=new ArrayList<String>();

		if(a.length!=0){
			for(Annotation val : a){

				if(val instanceof Parameters){
					//Test method has parameters

					//Get parameter names of test method
					//Need better way of doing it
					String temp=val.toString();


					int index1=temp.indexOf('[');
					int index2=temp.indexOf(']');
					temp=temp.substring(index1+1,index2);


					StringTokenizer st=new StringTokenizer(temp, ",");
					while(st.hasMoreTokens()){
						//Add parameter names List
						parameters.add(st.nextToken().trim());
						
						
					}
				}
			}
		}
		return parameters;
	}	

	private static String getTestClassName(Method testMethod){
		return testMethod.getDeclaringClass().getSimpleName();
		
	}
}
