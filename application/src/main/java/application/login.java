package application;

import javax.servlet.ServletException;  
import javax.servlet.http.*;  
import java.io.*;  
import java.sql.*;
/**
 * Servlet implementation class login
 */
public class Login extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private String userName,pass;
    private Connection con = null; //Database objects //連接object 
    private Statement stat = null; //執行,傳入之sql為完整字串 
    private ResultSet rs = null; //結果集 
    private PreparedStatement pst = null; 
    //執行,傳入之sql為預儲之字申,需要傳入變數之位置 
    //先利用?來做標示 
    
    private String selectSQL = "select * from user "; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() 
    {
		super();
		// TODO Auto-generated constructor stub
		try 
		{ 
			Class.forName("com.mysql.jdbc.Driver"); 
			//註冊driver 
			con = DriverManager.getConnection( 
			"jdbc:mysql://140.134.26.84:3308/tomcat?useUnicode=true&characterEncoding=utf-8", 
			"spm","SPMtomcat105"); 
			//取得connection
			
			//jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
			//localhost是主機名,test是database名
			//useUnicode=true&characterEncoding=Big5使用的編碼 
		} 
		catch(ClassNotFoundException e) 
		{ 
			System.out.println("DriverClassNotFound :"+e.toString()); 
		}//有可能會產生sqlexception 
		catch(SQLException x) 
		{ 
			System.out.println("Exception :"+x.toString()); 
		} 
	}

    //查詢資料 
    //可以看看回傳結果集及取得資料方式 
    public String checkUser(String userName,String pass) 
    { 
    	String userId = "";
		try 
		{ 
			final String selectSQL =	"SELECT "+ 
											"id "+
										"FROM "+
											"user "+
										"WHERE "+
											"name='"+userName+"' and "+
											"encrypted_password=password('"+pass+"')";
			stat = con.createStatement(); 
			rs = stat.executeQuery(selectSQL); 
			
			while(rs.next()) 
			{ 
				userId = rs.getString("id");
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
		return userId;
	} 
    //完整使用完資料庫後,記得要關閉所有Object 
    //否則在等待Timeout時,可能會有Connection poor的狀況 
	private void close() 
	{ 
		try 
		{ 
			if(rs!=null) 
			{ 
				rs.close(); 
				rs = null; 
			} 
			if(stat!=null) 
			{ 
				stat.close(); 
				stat = null; 
			} 
			if(pst!=null) 
			{ 
				pst.close(); 
				pst = null; 
			} 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("Close Exception :" + e.toString()); 
		} 
	} 
    


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		// doGet(request, response);
		System.out.println("doPost start");
		pass =request.getParameter("password");
		userName =request.getParameter("id");
		System.out.println("pass:"+pass);
		System.out.println("userName:"+userName);
	
		String userId = checkUser(userName,pass);
		if (!userId.equals(""))
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("userId", userId);
			System.out.println(userId);
			response.sendRedirect("viewTimeTable.jsp");
		}
		else
		{
			response.sendRedirect("index.jsp");
		}
		System.out.println("doPost End");
	}

}