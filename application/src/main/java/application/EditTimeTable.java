package application;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditTimeTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Connection con = null;
    private Statement stat = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;

    public EditTimeTable() {
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

	public void deleteClass(String userId, String classId)
	{
		//System.out.println("deleteClass Start");
		String query = "delete from curriculum where userID=? and classID=?;";
		try {
			pst = con.prepareStatement(query);

			pst.setString(1, userId);
			pst.setString(2, classId);
			pst.executeUpdate();
		} catch(SQLException e) {
			System.out.println("deleteClass Exception :" + e.toString());
		} finally {
			close();
		}
		System.out.println("deleteClass End");
	}

	public void addClass(String userId, String classId)
	{
		//System.out.println("addClass start");
		String query = "insert ignore into curriculum(userID,classID,TIMESTAMP ) value (?,?,now());";
		try {
			pst = con.prepareStatement(query);

			pst.setString(1, userId);
			pst.setString(2, classId);
			pst.executeUpdate();
		} catch(SQLException e) {
			System.out.println("deleteClass Exception :" + e.toString());
		} finally {
			close();
		}
		System.out.println("addClass End");
	}
	public ArrayList getMyClassList(String userId)
	{
		//System.out.println("getMyClassList start");
		ArrayList<Map<String,Object>> returnList = new ArrayList<>();
		try
		{
			String selectSQL =
							"SELECT " +
								"distinct "+
								"curriculum.userID as userId," +
								"class.id as classId," +
								"name as className," +
								"location," +
								"credit," +
								"dept," +
								"teacher.teacherID as teacherID," +
								"teacher_Name," +
								"teacher_Email " +
							"FROM " +
								" teacher, class " +
							"LEFT JOIN  " +
								"curriculum " +
							"on " +
								"curriculum.classID= class.id  and " +
								"curriculum.userID = '"+userId+"' "+
							"where " +
								"class.teacherID=teacher.teacherID and " +
								"userId is not null" +
							";";
			//System.out.println(selectSQL);
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);


			while(rs.next())
			{
				Map<String,Object> temp = new HashMap<String,Object>();
				temp.put("classId",rs.getString("classId"));
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
		System.out.println("getMyClassList end");
		return returnList;
	}

	public ArrayList getClassList(String userId)
	{
		//System.out.println("getClassList start");
		ArrayList<Map<String,Object>> returnList = new ArrayList<>();
		try
		{
			String selectSQL =
					"SELECT " +
							"distinct "+
							"curriculum.userID as userId," +
							"class.id as classId," +
							"name as className," +
							"location," +
							"credit," +
							"dept," +
							"teacher.teacherID as teacherID," +
							"teacher_Name," +
							"teacher_Email " +
					"FROM " +
							" teacher, class " +
					"LEFT JOIN  " +
							"curriculum " +
					"on " +
							"curriculum.classID= class.id  and " +
							"curriculum.userID = '"+userId+"' "+
					"where " +
							"class.teacherID=teacher.teacherID and " +
							"userId is null" +
							";";
			//System.out.println(selectSQL);
			stat = con.createStatement();
			rs = stat.executeQuery(selectSQL);


			while(rs.next())
			{
				Map<String,Object> temp = new HashMap<String,Object>();
				temp.put("classId",rs.getString("classId"));
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
		System.out.println("doGet ");
		String userId ="";
		try
		{
			HttpSession session = request.getSession(true);
			userId = session.getAttribute("userId").toString();
		}
		catch(Exception ex){}

		try
		{
			ArrayList classList = getClassList(userId);
			request.setAttribute("classList",classList );
			ArrayList myClassList = getMyClassList(userId);
			request.setAttribute("myClassList",myClassList );
			getServletConfig().getServletContext().getRequestDispatcher("/editTimeTable.jsp").forward(request, response);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		System.out.println("doPost ");
		try
		{
			HttpSession session = request.getSession(true);
			String userId = session.getAttribute("userId").toString();
			String classId = request.getParameter("id");
			String type = request.getParameter("type");

			if(type.equals("del"))
			{
				deleteClass(userId, classId);
			}
			else if(type.equals("add"))
			{
				addClass(userId, classId);
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		doGet(request,response);
	}

}