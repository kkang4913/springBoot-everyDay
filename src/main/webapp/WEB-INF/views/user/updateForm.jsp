<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>

<div id="container">

	<form>
		<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
		</div>
		<div class="form-group">
			<label for="email">Email address</label>
			<input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
		</div>
		<div class="form-group">
			<label for="password">Password</label>
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
	</form>
		<button id="btn-update" class="btn btn-primary">수정</button>

</div>

<%@ include file="../../include/footer.jsp" %>
<script src="${root}/resources/static/js/User.js" type="text/javascript"></script>
