package application;



import javax.servlet.ServletException;  
import javax.servlet.http.*;  
import java.io.*;  
import java.sql.*;
/**
 * Servlet implementation class register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con = null;
	private Statement stat = null; 
	private ResultSet rs = null; 
	private PreparedStatement pst = null;  

	public Register() {
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

	public void insertTable( String email,String name,String passwd) {
		String insertdbSQL = "insert into user(id,email,name,encrypted_password	) " + 
				"select ifNULL(max(id),0)+1,?,?,password(?) FROM user";
		try { 
			pst = con.prepareStatement(insertdbSQL); 

			pst.setString(1, email);
			pst.setString(2, name);
			pst.setString(3, passwd); 
			pst.executeUpdate(); 
		} catch(SQLException e) { 
			System.out.println("InsertDB Exception :" + e.toString()); 
		} finally { 
			close(); 
		} 
	} 

	public void selectTable() { 
		String selectSQL = "select * from user "; 
		try { 
			stat = con.createStatement(); 
			rs = stat.executeQuery(selectSQL); 
			System.out.println("ID\t\tName\t\tPASSWORD"); 
			while(rs.next()) { 
				System.out.println(rs.getInt("id")+"\t\t"+ 
						rs.getString("name")+"\t\t"+rs.getString("passwd")); 
			} 
		} catch(SQLException e) { 
			System.out.println("DropDB Exception :" + e.toString()); 
		} finally { 
			close(); 
		} 
	} 
	
	private void close() { 
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
		String name, email, pass;

		name =request.getParameter("name");
		email =request.getParameter("email");
		pass = request.getParameter("pass");
		System.out.println("pass:" + pass);

		Register test = new Register(); 
		System.out.println("test!!");


		test.insertTable(email, name, pass); 
		System.out.println("inserts!!");     
		response.sendRedirect("logIn.jsp");
	}

}