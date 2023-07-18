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
        <!-- 댓글 -->
        <div class="card">
            <div class="card-body">
                <textarea class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button class="btn btn-primary">등록</button>
            </div>
        </div>
        <br>
        <div class="card">
            <div class="card-header">댓글목록</div>
            <ul id="reply-box" class="list-group">
                <c:if test="${not empty board.replys}">
                    <c:forEach var="reply" items="${board.replys}">
                        <li id="reply--1" class="list-group-item d-flex justify-content-between">
                            <div>${reply.content}</div>
                            <div class="d-flex">
                                <c:if test="${not empty reply.id}">
                                    <div class="font-italic">작성자 : ${reply.user.username}&nbsp;</div>
                                    <button class="badge">삭제</button>
                                </c:if>
                            </div>
                        </li>
                    </c:forEach>
                </c:if>
            </ul>
        </div>
</div>

<%@ include file="../../include/footer.jsp" %>


<script src="${root}/resources/static/js/Board.js"></script>

