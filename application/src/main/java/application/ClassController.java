package application;

import javax.servlet.ServletException;  
import javax.servlet.http.*;  
import java.io.*;  
import java.sql.*;

public class ClassController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con = null;
    private Statement stat = null; 
    private ResultSet rs = null; 
    private PreparedStatement pst = null;  

    public ClassController() {
    	super();
		try { 
		    Class.forName("com.mysql.jdbc.Driver"); 
			con = DriverManager.getConnection( 
			"jdbc:mysql://140.134.26.84:3308/tomcat?useUnicode=true&characterEncoding=utf-8", 
			"spm", "SPMtomcat105");      
	 	} catch(ClassNotFoundException e) { 
		    System.out.println("DriverClassNotFound :" + e.toString()); 
		} catch(SQLException x) { 
			System.out.println("Exception :" + x.toString()); 
		}
    }

    public void insertTable(String name, String teacher, String location, String credit, String dept) {
    	String insertdbSQL = "INSERT INTO class(id, name, teacherID, location, credit, dept)"
    					   + "VALUE(NULL, ?, ?, ?, ?, ?)"; 
    	try { 
	        pst = con.prepareStatement(insertdbSQL); 
	        // setSting -> set '?' in insertdbSQL
	        pst.setString(1, name);
	        pst.setString(2, teacher);
	        pst.setString(3, location); 
	        pst.setString(4, credit);
	        pst.setString(5, dept);
	        pst.executeUpdate();
    	} catch(SQLException e) { 
    		System.out.println("InsertDB Exception :" + e.toString()); 
    	} finally { 
    		Close(); 
    	} 
    } 

    // Close DB Connect
    private void Close() { 
    	try { 
    		if(rs != null) { 
    			rs.close(); 
    			rs = null; 
    		} 
    		if(stat != null) { 
    			stat.close(); 
    			stat = null; 
    		} 
    		if(pst != null) { 
    			pst.close(); 
    			pst = null; 
    		} 
    	} catch(SQLException e) { 
    		System.out.println("Close Exception :" + e.toString()); 
    	} 
    } 
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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