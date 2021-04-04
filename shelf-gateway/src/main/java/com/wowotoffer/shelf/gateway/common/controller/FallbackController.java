package com.wowotoffer.shelf.gateway.common.controller;

import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/4 16:38
 */
@Slf4j
@RestController
public class FallbackController {
    @RequestMapping("fallback/{name}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ShelfResponse> systemFallback(@PathVariable String name){
        String response = "服务访问超时，请稍后再试";
        log.error("{}，目标微服务：{}", response, name);
        return Mono.just(new ShelfResponse().message(response));
    }
}
