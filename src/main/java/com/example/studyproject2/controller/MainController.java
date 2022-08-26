package com.example.studyproject2.controller;

import com.example.studyproject2.service.UserService;

import com.example.studyproject2.userVO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
    View 처리를 위한 컨트롤러
 */
@Controller
public class MainController {
    @Autowired
    private UserService userService;

    //회원가입 화면
    @GetMapping("sign-up")
    public String signUp(){
        return "sign-up";
    }

    //로그인 화면
    @GetMapping("login")
    public String login(){
        return "login";
    }

    //로그인 완료 화면
    @GetMapping("login-success")
    public String loginSuccess(HttpServletRequest request, Model model){
        UserVO userVO = new UserVO();
        String userId = request.getParameter("userId");
        boolean loginTimeResult = false;

        //String my_cookie = COOKIE_NAME.getValue();

//        if(my_cookie == null || user_id == null){
//            return "login";
//        }

        //로그인 성공시 현재 시각 저장
        userService.inputLoginReport(userId);

        //로그인 시간 및 횟수 확인
        UserVO loginReport = userService.findLoginReport(userId);

        if(loginReport.getLoginCount() > 1){ //첫 로그인이 아닐 경우
            loginTimeResult = true;
            model.addAttribute("loginTime", loginReport.getLoginTime());
        }

        model.addAttribute("LOGIN_USER", userId); //로그인 완료 시 아이디 표시
        model.addAttribute("loginReport",loginTimeResult); //첫 로그인인지 아닌지 표시
        model.addAttribute("loginCount", loginReport.getLoginCount()); //view에 로그인 횟수 저장

        return "login-success";
    }
}
