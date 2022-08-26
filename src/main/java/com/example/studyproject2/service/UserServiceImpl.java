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
        int dbResult = mainMapper.insertSignUp(userVO);

        if(dbResult == 1){
            result = true;
        }

        return result;
    }

    @Override
    public boolean idCheck(String userId) {
        boolean result = false;
        boolean dbResult = mainMapper.id_check(userId);

        if(dbResult == true){
            result = true;
        }
        return dbResult;
    }

    @Override
    public boolean loginCheck(String userId, String userPwd) {
        boolean result = false;
        UserVO dbResult = mainMapper.loginCheck(userId);

        if(dbResult.getUserId().equals(userId) && dbResult.getUserPwd().equals((userPwd))){
            result = true;
        }
        return result;
    }

    @Override
    public void insertLoginReport(String userId) {
        mainMapper.insertLoginReport(userId);
    }

    @Override
    public UserVO selectLoginReport(String userId) {
        UserVO loginReport = mainMapper.selectLoginReport(userId);
        int loginCount = 0;

        if(loginReport.getLoginCount() == 0){ //첫 로그인일 경우
            loginCount = 1;
        }else { //첫 로그인이 아닐 경우
            loginCount = loginReport.getLoginCount();
            loginCount +=1 ;
        }

        loginReport.setLoginCount(loginCount); //설정된 로그인횟수(login_count)를 vo에 저장
        loginReport.setUserId(userId);

        mainMapper.updateLoginCount(loginReport);

        return loginReport;
    }
}
