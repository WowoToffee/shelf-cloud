package com.wowotoffer.shelf.gateway.enhance.runner;

import com.wowotoffer.shelf.gateway.enhance.service.BlackListService;
import com.wowotoffer.shelf.gateway.enhance.service.RateLimitRuleService;
import com.wowotoffer.shelf.gateway.enhance.service.RouteEnhanceCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/4 16:44
 */
@RequiredArgsConstructor
public class ShelfRouteEnhanceRunner implements ApplicationRunner {
    private final RouteEnhanceCacheService cacheService;
    private final BlackListService blackListService;
    private final RateLimitRuleService rateLimitRuleService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("已开启网关增强功能：请求日志、黑名单&限流。");
        cacheService.saveAllBlackList(blackListService.findAll());
        cacheService.saveAllRateLimitRules(rateLimitRuleService.findAll());
    }
}
