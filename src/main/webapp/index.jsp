<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<jsp:include page="helper/header_links.jsp"></jsp:include>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">
		<jsp:include page="helper/main_navbar.jsp"></jsp:include>
		<jsp:include page="helper/main_sidebar.jsp"></jsp:include>
		
		<div class="content-wrapper">
			
			<div class="col-md-6">
			<h1>Index Page Here</h1>
			 <c:if test="${requestScope.successMsg!=null}">
			 	  <div class="alert alert-success alert-dismissible">
                  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                  <h5><i class="icon fas fa-check"></i> Alert!</h5>
                  ${requestScope.successMsg}
                </div>
			</c:if>
			<c:if test="${requestScope.errorMsg!=null}">
				 <div class="alert alert-danger alert-dismissible">
                  <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
                  <h5><i class="icon fas fa-ban"></i> Alert!</h5>
                  ${requestScope.errorMsg}
                </div>
			</c:if>
			
			 <!-- general form elements -->
            <div class="card card-primary">
              <div class="card-header">
                <h3 class="card-title">New Student</h3>
              </div>
              <!-- /.card-header -->
              <!-- form start -->
              <form action="Student" method="post">
                <div class="card-body">
                  <div class="form-group">
                    <label for="inputEmail">Email address</label>
                    <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="Enter email" required>
                  </div>
                  <div class="form-group">
                    <label for="inputFirstName">First Name</label>
                    <input type="text" class="form-control" id="inputFirstName" name="inputFirstName" placeholder="Enter First Name" required>
                  </div>
                  <div class="form-group">
                    <label for="inputLastName">Last Name</label>
                    <input type="text" class="form-control" id="inputLastName" name="inputLastName" placeholder="Enter Last Name">
                  </div>
                  <div class="form-group">
                    <label for="exampleInputPassword1">Password</label>
                    <input type="password" class="form-control" id="inputPassword" name="inputPassword" placeholder="Password">
                  </div>
                  
                </div>
                <!-- /.card-body -->

                <div class="card-footer">
                  <button type="submit" class="btn btn-primary">Submit</button>
                </div>
              </form>
              
              
              </div>
            </div>
            
            <!-- Table Design -->
             <div class="card">
              <div class="card-header">
                <h3 class="card-title">Students Data</h3>
              </div>
              <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-bordered">
                  <thead>
                    <tr>
                      <th style="width: 10px">#</th>
                      <th>First Name</th>
                      <th>Last Name</th>
                      <th>Eamil</th>
                      <th>#</th>
                      <th>#</th>
                    </tr>
                  </thead>
                  <tbody>
                  	<c:forEach var="student" items="${requestScope.students}">
                  		<tr>
	                      <td>${student.id }</td>
	                      <td>${student.firstName}</td>
	                      <td>${student.lastName}</td>
	                      <td>${student.email}</td>
	                      <td><a href="ModifyStudent?id=${student.id}&op=edit">Edit</a></td>
	                      <td><a href="ModifyStudent?id=${student.id}&op=delete">Delete</a></td>
	                    </tr>
                  	</c:forEach>
                    
                   
                  </tbody>
                </table>
              </div>
              <!-- /.card-body -->
              <div class="card-footer clearfix">
                <ul class="pagination pagination-sm m-0 float-right">
                  <li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
                  <li class="page-item"><a class="page-link" href="#">1</a></li>
                  <li class="page-item"><a class="page-link" href="#">2</a></li>
                  <li class="page-item"><a class="page-link" href="#">3</a></li>
                  <li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
                </ul>
              </div>
            </div>
		</div>
		<jsp:include page="helper/footer.jsp"></jsp:include>
	</div>
	
	<jsp:include page="helper/footer_script.jsp"></jsp:include>
</body>
</html>
