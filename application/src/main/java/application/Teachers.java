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
    //�s��object 
    private Statement stat = null; 
    //����,�ǤJ��sql������r�� 
    private ResultSet rs = null; 
    //���G�� 
    private PreparedStatement pst = null; 
    //����,�ǤJ��sql���w�x���r��,�ݭn�ǤJ�ܼƤ���m 
    //���Q��?�Ӱ��Х� 
    
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
            //���Udriver 
            con = DriverManager.getConnection( 
            "jdbc:mysql://140.134.26.84:3308/tomcat?useUnicode=true&characterEncoding=utf-8", 
            "spm","SPMtomcat105"); 
            //���oconnection

      //jdbc:mysql://localhost/test?useUnicode=true&characterEncoding=Big5
      //localhost�O�D���W,test�Odatabase�W
      //useUnicode=true&characterEncoding=Big5�ϥΪ��s�X 
            
          } 
          catch(ClassNotFoundException e) 
          { 
            System.out.println("DriverClassNotFound :"+e.toString()); 
          }//���i��|����sqlexception 
          catch(SQLException x) { 
            System.out.println("Exception :"+x.toString()); 
          } 
          
    }
  //�إ�table���覡 
    //�i�H�ݬ�Statement���ϥΤ覡 
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
    //�s�W��� 
    //�i�H�ݬ�PrepareStatement���ϥΤ覡 
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
    //�R��Table, 
    //��إ�table�ܹ� 
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
    //�d�߸�� 
    //�i�H�ݬݦ^�ǵ��G���Ψ��o��Ƥ覡 
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
    //����ϥΧ���Ʈw��,�O�o�n�����Ҧ�Object 
    //�_�h�b����Timeout��,�i��|��Connection poor�����p 
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