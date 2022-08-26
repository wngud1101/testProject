package com.example.studyproject2.service;

import com.example.studyproject2.mapper.MainMapper;
import com.example.studyproject2.userVO.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private MainMapper mainMapper;

    @Override
    public boolean signUp(UserVO userVO) {
        boolean result = true;
        int db_result = mainMapper.insertSignUp(userVO);

        if(db_result == 1){
            result = true;
        }

        return result;
    }

    @Override
    public boolean id_check(String user_id) {
        boolean result = false;
        boolean dbResult = mainMapper.id_check(user_id);

        if(dbResult == true){
            result = true;
        }
        return dbResult;
    }

    @Override
    public boolean loginCheck(String user_id, String user_pwd) {
        boolean result = false;
        UserVO dbResult = mainMapper.loginCheck(user_id);

        if(dbResult.getUser_id().equals(user_id) && dbResult.getUser_pwd().equals((user_pwd))){
            result = true;
        }
        return result;
    }

    @Override
    public void insertLoginReport(String user_id) {
        mainMapper.insertLoginReport(user_id);
    }

    @Override
    public UserVO selectLoginReport(String user_id) {
        UserVO login_report = mainMapper.selectLoginReport(user_id);
        int login_count = 0;

        if(login_report.getLogin_count() == 0){ //첫 로그인일 경우
            login_count = 1;
        }else { //첫 로그인이 아닐 경우
            login_count = login_report.getLogin_count();
            login_count +=1 ;
        }

        login_report.setLogin_count(login_count); //설정된 로그인횟수(login_count)를 vo에 저장
        login_report.setUser_id(user_id);

        mainMapper.updateLoginCount(login_report);

        return login_report;
    }
}
