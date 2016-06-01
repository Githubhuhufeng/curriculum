package application;

import javax.servlet.ServletException;  
import javax.servlet.http.*;  
import java.io.*;  
import java.sql.*;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;

public class ClassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnect dc;


	public ClassController() {
		super();
		dc = new DatabaseConnect();

	}

	public void insertTable(String name, String teacher, String location, String credit, String dept) {
		String insertdbSQL = "INSERT INTO class(id, name, teacherID, location, credit, dept)"
				+ "VALUE(NULL, '" + name + "', '" + teacher + "', '" + location + "', '" + credit + "', '" + dept + "')";
		dc.execute(insertdbSQL);
	} 



	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doPost!!");    
		String name, teacher, location, credit, dept;
		name 	 = request.getParameter("name");
		teacher  = request.getParameter("teacher");
		location = request.getParameter("location");
		credit 	 = request.getParameter("credit");
		dept 	 = request.getParameter("dept");

		ClassController classCon = new ClassController(); 
		classCon.insertTable(name, teacher, location, credit, dept); 
		System.out.println("inserts!!");     
		response.sendRedirect("classList");
	}

}