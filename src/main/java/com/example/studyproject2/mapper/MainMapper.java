package com.example.studyproject2.mapper;

import com.example.studyproject2.userVO.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    int insertSignUp(UserVO userVO);

    UserVO loginCheck(String user_id);

    boolean id_check(String user_id);

    void insertLoginReport(String user_id);

    UserVO selectLoginReport(String user_id);

//    UserVO selectLoginCount(String user_id);

    void updateLoginCount(UserVO userVO);
}
