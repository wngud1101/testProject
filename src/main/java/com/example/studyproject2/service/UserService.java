package com.example.studyproject2.service;

import com.example.studyproject2.userVO.UserVO;

import java.util.List;

public interface UserService {
    public boolean signUp(UserVO userVO);

    public boolean id_check(String user_id);

    public boolean loginCheck(String user_id, String user_pwd);

    public void insertLoginReport(String user_id);

    public UserVO selectLoginReport(String user_id);
}
