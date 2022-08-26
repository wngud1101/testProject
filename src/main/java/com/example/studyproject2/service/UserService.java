package com.example.studyproject2.service;

import com.example.studyproject2.userVO.UserVO;

import java.util.List;

public interface UserService {
    public boolean signUp(UserVO userVO);

    public boolean idCheck(String userID);

    public boolean loginCheck(String userID, String userPwd);

    public void insertLoginReport(String userID);

    public UserVO selectLoginReport(String userID);
}
