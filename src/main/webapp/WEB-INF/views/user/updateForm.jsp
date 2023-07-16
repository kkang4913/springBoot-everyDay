<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>

<div id="container">

	<form>
		<input type="hidden" id="id" value="${principal.user.oauth}">
		<div class="form-group">
			<label for="username">Username</label>
			<input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
		</div>
		<c:choose>
			<c:when test="${empty principal.user.oauth}">
				<div class="form-group">
					<label for="password"></label>
					<input type="password" class="form-control" placeholder="Enter password" id="password">
				</div>
				<div class="form-group">
					<label for="email">Email address</label>
					<input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
				</div>
			</c:when>
		<c:otherwise>
				<div class="form-group">
					<label for="email">Email address</label>
					<input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email" readonly>
				</div>
		</c:otherwise>
		</c:choose>
	</form>
		<button id="btn-update" class="btn btn-primary">수정</button>

</div>

<%@ include file="../../include/footer.jsp" %>
<script src="${root}/resources/static/js/User.js" type="text/javascript"></script>