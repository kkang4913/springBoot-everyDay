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

</div>

	<%@ include file="../../include/footer.jsp" %>
