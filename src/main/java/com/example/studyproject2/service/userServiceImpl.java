package com.example.studyproject2.service;

import com.example.studyproject2.mapper.mainMapper;
import com.example.studyproject2.userVO.userVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService{
    @Autowired
    private mainMapper mainMapper;

    @Override
    public int signUp(userVO userVO) {
        return mainMapper.insertSignUp(userVO);
    }

    @Override
    public boolean id_check(String user_id) {
        return mainMapper.id_check(user_id);
    }

    @Override
    public List<userVO> loginCheck(String user_id) {
        return mainMapper.loginCheck(user_id);
    }

    @Override
    public void insertLoginReport(String user_id) {
        mainMapper.insertLoginReport(user_id);
    }

    @Override
    public List<userVO> selectLoginReport(String user_id) {
        return mainMapper.selectLoginReport(user_id);
    }

    @Override
    public List<userVO> selectLoginCount(String user_id) {
        return mainMapper.selectLoginCount(user_id);
    }

    @Override
    public void updateLoginCount(userVO userVO) {
        mainMapper.updateLoginCount(userVO);
    }
}
