package servlets;

import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.StudentDao;
import models.Student;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String inputEmail=request.getParameter("inputEmail");
		String inputPassword=request.getParameter("inputPassword");
		StudentDao dao=new StudentDao();
		Properties properties=new Properties();
		RequestDispatcher rd=null;
		if(dao.verifyStudent(inputEmail, inputPassword, properties)) {
			// student verified
			//rd=request.getRequestDispatcher("Student");
			
			// Create Session
			Student student=(Student) properties.get("student");
			String fullName=student.getFirstName()+" "+student.getLastName();
			
			HttpSession session=request.getSession();
			session.setAttribute("email", inputEmail);				
			session.setAttribute("name", fullName);
			
			response.sendRedirect("Student");
		}else {
			// student verification failed
			rd=request.getRequestDispatcher("login.jsp");
			Set<Object> keySet = properties.keySet();
			for(Object obj : keySet) {
				request.setAttribute(obj.toString(), properties.get(obj));
			}
			rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
	}

}
