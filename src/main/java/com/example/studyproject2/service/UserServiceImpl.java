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
        return mainMapper.selectLoginReport(user_id);
    }

    @Override
    public UserVO selectLoginCount(String user_id) {
        return mainMapper.selectLoginCount(user_id);
    }

    @Override
    public void updateLoginCount(UserVO userVO) {
        mainMapper.updateLoginCount(userVO);
    }
}
