<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<%@ include file="../../include/header.jsp" %>


<div class="container">
    <form>
        <input type="hidden" id="id" value="${board.userid.id}">
        <div class="form-group">
            <label for="title">Title</label>
            <input value="${board.title}" type="text" class="form-control" placeholder="Enter title" id="title">
        </div>
        <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
    </form>
        <button id="btn-update" class="btn btn-primary">수정</button>

</div>

<%@ include file="../../include/footer.jsp" %>


<script src="${root}/resources/static/js/Board.js"></script>

