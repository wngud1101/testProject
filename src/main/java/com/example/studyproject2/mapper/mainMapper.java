package com.example.studyproject2.mapper;

import com.example.studyproject2.userVO.userVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface mainMapper {
    int insertSignUp(userVO userVO);

    List<userVO> loginCheck(String user_id);

    boolean id_check(String user_id);

    void insertLoginReport(String user_id);

    List<userVO> selectLoginReport(String user_id);

    List<userVO> selectLoginCount(String user_id);

    void updateLoginCount(userVO userVO);
}
