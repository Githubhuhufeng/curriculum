package application;



import javax.servlet.ServletException;  
import javax.servlet.http.*;  
import java.io.*;  
import java.sql.*;
/**
 * Servlet implementation class register
 */
public class Teachers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private Statement stat = null; 
	private ResultSet rs = null; 
	private PreparedStatement pst = null; 

	private String selectSQL = "select * from teacher "; 

	public Teachers() {
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

	public void insertTable( String ID,String Name,String Email) { 
		String insertdbSQL = "insert into teacher(teacherID,teacher_Name,teacher_Email)VALUES(?,?,?) ";
		try { 
			pst = con.prepareStatement(insertdbSQL); 

			pst.setString(1, ID);
			pst.setString(2, Name);
			pst.setString(3, Email); 
			pst.executeUpdate(); 
		} catch(SQLException e) { 
			System.out.println("InsertDB Exception :" + e.toString()); 
		} finally { 
			Close(); 
		} 
	} 
 
	public void SelectTable() { 
		try { 
			stat = con.createStatement(); 
			rs = stat.executeQuery(selectSQL); 
			System.out.println("ID\t\tName\t\tEMAIL"); 
			while(rs.next()) { 
				System.out.println(rs.getString("teacherID")+"\t\t"+ 
						rs.getString("teacher_Name") + "\t\t"+rs.getString("teacher_Email")); 
			} 
		} catch(SQLException e) { 
			System.out.println("DropDB Exception :" + e.toString()); 
		} finally { 
			Close(); 
		} 
	} 
	
	private void Close() { 
		try { 
			if(rs!=null) { 
				rs.close(); 
				rs = null; 
			} 
			if(stat!=null) { 
				stat.close(); 
				stat = null; 
			} 
			if(pst!=null) { 
				pst.close(); 
				pst = null; 
			} 
		} 
		catch(SQLException e) { 
			System.out.println("Close Exception :" + e.toString()); 
		} 
	} 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost!!");     
		String name, email, id;
		id    = request.getParameter("id");
		name  = request.getParameter("name");
		email = request.getParameter("email");

		System.out.println("id:"+id);
		Teachers test = new Teachers(); 
		System.out.println("test!!");

		test.insertTable(id, name,email); 
		System.out.println("inserts!!");     
		response.sendRedirect("classList");
	}
}