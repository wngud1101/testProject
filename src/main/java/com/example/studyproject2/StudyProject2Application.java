package com.example.studyproject2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.studyproject2.mapper")
public class StudyProject2Application {
    public static void main(String[] args) {
        SpringApplication.run(StudyProject2Application.class, args);
    }

}
