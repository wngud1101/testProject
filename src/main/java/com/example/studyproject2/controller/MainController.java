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
import java.util.List;

@Controller
public class MainController {
    //private final String COOKIE_NAME = new Cookie("LOGIN_USER");

    @Autowired
    private UserService userService;

    //회원가입 화면
    @GetMapping("signUp")
    public String signUp(){
        return "signUp";
    }

    //아이디 중복 확인
    @ResponseBody
    @PostMapping("idCheck")
    public boolean idCheck(@RequestParam("user_id")String user_id){
        boolean dbResult = userService.id_check(user_id);

        return dbResult;
    }

    //회원가입 정보 DB에 넣는 메서드
    @ResponseBody
    @PostMapping("signUp_check")
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

    //로그인 id, pwd 확인
    @ResponseBody
    @PostMapping("loginTest")
    public boolean loginTest(HttpServletRequest request){
        String user_id = request.getParameter("user_id");
        String user_pwd = request.getParameter("user_pwd");

        boolean dbResult = userService.loginCheck(user_id, user_pwd);

        return dbResult;
    }

    //로그인 완료 화면
    @GetMapping("loginSuccess")
    public String loginSuccess(HttpServletRequest request, HttpServletResponse response, Model model){
        String user_id = request.getParameter("user_id");

        //로그인 안되어 있으면 로그인 페이지로 이동
        if(user_id != null) {
            return "login";
        }
        //로그인 성공 시 유저와 로그인 시각 기록
        userService.insertLoginReport(user_id);

        //쿠키 생성
        Cookie login_user = new Cookie(user_id, user_id);
        response.addCookie(login_user);

        //마지막 로그인 시간 확인
        UserVO login_time = userService.selectLoginReport(user_id);
        boolean login_time_result = false;
        UserVO userVO = new UserVO();

        //로그인 횟수 확인
        UserVO login_db_count = userService.selectLoginCount(user_id);
        int login_count = 0;

        if(login_db_count.getLogin_count() == 0){ //방문 횟수 0일 경우
            login_count = 1;
            model.addAttribute("login_report", login_time_result);
        }else {
            login_count = login_db_count.getLogin_count();
            login_count +=1 ;
            login_time_result = true;

            model.addAttribute("login_report",login_time_result);
            model.addAttribute("login_time", login_time.getLogin_time());
        }

        userVO.setUser_id(user_id);
        userVO.setLogin_count(login_count);
        userService.updateLoginCount(userVO);

        model.addAttribute("LOGIN_USER", user_id);      //쿠키 생성
        model.addAttribute("login_count", login_count); //view에 로그인 횟수 저장

        return "loginSuccess";
    }

    //로그아웃 처리
    @ResponseBody
    @PostMapping("logout")
    public boolean logout(HttpServletRequest request, HttpServletResponse response){
        boolean result = false;
        String myCookie = request.getParameter(request.getParameter("myCookie"));

        //쿠키 삭제
        Cookie cookie_delete = new Cookie(myCookie, null);
        cookie_delete.setMaxAge(0);
        response.addCookie(cookie_delete);

        return result;
    }
}
