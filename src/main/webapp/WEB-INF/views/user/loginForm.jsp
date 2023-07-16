<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../../include/header.jsp" %>

<div id="container">

	<form action="${root}/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">UserName</label> <input type="username" name="username" class="form-control" placeholder="Enter username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"><input type="checkbox" class="form-check-input"> Remember me
			</label>
		</div>
		<button id="btn-login" class="btn btn-primary">로그인</button>
	</form>
	<a href="https://kauth.kakao.com/oauth/authorize?client_id=ee23445d02dab269d03fe4fa482ea2dd&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
		<img height="38px" src="/resources/static/image/kakao_button.png">
	</a>

</div>

	<%@ include file="../../include/footer.jsp" %>
