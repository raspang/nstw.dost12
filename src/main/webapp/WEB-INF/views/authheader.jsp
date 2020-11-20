<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="authbar">

	<img src="<c:url value='/static/images/dost.jpg'/>" height="50rem">
	<span>Logged user <strong>${loggedinuser}</strong>, Welcome
	</span> <span class="floatRight">
		<a class="btn"
		href="<c:url value="/logout" />">Logout</a></span>

	<br><br>
	<sec:authorize access="hasRole('ADMIN')">

			<ul class="breadcrumb">
				<li role="presentation" >
					<a href="${contextRoot}/listparticipants" >List of Participants</a>
				</li>
		  		<li role="presentation" >
		  	
	
		  			<a href="${contextRoot}/liststatus">View Status</a>
		
		  		</li>
		  		<li role="presentation">
		  			<a href="${contextRoot}/list">Manage Users</a>
		  		</li>
		  	
		  	</ul>
	
	</sec:authorize>
		  				
</div>
