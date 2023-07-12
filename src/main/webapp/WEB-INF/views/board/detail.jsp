<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<%@ include file="../../include/header.jsp" %>


<div class="container">
    <br>
    <button class="btn btn-secondary" onclick="history.back()">글목록</button>
    <c:if test="${board.userid.id == principal.user.id}">
        <a href="/board/${board.userid.id}/updateForm" class="btn btn-warning">수정</a>
        <button id="btn-delete" class="btn btn-danger">삭제</button>
    </c:if>
    <br><br>
        로그인 한 사용자 id : ${principal.user.id}<br>
        게시글 작성한 사용자 id : ${board.userid.id}<br>
        <hr>
        게시글 번호 : <span id="id"><i>${board.id}</i></span><br>
        작성자     : <span><i>${board.userid.username}</i></span>
        <hr>

        <div>
            <h3>${board.title}</h3>
        </div>
        <hr />
        <div>
            <div>${board.content}</div>
        </div>
</div>

<%@ include file="../../include/footer.jsp" %>


<script src="${root}/resources/static/js/Board.js"></script>

