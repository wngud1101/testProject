<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>로그인화면</title>

    <link rel="stylesheet" href="/css/login.css" type="text/css"/>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="/js/login.js"></script>


</head>
<body>
    <div id="login_text">로그인화면</div>
    <form name="login_form">
        <input type="text" id="user_id" name="user_id" placeholder="아이디"/>
        <input type="password" id="user_pwd" name="user_pwd" placeholder="비밀번호"/>
        <input type="button" id="btn_login" value="로그인" />
    </form>

    <input type="button" id="signUp" value="회원가입" />
</body>
</html>