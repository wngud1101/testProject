package com.example.studyproject2;

import com.example.studyproject2.mapper.mainMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.studyproject2.mapper")
public class StudyProject2Application {

    @Autowired
    mainMapper mainMapper;

    public static void main(String[] args) {
        SpringApplication.run(StudyProject2Application.class, args);
    }

}
