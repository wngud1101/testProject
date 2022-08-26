package com.example.studyproject2.mapper;

import com.example.studyproject2.userVO.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainMapper {
    int inputSignUp(UserVO userVO);

    UserVO findLoginCheck(String userId);

    boolean isIdCheck(String userId);

    void inputLoginReport(String userId);

    UserVO findLoginReport(String userId);

    void modifyLoginCount(UserVO userVO);
}
