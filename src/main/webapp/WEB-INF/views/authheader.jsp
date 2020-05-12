
<div class="authbar">
	<span>Dear <strong>${loggedinuser}</strong>, Welcome to List of
		Participants.
	</span> <span class="floatRight"><a class="btn"
		href="<c:url value="/logout" />">Logout</a></span>

	<sec:authorize access="hasRole('ADMIN')">
			<span class="lead"><a href="${contextRoot}/listparticipants">List of Participants</a></span>
		  		&nbsp;|&nbsp;
		  		<span class="lead"><a href="${contextRoot}/liststatus">List of Status</a></span>
		  		&nbsp;|&nbsp;<span class="lead">
		  		<a href="${contextRoot}/list">List of Users</a></span>
	</sec:authorize>
</div>
