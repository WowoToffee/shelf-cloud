package com.wowotoffer.shelf.gateway.enhance.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Stopwatch;
import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.utils.DateUtil;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import com.wowotoffer.shelf.gateway.enhance.entity.BlackList;
import com.wowotoffer.shelf.gateway.enhance.service.RouteEnhanceCacheService;
import com.wowotoffer.shelf.gateway.enhance.service.RouteEnhanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/13 21:39
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RouteEnhanceServiceImpl implements RouteEnhanceService {

    private final RouteEnhanceCacheService routeEnhanceCacheService;
    private final AntPathMatcher pathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filterBalckList(ServerWebExchange exchange) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        try {
            // 获取访问URL
            URI originUri = getGatewayOriginalRequestUrl(exchange);
            if (originUri != null) {
                String requestIp = ShelfUtil.getServerHttpRequestIpAddress(request);
                String requestMethod = request.getMethodValue();
                AtomicBoolean forbid = new AtomicBoolean(false);
                // 从缓存中获取黑名单规则
                Set<Object> blackList = routeEnhanceCacheService.getBlackList(requestIp);
                blackList.addAll(routeEnhanceCacheService.getBlackList());

                doBlackListCheck(forbid, blackList, originUri, requestMethod);

                log.info("Blacklist verification completed - {}", stopwatch.stop());
                if (forbid.get()) {
                    return ShelfUtil.makeWebFluxResponse(response, MediaType.APPLICATION_JSON_VALUE,
                            HttpStatus.NOT_ACCEPTABLE, new ShelfResponse().message("黑名单限制，禁止访问"));
                }
            } else {
                log.info("Request IP not obtained, no blacklist check - {}", stopwatch.stop());
            }
        } catch (Exception e) {
            log.warn("Blacklist verification failed : {} - {}", e.getMessage(), stopwatch.stop());
        }
        return null;
    }

    @Override
    public Mono<Void> filterRateLimit(ServerWebExchange exchange) {
        return null;
    }

    private URI getGatewayOriginalRequestUrl(ServerWebExchange exchange) {
        LinkedHashSet<URI> uris = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = null;
        if (uris != null) {
            originUri = uris.stream().findFirst().orElse(null);
        }
        return originUri;
    }

    /**
     * 黑名单验证
     *
     * @param forbid
     * @param blackList
     * @param uri
     * @param requestMethod
     */
    private void doBlackListCheck(AtomicBoolean forbid, Set<Object> blackList, URI uri, String requestMethod) {
        for (Object o : blackList) {
            BlackList b = JSONObject.parseObject(o.toString(), BlackList.class);
            if (pathMatcher.match(b.getRequestUri(), uri.getPath()) && BlackList.OPEN.equals(b.getStatus())) {
                if (BlackList.METHOD_ALL.equalsIgnoreCase(b.getRequestMethod())
                        || StringUtils.equalsIgnoreCase(requestMethod, b.getRequestMethod())) {
                    if (StringUtils.isNotBlank(b.getLimitFrom()) && StringUtils.isNotBlank(b.getLimitTo())) {
                        if (DateUtil.between(LocalTime.parse(b.getLimitFrom()), LocalTime.parse(b.getLimitTo()))) {
                            forbid.set(true);
                        }
                    } else {
                        forbid.set(true);
                    }
                }
            }
            if (forbid.get()) {
                break;
            }
        }
    }

    private URI getGatewayRequestUrl(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
    }

    private Route getGatewayRoute(ServerWebExchange exchange) {
        return exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
    }

}
