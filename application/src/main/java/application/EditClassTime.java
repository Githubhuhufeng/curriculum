package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditClassTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DatabaseConnect dc;

	public EditClassTime() {
		super();
		dc = new DatabaseConnect();
	}
	
	private ArrayList<Map<String, Object>> getClassInfo() {
		return dc.queryClassInfo();
	}
	
	
	private ArrayList<Map<String, Object>> getTimeList() {
		return dc.queryClassTime();
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            request.setAttribute("timeList", getTimeList());
            request.setAttribute("classInfo", getClassInfo());
            getServletConfig().getServletContext().getRequestDispatcher("/editClassTime.jsp").forward(request, response);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
