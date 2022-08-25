package com.example.studyproject2.controller;

import com.example.studyproject2.service.userService;

import com.example.studyproject2.userVO.userVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class mainController {
    @Autowired
    private userService userService;

    //회원가입 화면
    @GetMapping("signUp")
    public String signUp(){
        System.out.println("회원가입화면");
        return "signUp";
    }

    //아이디 중복 확인
    @ResponseBody
    @RequestMapping(value="idCheck", method = {RequestMethod.POST})
    public boolean idCheck(@RequestParam("user_id")String user_id){
        boolean result = false;

        System.out.println("받아온 아이디 값 : " + user_id);

        boolean dbResult = userService.id_check(user_id);

        if(dbResult == true){
            result = true;
        }

        return result;
    }

    //회원가입 정보 DB에 넣는 메서드
    @ResponseBody
    @RequestMapping(value="signUp_check", method = {RequestMethod.POST})
    public boolean signUp_check(@RequestParam("user_id")String user_id,
                                @RequestParam("user_pwd")String user_pwd){
        userVO userVO = new userVO();
        boolean result = true;

        userVO.setUser_id(user_id);
        userVO.setUser_pwd(user_pwd);

        int dbResult = userService.signUp(userVO);

        if(dbResult == 1){
            result = true;
            System.out.println("회원가입 완료");
        }

        return result;
    }


    //로그인 화면
    @GetMapping("login")
    public String login(){
        return "login";
    }

    //로그인 id, pwd 확인
    @ResponseBody
    @RequestMapping(value="loginTest", method = {RequestMethod.POST})
    public boolean loginTest(HttpServletRequest request){
        System.out.println("아이디 체크 중");
        boolean result = false;
        userVO userVO = new userVO();

        String user_id = request.getParameter("user_id");
        String user_pwd = request.getParameter("user_pwd");

        System.out.println("받은 id 확인 : " + user_id);
        System.out.println("받은 pwd 확인 : " + user_pwd);

        //DB에 있는 id, pwd 비교 후 동일할 시 진행
        //id값으로 DB갔다옴
        List<userVO> dbResult = userService.loginCheck(user_id);

        if(dbResult.get(0).getUser_id().equals(user_id) && dbResult.get(0).getUser_pwd().equals((user_pwd))){
            result = true;
        }

        return result;
    }

    //로그인 완료 화면
    @GetMapping("loginSuccess")
    public String loginSuccess(HttpServletRequest request, HttpServletResponse response, Model model){
        String user_id = request.getParameter("user_id");
        System.out.println("로그인한 유저 : " + user_id);

        //로그인 안되어 있으면 로그인 페이지로 이동
        if(user_id != null){
            //로그인 성공 시 유저와 로그인 시각 기록
            userService.insertLoginReport(user_id);

            //쿠키 생성
            Cookie login_user = new Cookie(user_id, user_id);
            response.addCookie(login_user);

            model.addAttribute("LOGIN_USER", user_id);

            //마지막 로그인 시간 확인
            List<userVO> login_time = userService.selectLoginReport(user_id);
            boolean login_time_result = false;
            userVO userVO = new userVO();

            //로그인 횟수 확인
            List<userVO> login_db_count = userService.selectLoginCount(user_id);
            int login_count = 0;

            System.out.println("가져온 DB " + login_db_count);

            if(login_db_count.isEmpty()){ //방문 횟수 0일 경우
                System.out.println("방문횟수 0");
                login_count = 1;
            }else {
                System.out.println("처음방문아님");
                login_count = login_db_count.get(0).getLogin_count();
                login_count +=1 ;
            }

            userVO.setUser_id(user_id);
            userVO.setLogin_count(login_count);
            userService.updateLoginCount(userVO);

            if(login_db_count == null){ //첫 로그인
                model.addAttribute("login_report", login_time_result);
            }else { //1번 이상 로그인 했던경우
                login_time_result = true;

                model.addAttribute("login_report",login_time_result);
                model.addAttribute("login_time", login_time.get(0).getLogin_time());
            }

            model.addAttribute("login_count", login_count);

            return "loginSuccess";
        }else {
            System.out.println("로그인 안됨");
            return "login";
        }
    }

    //로그아웃 처리
    @ResponseBody
    @RequestMapping(value="logout", method = {RequestMethod.POST})
    public boolean logout(HttpServletRequest request, HttpServletResponse response){
        boolean result = false;
        String myCookie = request.getParameter(request.getParameter("myCookie"));
        System.out.println("받은 쿠키 : " + myCookie);

        //쿠키 삭제
        Cookie cookie_delete = new Cookie(myCookie, null);
        cookie_delete.setMaxAge(0);
        response.addCookie(cookie_delete);

        return result;
    }
}
