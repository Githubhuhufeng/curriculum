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
	private String name,email,pass;
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
    
    private String createdbSQL = "CREATE TABLE user (" + 
      "    id     INTEGER " + 
      "  , name    VARCHAR(20) " + 
      "  , passwd  VARCHAR(20))"; 
    
    private String insertdbSQL = "insert into user(id,email,name,encrypted_password	) " + 
        "select ifNULL(max(id),0)+1,?,?,password(?) FROM user"; 
    
    private String selectSQL = "select * from user "; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
        close(); 
      } 
    } 
    //新增資料 
    //可以看看PrepareStatement的使用方式 
    public void insertTable( String email,String name,String passwd) 
    { 
      try 
      { 
        pst = con.prepareStatement(insertdbSQL); 
        
        pst.setString(1, email);
        pst.setString(2, name);
        pst.setString(3, passwd); 
        pst.executeUpdate(); 
      } 
      catch(SQLException e) 
      { 
        System.out.println("InsertDB Exception :" + e.toString()); 
      } 
      finally 
      { 
        close(); 
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
        close(); 
      } 
    } 
    //查詢資料 
    //可以看看回傳結果集及取得資料方式 
    public void selectTable() 
    { 
      try 
      { 
        stat = con.createStatement(); 
        rs = stat.executeQuery(selectSQL); 
        System.out.println("ID\t\tName\t\tPASSWORD"); 
        while(rs.next()) 
        { 
          System.out.println(rs.getInt("id")+"\t\t"+ 
              rs.getString("name")+"\t\t"+rs.getString("passwd")); 
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
		
		name =request.getParameter("name");
		email =request.getParameter("email");
		pass = request.getParameter("pass");
		System.out.println("pass:"+pass);
		
		Register test = new Register(); 
		System.out.println("test!!");
		     
		     
		test.insertTable(email, name,pass); 
		System.out.println("inserts!!");     
		response.sendRedirect("success.jsp");
	}

}