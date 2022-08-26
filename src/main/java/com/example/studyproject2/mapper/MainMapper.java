package com.example.studyproject2.mapper;

import com.example.studyproject2.userVO.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    int insertSignUp(UserVO userVO);

    UserVO loginCheck(String userId);

    boolean idCheck(String userId);

    void insertLoginReport(String userId);

    UserVO selectLoginReport(String userId);

    void updateLoginCount(UserVO userVO);
}
