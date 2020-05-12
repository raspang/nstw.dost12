<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
	<script>
		window.contextRoot = '${contextRoot}'
			window.userRole = 'USER';

		<sec:authorize access="hasRole('ADMIN')">
			window.userRole = 'ADMIN';
		</sec:authorize>
	
	</script>
	
	
	<link href="<c:url value='/static/css/bootstrap.css'/>" rel="stylesheet"></link> 
	<%-- <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link> --%>
	<link href="<c:url value='/static/css/dataTables.bootstrap.css'/>" rel="stylesheet"></link> 
	<link href="<c:url value='/static/css/dataTables.bootstrap4.css'/>" rel="stylesheet"></link> 
	<link href="<c:url value='/static/css/dataTables.jqueryui.css'/>" rel="stylesheet"></link> 
	<link href="<c:url value='/static/css/dataTables.semanticui.css'/>" rel="stylesheet"></link> 
	<link href="<c:url value='/static/css/jquery.dataTables.css'/>" rel="stylesheet"></link>

	<link href="<c:url value='/static/css/responsive.bootstrap.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/responsive.dataTables.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/responsive.foundation.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/responsive.responsive.jqueryui.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/responsive.semanticui.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/buttons.bootstrap.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/buttons.bootstrap4.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/buttons.dataTables.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/buttons.foundation.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/buttons.jqueryui.min.css'/>" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/buttons.semanticui.min.css'/>" rel="stylesheet"></link>
	
	
  <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
 		
</head>

<body>

   <div class="container-fluid">
         <div class="table-wrapper"> 
				<div class="table-title">
				<%@include file="authheader.jsp" %>	
				<span class="lead"><a href="${contextRoot}/listparticipants">List of Voters</a></span>
				&nbsp;|&nbsp;
				<span class="lead">Generated Codes</span>	
				<sec:authorize access="hasRole('ADMIN')">
		  		&nbsp;|&nbsp;<span class="lead"><a href="${contextRoot}/list">List of Users</a></span>
		  		</sec:authorize>	
		  		</div>
		
			<div class="card-body">
			
			<table class="table table-hover table-bordered" id="codes" width="100%" >
	    		<thead>
		      		<tr>
				        <th>Barangay</th>
				        <th>Code</th>
				        <th></th>			       		       
				    </tr>
		    	</thead>
	    	</table>
	    	</div>
	
<%-- 		<sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		<a class="btn btn-primary btn-xs"  href="<c:url value='/newperson' />">Add New</a>
		 	</div>
	 	</sec:authorize> --%>
	 	
		<sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		
		 		<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#mymodalgenerate">Generate</button>
		 	</div>
	 	</sec:authorize> 
	 	
	 	<sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		
		 		<button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#mymodalpdf">Print</button>
		 	</div>
	 	</sec:authorize>
	 	
	 	</div>
	 	
   	</div>

 	<div id="mymodalgenerate" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<form:form class="form-horizontal" action="${contextRoot}/generatecode" method="GET">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Generate Code</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label class="control-label col-md-4">Barangay</label>
							<div class="col-md-8 validate">
								<select name="barangayId" class="form-control" >
									<c:forEach items="${barangays}" var="barangay">
										<option value="${barangay.id}">${barangay.name} (${barangay.countGenerated} generated)</option>
									</c:forEach>
								</select>
							</div>
						</div>


						<div class="form-group">
							<label class="control-label col-md-4">*How Many No.:</label>
							<div class="col-md-8 validate">
								<input type="number" name="printNo" class="form-control" placeholder="Create number codes" required="required"/>
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
	
	
	
	<div id="mymodalpdf" class="modal fade" role="dialog">
		<div class="modal-dialog">

			<form:form target="_blank"  class="form-horizontal" action="${contextRoot}/barangay-pdf" method="GET">
				<!-- Modal content-->
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Generate Code</h4>
					</div>
					<div class="modal-body">

						<div class="form-group">
							<label class="control-label col-md-4">Barangay</label>
							<div class="col-md-8 validate">
								<select name="barangayId" class="form-control" >
									<c:forEach items="${barangays}" var="barangay">
										<option value="${barangay.id}">${barangay.name} (${barangay.countGenerated})</option>
									</c:forEach>
								</select>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-md-4">*How Many No.:</label>
							<div class="col-md-8 validate">
								<input type="number" name="printNo" class="form-control" placeholder="Print a number of codes" required="required"/>
							</div>
						</div>
						

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
						<button type="Submit" class="btn btn-primary">Print</button>
					</div>
				</div>
				
			</form:form>
			
		</div>
	</div>
	
	
	
   	<script src="<c:url value='/static/js/jquery-3.3.1.min.js'/>"></script>
   	<script src="<c:url value='/static/js/jquery.dataTables.js'/>"></script>
   	<script src="<c:url value='/static/js/dataTables.bootstrap.js'/>"></script>
   	<script src="<c:url value='/static/js/bootstrap.min.js'/>"></script>
   	
   	<script src="<c:url value='/static/js/dataTables.buttons.min.js'/>"></script>
   	<script src="<c:url value='/static/js/buttons.print.min.js'/>"></script>
   	
   	<script src="<c:url value='/static/js/dataTables.bootstrap4.js'/>"></script>
   	<script src="<c:url value='/static/js/dataTables.foundation.js'/>"></script>
    <script src="<c:url value='/static/js/dataTables.jqueryui.js'/>"></script>

	 <script src="<c:url value='/static/js/dataTables.responsive.js'/>"></script>
   	<script src="<c:url value='/static/js/responsive.bootstrap.min.js'/>"></script>
   	<script src="<c:url value='/static/js/responsive.bootstrap4.min.js'/>"></script>
   	<script src="<c:url value='/static/js/responsive.foundation.min.js'/>"></script>
   	<script src="<c:url value='/static/js/responsive.jqueryui.min.js'/>"></script>
   	<script src="<c:url value='/static/js/responsive.semanticui.min.js'/>"></script>
   	
   	<script src="<c:url value='/static/js/buttons.bootstrap.min.js'/>"></script>
   	<script src="<c:url value='/static/js/buttons.colVis.min.js'/>"></script>
   	<script src="<c:url value='/static/js/buttons.foundation.min.js'/>"></script>
   	<script src="<c:url value='/static/js/buttons.html5.min.js'/>"></script>
   	<script src="<c:url value='/static/js/buttons.jqueryui.min.js'/>"></script>
   	
   	
   	<script src="<c:url value='/static/js/app.js'/>"></script> 
</body>
</html>