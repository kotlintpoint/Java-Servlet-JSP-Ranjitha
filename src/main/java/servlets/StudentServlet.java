package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import models.Student;
import utils.ConnectionUtils;

/**
 * Servlet implementation class StudentServlet
 */
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	
		processRequest(request, response);
		
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getMethod());
		
		RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
		StudentDao dao=new StudentDao();
		Properties properties=new Properties();
		if(request.getMethod().equals("GET")) {		
			dao.getStudents(properties);						
		}else if(request.getMethod().equals("POST")) {
			String firstName=request.getParameter("inputFirstName");
			String lastName=request.getParameter("inputLastName");
			String email=request.getParameter("inputEmail");
			String password=request.getParameter("inputPassword");						
			Student student=new Student(firstName, lastName, email, password);			
			dao.saveStudent(student, properties);
			dao.getStudents(properties);
		}
		Set<Object> keySet = properties.keySet();
		for(Object obj : keySet) {
			request.setAttribute(obj.toString(), properties.get(obj));
		}
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		processRequest(request, response);
		
	}

}
