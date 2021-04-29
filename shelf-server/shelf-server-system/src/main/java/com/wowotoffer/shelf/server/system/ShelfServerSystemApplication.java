package com.wowotoffer.shelf.server.system;

import com.wowotoffer.shelf.common.security.starter.annotation.EnableShelfCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author OF
 * @create 2021-04-29 15:38
 */
@EnableAsync
@SpringBootApplication
@EnableShelfCloudResourceServer
@MapperScan("com.wowotoffer.shelf.server.system.mapper")
public class ShelfServerSystemApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ShelfServerSystemApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
