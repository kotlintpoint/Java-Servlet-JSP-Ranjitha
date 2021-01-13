package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import models.Student;
import utils.ConnectionUtils;

public class StudentDao {
	public void saveStudent(Student student, Properties properties) {
		
		try {
			Connection conn=ConnectionUtils.getConnection();
			if(conn==null) {
				properties.put("errorMsg", "Database Connection Problem");
				//request.setAttribute("errorMsg", "Database Connection Problem");
			}else {
				// 4. Create SQL query 
				String sql="insert into student (first_name, last_name, email, password) values (?,?,?,?)";
				
				// 5. Create Prepared Statement 
				PreparedStatement stmt=conn.prepareStatement(sql);
				
				// 6. add parameteres 
				stmt.setString(1, student.getFirstName());
				stmt.setString(2, student.getLastName());
				stmt.setString(3, student.getEmail());
				stmt.setString(4, student.getPassword());
				
				// 7. Execute Query
				int count=stmt.executeUpdate();
				
			
				if(count>0) {
					properties.put("successMsg", "Inserted Successfully");
					//request.setAttribute("successMsg", "Inserted Successfully");
					//response.getWriter().append("Inserted Successfully");
					
				}else {
					properties.put("errorMsg", "Operation Fail");
					//request.setAttribute("errorMsg", "Operation Fail");
					//response.getWriter().append("OPeration Fail");
				}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//response.getWriter().append("OPeration Fail");
			//request.setAttribute("errorMsg", e.toString());
			properties.put("errorMsg",e.toString());
		}	
	
	}
	
	public void getStudents(Properties properties){		
		try {
			Connection conn=ConnectionUtils.getConnection();
			if(conn==null) {
				properties.put("errorMsg", "Database Connection Problem");
			}
			else {
				String sql="select * from student";
				PreparedStatement stmt=conn.prepareStatement(sql);
				ResultSet rs=stmt.executeQuery();
				ArrayList<Student> students=new ArrayList<Student>();
				while(rs.next()) {					
					int id=rs.getInt("id");
					String firstName=rs.getString("first_name");
					String lastName=rs.getString("last_name");
					String email=rs.getString("email");
					String password=rs.getString("password");
					Student student=new Student(id, firstName, lastName, email, password);
					students.add(student);
				}
				properties.put("students", students);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//response.getWriter().append("OPeration Fail");
			//request.setAttribute("errorMsg", e.toString());
			properties.put("errorMsg",e.toString());
		}	
		
	}
	
	public void getStudentById(int id, Properties properties) {
		try {
			Connection conn=ConnectionUtils.getConnection();
			if(conn==null) {
				properties.put("errorMsg", "Database Connection Problem");
			}
			else {
				String sql="select * from student where id=?";
				PreparedStatement stmt=conn.prepareStatement(sql);
				stmt.setInt(1, id);
				ResultSet rs=stmt.executeQuery();				
				if(rs.next()) {					
					String firstName=rs.getString("first_name");
					String lastName=rs.getString("last_name");
					String email=rs.getString("email");
					String password=rs.getString("password");
					Student student=new Student(id, firstName, lastName, email, password);
					properties.put("student", student);	
				}				
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//response.getWriter().append("OPeration Fail");
			//request.setAttribute("errorMsg", e.toString());
			properties.put("errorMsg",e.toString());
		}
	}
	
	public boolean verifyStudent(String email, String password, Properties properties) {
		boolean flag=false;
		try {
			Connection conn=ConnectionUtils.getConnection();
			if(conn==null) {
				properties.put("errorMsg", "Database Connection Problem");
			}
			else {
				String sql="select * from student where email=? and password=?";
				PreparedStatement stmt=conn.prepareStatement(sql);
				stmt.setString(1, email);
				stmt.setString(2, password);
				ResultSet rs=stmt.executeQuery();				
				if(rs.next()) {					
					int id=rs.getInt("id");
					String firstName=rs.getString("first_name");
					String lastName=rs.getString("last_name");					
					Student student=new Student(id, firstName, lastName, email, password);
					properties.put("student", student);	
					flag=true;
				}
				else {
					properties.put("errorMsg", "User Not Found!!!");	
				}
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//response.getWriter().append("OPeration Fail");
			//request.setAttribute("errorMsg", e.toString());
			properties.put("errorMsg",e.toString());
		}
		return flag;
	}
	
	public void updateStudent(Student student, Properties properties) {
			
			try {
				Connection conn=ConnectionUtils.getConnection();
				if(conn==null) {
					properties.put("errorMsg", "Database Connection Problem");
					//request.setAttribute("errorMsg", "Database Connection Problem");
				}else {
					// 4. Create SQL query 
					String sql="update student set first_name=?, last_name=?, email=?, password=? where id=?";
					
					// 5. Create Prepared Statement 
					PreparedStatement stmt=conn.prepareStatement(sql);
					
					// 6. add parameteres 
					stmt.setString(1, student.getFirstName());
					stmt.setString(2, student.getLastName());
					stmt.setString(3, student.getEmail());
					stmt.setString(4, student.getPassword());
					stmt.setInt(5, student.getId());
					
					// 7. Execute Query
					int count=stmt.executeUpdate();					
				
					if(count>0) {
						properties.put("successMsg", "Updated Successfully");
						//request.setAttribute("successMsg", "Inserted Successfully");
						//response.getWriter().append("Inserted Successfully");
						
					}else {
						properties.put("errorMsg", "Operation Fail");
						//request.setAttribute("errorMsg", "Operation Fail");
						//response.getWriter().append("OPeration Fail");
					}
				
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//response.getWriter().append("OPeration Fail");
				//request.setAttribute("errorMsg", e.toString());
				properties.put("errorMsg",e.toString());
			}	
		
		}
	public void deleteStudent(int id, Properties properties) {
		
		try {
			Connection conn=ConnectionUtils.getConnection();
			if(conn==null) {
				properties.put("errorMsg", "Database Connection Problem");
				//request.setAttribute("errorMsg", "Database Connection Problem");
			}else {
				// 4. Create SQL query 
				String sql="delete from student where id=?";
				
				// 5. Create Prepared Statement 
				PreparedStatement stmt=conn.prepareStatement(sql);
				
				// 6. add parameteres				
				stmt.setInt(1, id);
				
				// 7. Execute Query
				int count=stmt.executeUpdate();					
			
				if(count>0) {
					properties.put("successMsg", "Deleted Successfully");
					//request.setAttribute("successMsg", "Inserted Successfully");
					//response.getWriter().append("Inserted Successfully");
					
				}else {
					properties.put("errorMsg", "Operation Fail");
					//request.setAttribute("errorMsg", "Operation Fail");
					//response.getWriter().append("OPeration Fail");
				}
			
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//response.getWriter().append("OPeration Fail");
			//request.setAttribute("errorMsg", e.toString());
			properties.put("errorMsg",e.toString());
		}	
	
	}
}
