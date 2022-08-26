package com.example.studyproject2.controller;

import com.example.studyproject2.service.UserService;
import com.example.studyproject2.userVO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
    Ajax 처리를 위한 컨트롤러
 */
@RestController
public class ResponseController {
    private final String COOKIE_NAME = "LOGIN_USER";
    @Autowired
    private UserService userService;

    //아이디 중복확인
    @PostMapping("id-check")
    public boolean idCheck(@RequestParam("userId")String userId){
        boolean dbResult = userService.idCheck(userId);

        return dbResult;
    }

    //회원가입 정보 DB에 넣는 메서드
    @ResponseBody
    @PostMapping("sign-up-check")
    public boolean signUpCheck(@RequestParam("userId")String userId, @RequestParam("userPwd")String userPwd){
        UserVO userVO = new UserVO();

        userVO.setUserId(userId);
        userVO.setUserPwd(userPwd);

        boolean dbResult = userService.signUp(userVO);

        return dbResult;
    }

    //로그인 id, pwd 비교
    @ResponseBody
    @PostMapping("login-test")
    public boolean loginTest(HttpServletRequest request, HttpServletResponse response, Model model){
        String userId = request.getParameter("userId");
        String userPwd = request.getParameter("userPwd");

        boolean dbResult = userService.loginCheck(userId, userPwd);

        if(dbResult == true){
            //쿠키 생성
            Cookie loginUser = new Cookie(COOKIE_NAME, userId);
            response.addCookie(loginUser);
        }

        return dbResult;
    }

    //로그아웃 처리
    @ResponseBody
    @PostMapping("logout")
    public boolean logout(HttpServletResponse response){
        boolean result = true;
        //쿠키 삭제
        Cookie cookie_delete = new Cookie(COOKIE_NAME, null);
        cookie_delete.setMaxAge(0);
        response.addCookie(cookie_delete);

        return result;
    }
}
