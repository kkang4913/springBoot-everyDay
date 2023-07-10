<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<%@ include file="../../include/header.jsp" %>


<div class="container">
    <br>
    <button class="btn btn-secondary" onclick="history.back()">글목록</button>
    <button id="btn -update" class="btn btn-warning">수정</button>
    <button id="btn -delete" class="btn btn-danger">삭제</button>
    <br><br>
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

