package com.example.studyproject2.service;

import com.example.studyproject2.userVO.UserVO;

import java.util.List;

public interface UserService {
    public boolean findSignUp(UserVO userVO);

    public boolean isIdCheck(String userID);

    public boolean findLoginCheck(String userID, String userPwd);

    public void inputLoginReport(String userID);

    public UserVO findLoginReport(String userID);
}
