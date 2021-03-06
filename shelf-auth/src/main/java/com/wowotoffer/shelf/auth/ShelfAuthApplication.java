package com.wowotoffer.shelf.auth;

import com.wowotoffer.shelf.common.security.starter.annotation.EnableShelfCloudResourceServer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/19 10:42
 */
@SpringBootApplication
@EnableRedisHttpSession
@EnableShelfCloudResourceServer
@MapperScan("com.wowotoffer.shelf.auth.mapper")
public class ShelfAuthApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ShelfAuthApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
