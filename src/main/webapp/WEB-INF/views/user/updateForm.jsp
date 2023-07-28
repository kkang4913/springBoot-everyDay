<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../../include/header.jsp" %>

<div id="container">

	<form>
		<input type="hidden" id="id" value="${principal.user.id}">
		<div class="form-group">

			<label for="username">Username</label>
			<input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
		</div>
		<c:choose>
			<c:when test="${empty principal.user.oauth}">
				<div class="form-group">
					<label for="password">User password</label>
					<input type="password" class="form-control" placeholder="Enter password" id="password">
				</div>
				<div class="form-group">
					<label for="nickname">Nickname</label>
					<input type="text" value="${principal.user.nickname}" class="form-control" placeholder="Enter nickname" id="nickname">
				</div>
			</c:when>
		<c:otherwise>
				<div class="form-group">
					<label for="nickname">Nickname</label>
					<input type="text" value="${principal.user.nickname}" class="form-control" placeholder="Enter nickname" id="nickname" readonly>
				</div>
		</c:otherwise>
		</c:choose>
	</form>
		<button id="btn-update" class="btn btn-primary">수정</button>

</div>

<%@ include file="../../include/footer.jsp" %>
<script src="${root}/resources/static/js/User.js" type="text/javascript"></script>
