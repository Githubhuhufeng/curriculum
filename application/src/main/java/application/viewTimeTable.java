package application;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;  
import javax.servlet.http.*;

import com.google.gson.Gson;

import java.io.*;  
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Servlet implementation class login
 */
public class ViewTimeTable extends HttpServlet 
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
    public ViewTimeTable() 
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
    public TimeTableObj[] getTimeTable(String userId) 
    { 
    	System.out.println("getTimeTable start"); 
    	TimeTableObj[] timeTable =null;
		try 
		{ 
			String selectSQL =	"SELECT "+ 
									"class.id as ClassId, "+
									"class.name as ClassName, "+
									"class.location as ClassLocation, "+
									"time_reference.week as week, "+
									"time_reference.start_time as start_time, "+
									"time_reference.end_time as end_time, "+
									"time_reference.week as week "+
								"FROM "+
									"class, "+
									"curriculum, "+
									"class_time, "+
									"time_reference "+
								"WHERE "+
									"time_reference.time_id=class_time.time_id and "+
									"curriculum.classID=class_time.class_id and "+
									"class.id = class_time.class_id and "+
									"userId='"+userId+"'"+
								"ORDER BY "+
									"ClassId,start_time";
			stat = con.createStatement(); 
			rs = stat.executeQuery(selectSQL); 
			//System.out.println(selectSQL);
			int rowcount =0;
			if (rs.last()) 
			{
				  rowcount = rs.getRow();
				  rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
			}
			timeTable = new TimeTableObj[rowcount];
			int count =0;
			while(rs.next()) 
			{ 
				Calendar today = Calendar.getInstance();
				int dayOfWeek =today.get(Calendar.DAY_OF_WEEK);
	
				//2 星期一  		1
				//3 星期二		2
				//4 星期三		3
				//5 星期四		4
				//6 星期五		5
				//7 星期六		6
				//1 星期日		0
				dayOfWeek -= 1;
				if(dayOfWeek==0)
					dayOfWeek=7;
				String diffString = rs.getString("week");
				int diff = dayOfWeek - Integer.parseInt(diffString);
				
				today.add(today.DATE,diff);
				Date date=new Date();//取时间
				date=today.getTime();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(date);
				
				
				TimeTableObj tempList = new TimeTableObj();
				tempList.TaskID = count+"";
				tempList.OwnerID = rs.getString("ClassId");
				tempList.Title = rs.getString("ClassName");
				tempList.Description = rs.getString("ClassLocation");
				tempList.Start = dateString+" "+rs.getString("start_time");
				tempList.End = dateString+" "+rs.getString("end_time");

				timeTable[count] = tempList;
				count++;
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
		System.out.println("getTimeTable end"); 
		return timeTable;
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
		System.out.println("doGet start"); 
		String userId ="";
		Object someObject =null;
		try
		{
			HttpSession session = request.getSession(true);
			userId = session.getAttribute("userId").toString();
			
		}
		catch(Exception ex)
		{
			
		}
		someObject = getTimeTable(userId);
		
		
		String json = new Gson().toJson(someObject);
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);
	    System.out.println("doGet end"); 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{

	}
}

class TimeTableObj
{
	String TaskID ="";
	String OwnerID ="";
	String Title = "";
	String Description ="";
	String StartTimezone = null;
	String Start= "";
	String End = "";
	String EndTimezone = null;
	String RecurrenceRule = null;
	String RecurrenceID = null;
	String RecurrenceException = null;
	boolean IsAllDay = false;
}