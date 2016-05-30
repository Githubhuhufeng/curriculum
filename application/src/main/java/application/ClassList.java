package application;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.*;  
import java.io.*;  
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClassList extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con = null;
    private Statement stat = null; 
    private ResultSet rs = null; 
    private PreparedStatement pst = null;  

    public ClassList() {
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
	public ArrayList getClassList()
	{
		System.out.println("getClassList start");
		ArrayList<Map<String,Object>> returnList = new ArrayList<>();
		try
		{
			String selectSQL =	/*"SELECT " +
									"distinct "+
									"name as className," +
									"location," +
									"credit," +
									"dept," +
									"teacher.teacherID as teacherID," +
									"teacher_Name," +
									"teacher_Email " +
								"FROM " +
									"class, teach_class, teacher " +
								"where " +
									"class.id =teach_class.classID and " +
									"teach_class.teacherID=teacher.teacherID" +
									";";*/
							"SELECT " +
								"distinct "+
								"name as className," +
								"location," +
								"credit," +
								"dept," +
								"teacher.teacherID as teacherID," +
								"teacher_Name," +
								"teacher_Email " +
							"FROM " +
								"class, teacher " +
							"where " +
								"class.teacherID=teacher.teacherID" +
							";";
			System.out.println(selectSQL);
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);


			while(rs.next())
			{
				Map<String,Object> temp = new HashMap<String,Object>();
				temp.put("className",rs.getString("className"));
				temp.put("location",rs.getString("location"));
				temp.put("credit",rs.getString("credit"));
				temp.put("dept",rs.getString("dept"));
				temp.put("teacherID",rs.getString("teacherID"));
				temp.put("teacher_Name",rs.getString("teacher_Name"));
				temp.put("teacher_Email",rs.getString("teacher_Email"));
				returnList.add(temp);
			}
		}
		catch(SQLException e)
		{
			System.out.println("DropDB Exception :" + e.toString());
		}
		finally
		{
			close();
		}
		System.out.println("getClassList end");
		return returnList;
	}
	// Close DB Connect
    private void close() {
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
		try
		{
			ArrayList temp = getClassList();
			request.setAttribute("classList",temp );
			getServletConfig().getServletContext().getRequestDispatcher("/classList.jsp").forward(request, response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}