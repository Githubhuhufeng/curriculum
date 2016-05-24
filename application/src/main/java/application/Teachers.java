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
    String name,email,id;
    private Connection con = null; //Database objects 
    //連接object 
    private Statement stat = null; 
    //執行,傳入之sql為完整字串 
    private ResultSet rs = null; 
    //結果集 
    private PreparedStatement pst = null; 
    //執行,傳入之sql為預儲之字申,需要傳入變數之位置 
    //先利用?來做標示 
    
    private String dropdbSQL = "DROP TABLE user "; 
    
    private String createdbSQL = "CREATE TABLE teacher (" + 
      "    teacherID     VARCHAR(20) " + 
      "  , teacher_Name    VARCHAR(256) " + 
      "  , teacher_Email  VARCHAR(256))"; 
    
    private String insertdbSQL = "insert into teacher(teacherID,teacher_Name,teacher_Email)VALUES(?,?,?) " ; 
        //"select ifNULL(max(id),0)+1,? FROM teacher"; 
    
    private String selectSQL = "select * from teacher "; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Teachers() {
        super();
        // TODO Auto-generated constructor stub
        try { 
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
          catch(SQLException x) { 
            System.out.println("Exception :"+x.toString()); 
          } 
          
    }
  //建立table的方式 
    //可以看看Statement的使用方式 
    public void createTable() 
    { 
      try 
      { 
        stat = con.createStatement(); 
        stat.executeUpdate(createdbSQL); 
      } 
      catch(SQLException e) 
      { 
        System.out.println("CreateDB Exception :" + e.toString()); 
      } 
      finally 
      { 
        Close(); 
      } 
    } 
    //新增資料 
    //可以看看PrepareStatement的使用方式 
    public void insertTable( String ID,String Name,String Email) 
    { 
      try 
      { 
        pst = con.prepareStatement(insertdbSQL); 
        
        pst.setString(1, ID);
        pst.setString(2, Name);
        pst.setString(3, Email); 
        pst.executeUpdate(); 
      } 
      catch(SQLException e) 
      { 
        System.out.println("InsertDB Exception :" + e.toString()); 
      } 
      finally 
      { 
        Close(); 
      } 
    } 
    //刪除Table, 
    //跟建立table很像 
    public void dropTable() 
    { 
      try 
      { 
        stat = con.createStatement(); 
        stat.executeUpdate(dropdbSQL); 
      } 
      catch(SQLException e) 
      { 
        System.out.println("DropDB Exception :" + e.toString()); 
      } 
      finally 
      { 
        Close(); 
      } 
    } 
    //查詢資料 
    //可以看看回傳結果集及取得資料方式 
    public void SelectTable() 
    { 
      try 
      { 
        stat = con.createStatement(); 
        rs = stat.executeQuery(selectSQL); 
        System.out.println("ID\t\tName\t\tEMAIL"); 
        while(rs.next()) 
        { 
          System.out.println(rs.getString("teacherID")+"\t\t"+ 
              rs.getString("teacher_Name")+"\t\t"+rs.getString("teacher_Email")); 
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		id = request.getParameter("id");
		name =request.getParameter("name");
		email =request.getParameter("email");
		
		System.out.println("id:"+id);
		
		Teachers test = new Teachers(); 
		System.out.println("test!!");
		     
		     
		    test.insertTable(id, name,email); 
		    System.out.println("inserts!!");     
		response.sendRedirect("success.jsp");
	}

}