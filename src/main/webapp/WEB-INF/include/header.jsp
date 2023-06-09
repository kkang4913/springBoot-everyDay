<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<!--해당 구문은 로그인이 되었는지 확인한 뒤, 해당 정보를 'principal'이라는 변수에 저장하여 가져오는 작업을 수행합니다.-->
<sec:authorize access="isAuthenticated()">
  <sec:authentication property="principal" var="principal"/>
</sec:authorize>


<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Blog</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <a class="navbar-brand" href="/">OOTD</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <c:choose>
      <c:when test="${empty principal}">
        <ul class="navbar-nav">
          <li class="nav-item"><a class="nav-link" href="${root}/auth/loginForm">로그인</a></li>
          <li class="nav-item"><a class="nav-link" href="${root}/auth/joinForm">회원가입</a></li>
        </ul>
      </c:when>
      <c:otherwise>
        <ul class="navbar-nav">
          <li class="nav-item"><a class="nav-link" href="${root}/board/saveForm">글쓰기</a></li>
          <li class="nav-item"><a class="nav-link" href="${root}/user/updateForm">회원정보</a></li>
          <li class="nav-item"><a class="nav-link" href="${root}/logout">로그아웃</a></li>
        </ul>
      </c:otherwise>
    </c:choose>
  </div>
</nav>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

</body>