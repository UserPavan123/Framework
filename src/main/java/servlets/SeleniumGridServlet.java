package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utilities.InitTests;
import utilities.XMLCreator;

/**
 * @author Yugandhar Reddy 
 * Servlet implementation class SeleniumGridServlet
 */
public class SeleniumGridServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String path ;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException 
	{
		

	
		// reading the user input    
		String testcase[]=request.getParameterValues("GridTests");
		ArrayList<String>testCases=new ArrayList<String>();
		for(int i=0;i<testcase.length;i++)
		{
			
			testCases.add(testcase[i]);
			//out.println("<li>"+testcase[i]+"</li>");
		}
		System.out.println("Running Testcases....");
		XMLCreator.xmlCreator(testCases,"seleniumgrid");
		System.out.println("***************I'm back in Servlet*********************");
		RequestDispatcher dispatcher = request.getRequestDispatcher("Jsp/index.jsp");
		dispatcher.forward( request, response );
		
	}  

}
