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
/**
 * Servlet implementation class login
 */
public class viewTimeTable extends HttpServlet 
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
    public viewTimeTable() 
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
    public Map[][] getTimeTable(String userId) 
    { 
    	System.out.println("getTimeTable start"); 
		Map[][] timeTable = new Map[7][14];
		try 
		{ 
			String selectSQL =	"SELECT "+ 
									"class.id as ClassId, "+
									"class.name as ClassName, "+
									"class.location as ClassLocation, "+
									"time_reference.week as week, "+
									"time_reference.section as section "+
								"FROM "+
									"class, "+
									"curriculum, "+
									"class_time, "+
									"time_reference "+
								"WHERE "+
									"time_reference.time_id=class_time.time_id and "+
									"curriculum.classID=class_time.class_id and "+
									"class.id = class_time.class_id and "+
									"userId='"+userId+"'";
			stat = con.createStatement(); 
			rs = stat.executeQuery(selectSQL); 
			
			while(rs.next()) 
			{ 
				Map<String, Object> tempList = new HashMap<String, Object>();
				tempList.put("ClassId", rs.getString("ClassId"));
				tempList.put("ClassName", rs.getString("ClassName"));
				tempList.put("ClassLocation", rs.getString("ClassLocation"));
				int week = Integer.parseInt(rs.getString("week"));
				int section = Integer.parseInt(rs.getString("section"));
				timeTable[week][section] = tempList;
			} 
		} 
		catch(SQLException e) 
		{ 
			System.out.println("DropDB Exception :" + e.toString()); 
		} 
		finally 
		{ 
			Close(); 
		} 
		System.out.println("getTimeTable end"); 
		return timeTable;
	} 
    //完整使用完資料庫後,記得要關閉所有Object 
    //否則在等待Timeout時,可能會有Connection poor的狀況 
	private void Close() 
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
		System.out.println("doGet start"); 
		String userId ="";
		try
		{
			HttpSession session = request.getSession(true);
			userId = session.getAttribute("userId").toString();
		}
		catch(Exception ex)
		{
			
		}
		
		String url="/viewTimeTable.jsp"; //relative url for display jsp page

	    ServletContext sc = getServletContext();
	    RequestDispatcher rd = sc.getRequestDispatcher(url);

	    //request.setAttribute("accountList", getTimeTable(userId) );
	    request.setAttribute("timeTable", getTimeTable(userId) );
	    rd.forward(request, response);
	  
	    System.out.println("doGet end"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}

}