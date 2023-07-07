<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>

<div id="container">

	<form>
		<div class="form-group">
			<label for="username">Username</label> <input type="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="email">Email address</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
	</form>
		<button id="btn-save" class="btn btn-primary">회원가입</button>

</div>

<%@ include file="../../include/footer.jsp" %>
<script src="${root}/resources/static/js/User.js" type="text/javascript"></script>
