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

@Controller
public class MainController {
    private final Cookie COOKIE_NAME = new Cookie("LOGIN_USER", null);

    @Autowired
    private UserService userService;

    //회원가입 화면
    @GetMapping("sign-up")
    public String signUp(){
        return "sign-up";
    }

    //아이디 중복 확인
    @ResponseBody
    @PostMapping("id-check")
    public boolean idCheck(@RequestParam("user_id")String user_id){
        boolean dbResult = userService.id_check(user_id);

        return dbResult;
    }

    //회원가입 정보 DB에 넣는 메서드
    @ResponseBody
    @PostMapping("sign-up-check")
    public boolean signUp_check(@RequestParam("user_id")String user_id, @RequestParam("user_pwd")String user_pwd){
        UserVO userVO = new UserVO();

        userVO.setUser_id(user_id);
        userVO.setUser_pwd(user_pwd);

        boolean dbResult = userService.signUp(userVO);

        return dbResult;
    }

    //로그인 화면
    @GetMapping("login")
    public String login(){
        return "login";
    }

    //로그인 id, pwd 비교
    @ResponseBody
    @PostMapping("login-test")
    public boolean loginTest(HttpServletRequest request, HttpServletResponse response, Model model){
        String user_id = request.getParameter("user_id");
        String user_pwd = request.getParameter("user_pwd");

        boolean dbResult = userService.loginCheck(user_id, user_pwd);

        if(dbResult == true){
            //쿠키 생성
            Cookie login_user = new Cookie("LOGIN_USER", user_id);
            response.addCookie(login_user);
        }

        COOKIE_NAME.setValue(user_id);
        model.addAttribute("LOGIN_USER", user_id);      //쿠키 생성

        return dbResult;
    }

    //로그인 완료 화면
    @GetMapping("login-success")
    public String loginSuccess(HttpServletRequest request, Model model){
        UserVO userVO = new UserVO();
        String user_id = request.getParameter("user_id");
        boolean login_time_result = false;

        String my_cookie = COOKIE_NAME.getValue();

        if(my_cookie == null || user_id == null){
            return "login";
        }

        //로그인 성공시 현재 시각 저장
        userService.insertLoginReport(user_id);

        //로그인 시간 및 횟수 확인
        UserVO login_report = userService.selectLoginReport(user_id);

        if(login_report.getLogin_count() > 1){ //첫 로그인이 아닐 경우
            login_time_result = true;
            model.addAttribute("login_time", login_report.getLogin_time());
        }

        model.addAttribute("LOGIN_USER", user_id); //로그인 완료 시 아이디 표시
        model.addAttribute("login_report",login_time_result); //첫 로그인인지 아닌지 표시
        model.addAttribute("login_count", login_report.getLogin_count()); //view에 로그인 횟수 저장

        return "login-success";
    }

    //로그아웃 처리
    @ResponseBody
    @PostMapping("logout")
    public boolean logout(HttpServletResponse response){
        boolean result = true;
        //쿠키 삭제
        Cookie cookie_delete = COOKIE_NAME;
        cookie_delete.setMaxAge(0);
        response.addCookie(cookie_delete);

        return result;
    }
}
