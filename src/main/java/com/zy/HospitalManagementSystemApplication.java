package com.zy;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableTransactionManagement
@ServletComponentScan
@MapperScan("com.zy.dao")
public class HospitalManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(HospitalManagementSystemApplication.class, args);
        log.info("项目开始启动......");
    }

}
