package com.wowotoffer.shelf.gateway.enhance.service;

import com.wowotoffer.shelf.common.core.entity.constant.ShelfConstant;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/13 21:39
 */
public interface RouteEnhanceService {
    /**
     * 根据黑名单规则进行过滤
     *
     * @param exchange ServerWebExchange
     * @return Mono<Void>
     */
    Mono<Void> filterBlackList(ServerWebExchange exchange);

    /**
     * 异步存储拦截日志
     *
     * @param exchange ServerWebExchange
     */
    @Async(ShelfConstant.ASYNC_POOL)
    void saveBlockLogs(ServerWebExchange exchange);

    /**
     * 根据限流规则进行过滤
     *
     * @param exchange ServerWebExchange
     * @return Mono<Void>
     */
    Mono<Void> filterRateLimit(ServerWebExchange exchange);

    /**
     * 异步存储限流日志
     *
     * @param exchange ServerWebExchange
     */
    @Async(ShelfConstant.ASYNC_POOL)
    void saveRateLimitLogs(ServerWebExchange exchange);
}
