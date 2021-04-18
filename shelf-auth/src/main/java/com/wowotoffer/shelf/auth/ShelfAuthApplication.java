package com.wowotoffer.shelf.auth;

import com.wowotoffer.shelf.common.security.starter.annotation.EnableShelfCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/19 10:42
 */
@SpringBootApplication
@EnableShelfCloudResourceServer
@MapperScan("com.wowotoffer.shelf.auth.mapper")
public class ShelfAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShelfAuthApplication.class, args);
    }
}
