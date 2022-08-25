<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>

    <link rel="stylesheet" href="/css/login.css" type="text/css"/>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script src="/js/login.js" type="text/javascript"></script>

</head>
<body>
    <c:if test="${login_report eq false}">
        <p>첫 로그인입니다.</p>
    </c:if>

    <c:if test="${login_report ne false}">
        <p>마지막 로그인 시간 : <c:out value="${login_time}" /></p>
    </c:if>

    <c:if test="${login_count >= 5 }">
        <p>로그인 횟수 : ${login_count}</p>
    </c:if>

    <input type="hidden" id="LOGIN_USER" value="${LOGIN_USER}"/>
    <p>ID : ${LOGIN_USER}</p>

    <button id="btn_modal">시간확인</button>
    <div class="modal">
        <p>현재 시간<input type="text" name="modal_content" value=""/></p>
        <button id="modal_close">닫기</button>
    </div>
    <p><input type="button" id="btn_logout" value="로그아웃"/></p>
</body>
</html>