package com.wowotoffer.shelf.server.test;

import com.wowotoffer.shelf.common.security.starter.annotation.EnableShelfCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author of
 * @version 1.0
 * @date 2021/5/5 20:04
 */
@EnableAsync
@SpringBootApplication
@EnableShelfCloudResourceServer
@MapperScan("com.wowotoffer.shelf.server.test.mapper")
public class ShelfServerTestApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ShelfServerTestApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
