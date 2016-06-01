package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnect {
	private Connection con = null;
    private Statement stat = null; 
    private ResultSet rs = null; 
    private PreparedStatement pst = null;  
	
	public DatabaseConnect() {
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
	
	public void execute(String sql) {
		try {
	        pst = con.prepareStatement(sql);
	        pst.executeUpdate();
    	} catch(SQLException e) { 
    		System.out.println("SQL :" + sql);
    		System.out.println("InsertDB Exception :" + e.toString()); 
    	} finally { 
    		Close(); 
    	} 
	}
	
	// Close DB Connect
    private void Close() { 
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
}
