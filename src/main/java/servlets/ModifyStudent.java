package servlets;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDao;
import models.Student;

/**
 * Servlet implementation class ModifyStudent
 */
public class ModifyStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String op=request.getParameter("op");
		int id=Integer.parseInt(request.getParameter("id"));
		StudentDao dao=new StudentDao();
		
		
		RequestDispatcher rd=request.getRequestDispatcher("edit_student.jsp");			
		Properties properties=new Properties();
		dao.getStudentById(id, properties);
		Set<Object> keySet = properties.keySet();
		for(Object obj : keySet) {
			request.setAttribute(obj.toString(), properties.get(obj));
		}
		rd.forward(request, response);
		
		
		if(op.equals("edit")) {
			
		}else if(op.equals("delete")) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		String op=request.getParameter("button");
		StudentDao dao=new StudentDao();
		Properties properties=new Properties();
		int id=Integer.parseInt(request.getParameter("inputId"));
		
		if(op.equals("update")) {
			
			String firstName=request.getParameter("inputFirstName");
			String lastName=request.getParameter("inputLastName");
			String email=request.getParameter("inputEmail");
			String password=request.getParameter("inputPassword");
			Student student=new Student(id, firstName, lastName, email, password);			
			dao.updateStudent(student, properties);
		}
		else if(op.equals("delete")) {
			dao.deleteStudent(id, properties);
		}
		RequestDispatcher rd=request.getRequestDispatcher("index.jsp");
		dao.getStudents(properties);
		Set<Object> keySet = properties.keySet();
		for(Object obj : keySet) {
			request.setAttribute(obj.toString(), properties.get(obj));
		}
		rd.forward(request, response);
		
	}

}
