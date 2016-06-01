package application;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DatabaseConnect dc;
	
	public UpdateTime() {
		super();
		dc = new DatabaseConnect();
	}
	
	private void insertTable(String classInfo, String time) {
		String insertdbSQL = "UPDATE `class_time` SET `class_id` = " + classInfo + "  WHERE `class_time`.`class_id` = "
				+ classInfo + " AND `class_time`.`time_id` = "+ time;

		dc.execute(insertdbSQL);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("doPost!!");    
		String classInfo = request.getParameter("classInfo");
		String time      = request.getParameter("time");

		insertTable(classInfo, time); 
		System.out.println("inserts!!");     
		response.sendRedirect("classList");
	}
}
