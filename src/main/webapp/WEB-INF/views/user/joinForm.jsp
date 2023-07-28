<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>

<div class="container">
	<form>
		<br>
		<div class="form-group">
			<label for="username">Username(아이디)</label> <input type="text" class="form-control" placeholder="Enter username(아이디)" id="username">
		</div>
		<p id="valid_username"></p>
		<div class="form-group">
			<label for="password">Password(패스워드)</label> <input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<p id="valid_password"></p>
		<div class="form-group">
			<label for="nickname">Email nickname(닉네임)</label> <input type="text" class="form-control" placeholder="Enter nickname" id="nickname">
		</div>
		<p id="valid_nickname"></p>
		<div class="form-group">
			<label for="email">Email address(이메일)</label> <input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		<p id="valid_email"></p>
	</form>
	<button id="btn-join" class="btn btn-primary">Join</button>
</div>

<%@ include file="../../include/footer.jsp" %>
<script src="${root}/resources/static/js/User.js" type="text/javascript"></script>
