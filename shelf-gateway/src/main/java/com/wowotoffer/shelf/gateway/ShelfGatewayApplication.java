package com.wowotoffer.shelf.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/1 20:41
 */
@SpringBootApplication
public class ShelfGatewayApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ShelfGatewayApplication.class)
                .web(WebApplicationType.REACTIVE)
                .run(args);
    }
}
