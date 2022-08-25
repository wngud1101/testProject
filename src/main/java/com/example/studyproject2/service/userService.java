package com.example.studyproject2.service;

import com.example.studyproject2.userVO.userVO;

import java.util.List;

public interface userService {
    public int signUp(userVO userVO);

    public boolean id_check(String user_id);

    public List<userVO> loginCheck(String user_id);

    public void insertLoginReport(String user_id);

    public List<userVO> selectLoginReport(String user_id);

    public List<userVO> selectLoginCount(String user_id);

    public void updateLoginCount(userVO userVO);
}
