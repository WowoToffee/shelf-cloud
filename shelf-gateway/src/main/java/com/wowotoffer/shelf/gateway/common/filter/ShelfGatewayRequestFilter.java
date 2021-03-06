package com.wowotoffer.shelf.gateway.common.filter;

import com.wowotoffer.shelf.common.core.entity.constant.ShelfConstant;
import com.wowotoffer.shelf.gateway.enhance.service.RouteEnhanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局网关过滤器
 *
 * @author of
 * @version 1.0
 * @date 2021/4/3 16:50
 */
@Slf4j
@Component
@Order(0)
@RequiredArgsConstructor
public class ShelfGatewayRequestFilter implements GlobalFilter {
    private final RouteEnhanceService routeEnhanceService;

    @Value("${shelf.gateway.enhance:false}")
    private Boolean routeEhance;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        if(routeEhance){
            // 根据黑名单规则进行过滤
            Mono<Void> blackListResult = routeEnhanceService.filterBlackList(exchange);
            if (blackListResult != null) {
                routeEnhanceService.saveBlockLogs(exchange);
                return blackListResult;
            }
            // 根据限流规则进行过滤
            Mono<Void> rateLimitResult = routeEnhanceService.filterRateLimit(exchange);
            if (rateLimitResult != null) {
                routeEnhanceService.saveRateLimitLogs(exchange);
                return rateLimitResult;
            }
            routeEnhanceService.saveRequestLogs(exchange);
        }

        // 转发添加gateway token，进行权限校验
        byte[] token = Base64Utils.encode((ShelfConstant.GATEWAY_TOKEN_VALUE).getBytes());
        String[] headerValues = {new String(token)};
        ServerHttpRequest build = exchange.getRequest().mutate().header(ShelfConstant.GATEWAY_TOKEN_HEADER, headerValues).build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }
}
