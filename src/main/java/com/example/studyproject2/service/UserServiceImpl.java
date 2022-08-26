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
    public boolean findSignUp(UserVO userVO) {
        boolean result = true;
        int dbResult = mainMapper.inputSignUp(userVO);

        if(dbResult == 1){
            result = true;
        }

        return result;
    }

    @Override
    public boolean isIdCheck(String userId) {
        boolean result = false;
        boolean dbResult = mainMapper.isIdCheck(userId);

        if(dbResult == true){
            result = true;
        }
        return dbResult;
    }

    @Override
    public boolean findLoginCheck(String userId, String userPwd) {
        boolean result = false;
        UserVO dbResult = mainMapper.findLoginCheck(userId);

        if(dbResult.getUserId().equals(userId) && dbResult.getUserPwd().equals((userPwd))){
            result = true;
        }
        return result;
    }

    @Override
    public void inputLoginReport(String userId) {
        mainMapper.inputLoginReport(userId);
    }

    @Override
    public UserVO findLoginReport(String userId) {
        UserVO loginReport = mainMapper.findLoginReport(userId);
        int loginCount = 0;

        if(loginReport.getLoginCount() == 0){ //첫 로그인일 경우
            loginCount = 1;
        }else { //첫 로그인이 아닐 경우
            loginCount = loginReport.getLoginCount();
            loginCount +=1 ;
        }

        loginReport.setLoginCount(loginCount); //설정된 로그인횟수(login_count)를 vo에 저장
        loginReport.setUserId(userId);

        mainMapper.modifyLoginCount(loginReport);

        return loginReport;
    }
}
