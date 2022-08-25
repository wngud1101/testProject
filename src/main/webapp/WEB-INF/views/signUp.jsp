<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>회원가입화면</title>

    <link rel="stylesheet" href="/css/singUp.css" type="text/css"/>
    <script src="/js/jquery-3.6.0.min.js"></script>
    <script src="/js/signUp.js" type="text/javascript"></script>


</head>
<body>
    회원가입하기
    <div>
        <form name="signUp">
            <p><input type="text" id="user_id" name="user_id" placeholder="아이디 입력"/>
                <input type="button" id="btn_idCheck" value="중복확인" /></p>
            <p><input type="text" id="user_pwd" name="user_pwd" placeholder="비밀번호 입력"/></p>
            <input type="button" id="btn_signUp" value="회원가입하기"/>
        </form>
    </div>
</body>
</html>