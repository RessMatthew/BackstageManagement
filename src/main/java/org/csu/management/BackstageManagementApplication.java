package org.csu.management;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.csu.management.mapper")
public class BackstageManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackstageManagementApplication.class, args);
    }

}
