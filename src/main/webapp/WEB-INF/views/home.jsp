<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp" %>

<div class="container mt-2 d-flex" id="search"></div>

<div class="container " id="boardList"></div>
<ul style="display: flex; justify-content: center;" id="pagination" class="pagination"></ul>


<script src="${root}/resources/static/js/BoardList.js" type="text/javascript"> </script>
<!--<script src="${root}/resources/static/js/Paging.js" type="text/javascript"> </script>-->
<%@ include file="../include/footer.jsp" %>
