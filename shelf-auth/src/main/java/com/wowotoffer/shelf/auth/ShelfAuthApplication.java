package com.wowotoffer.shelf.auth;

import com.wowotoffer.shelf.common.annotation.EnableShelfAuthExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/19 10:42
 */
@EnableShelfAuthExceptionHandler
@SpringBootApplication
public class ShelfAuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShelfAuthApplication.class, args);
    }
}
