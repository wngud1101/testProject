package com.example.studyproject2.userDAO;

import com.example.studyproject2.userVO.userVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class userDAO {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    public int signUp(userVO userVO){
        int dbResult = sqlSessionTemplate.insert("UserDAO.insertSignUp", userVO);

        return dbResult;
    }

    public List<String> loginCheck(String user_id){
        List<String> dbResult = sqlSessionTemplate.selectList("UserDAO.selectIdCheck", user_id);
        return dbResult;
    }
}
