<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Participant Registration Form</title>

<link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet"></link> 
	 <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	
	
</head>

<body>
	
	<div class="container">
		<%@include file="authheader.jsp"%>

		<div class="well lead">Participant Registration Form</div>
		<form:form method="POST" modelAttribute="voter" class="form-horizontal">
		

			
			<div class="container">
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="firstName">First Name</label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName" class="form-control input-sm" required="required"/>
						<div class="has-error">
							<form:errors path="firstName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="middleName">Middle Name</label>
					<div class="col-md-7">
						<form:input type="text" path="middleName" id="middleName" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="middleName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="lastName">Last Name</label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName" class="form-control input-sm" required="required"/>
						<div class="has-error">
							<form:errors path="lastName" class="help-inline" />
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="company">Company</label>
					<div class="col-md-7">
						<form:input type="text" path="company" id="company" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="company" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="designation">Designation</label>
					<div class="col-md-7">
						<form:input type="text" path="designation" id="designation" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="designation" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			
				<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="contact">Contact</label>
					<div class="col-md-7">
						<form:input type="text" path="contact" id="contact" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="contact" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="contact">Email</label>
					<div class="col-md-7">
						<form:input type="email" path="email" id="email" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="email" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="age">Age</label>
					<div class="col-md-7">
						<form:input type="number" path="age" id="age" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="age" class="help-inline" />
						</div>
					</div>
				</div>
			</div>
					
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="lastName">Gender</label>
					<div class="col-md-7">				
						<form:select path="gender" items="${genders}" class="form-control" />
					</div>
				</div>
			</div>


				

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="status">Civil Status</label>
					<div class="col-md-7">
						<form:input type="text" path="status" id="status" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="status" class="help-inline" />
						</div>
						
						
					</div>
				</div>
			</div>			
							
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="business">Business Line</label>
					<div class="col-md-7">				
						<form:select path="business" items="${businessLines}" class="form-control" />
					</div>
				</div>
			</div>
											

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-label" for="vip">Is VIP</label>
					<div class="col-md-1">				
							<form:checkbox path="vip" id="vip" class="form-control"/>
							
					</div>
				</div>
			</div>
			
			

			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"
								class="btn btn-primary btn-sm" />
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register"
								class="btn btn-primary btn-sm" />
						</c:otherwise>
					</c:choose>
					 or <a href="<c:url value='/listparticipants' />">Cancel</a>
				</div>
			</div>
			</div>
			<form:input type="hidden" path="id"/>
			<form:input type="hidden" path="code"/>
		</form:form>
	</div>

	<div id="mymodal" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<form:form class="form-horizontal"
				action="${contextRoot}/newbarangay" method="GET">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Barangay</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label class="control-label col-md-4">Barangay</label>
							<div class="col-md-8 validate">
								<%-- <form:input type="text"  class="form-control" placeholder="Barangay Name" /> --%>
								<input type="text" name="name" class="form-control"
									placeholder="Barangay Name" />
							</div>
						</div>

					</div>
					<div class="modal-footer">

						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="Submit" class="btn btn-primary">Save</button>
					</div>
				</div>
			</form:form>

		</div>
	</div>

	<div id="mymodal2" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<form:form class="form-horizontal"
				action="${contextRoot}/newpurok" method="GET">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Purok</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label class="control-label col-md-4">Barangay</label>
							<div class="col-md-8 validate">
								<select name="barangayId" class="form-control" >
									<c:forEach items="${barangays}" var="barangay">
										<option value="${barangay.id}">${barangay.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4">Purok</label>
							<div class="col-md-8 validate">
								<input type="text" name="name" class="form-control" placeholder="Purok Name" />
							</div>
						</div>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="Submit" class="btn btn-primary">Save</button>
					</div>
				</div>
				
			</form:form>
			
		</div>
	</div>

 	<script src="<c:url value='/static/js/jquery-3.3.1.min.js'/>"></script>
  	<script src="<c:url value='/static/js/bootstrap.js'/>"></script>
</body>
</html>