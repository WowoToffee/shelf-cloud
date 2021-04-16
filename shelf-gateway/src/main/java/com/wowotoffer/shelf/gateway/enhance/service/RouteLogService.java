package com.wowotoffer.shelf.gateway.enhance.service;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.gateway.enhance.entity.RouteLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/13 20:40
 */
public interface RouteLogService {

    /**
     * 创建路由日志
     *
     * @param routeLog 路由日志
     * @return 路由日志
     */
    Mono<RouteLog> create(RouteLog routeLog);

    /**
     * 查找路由日志分页数据
     *
     * @param request  request
     * @param routeLog routeLog
     * @return 路由日志分页数据
     */
    Flux<RouteLog> findPages(QueryRequest request, RouteLog routeLog);

    /**
     * 查找路由分页数据count
     *
     * @param routeLog routeLog
     * @return count
     */
    Mono<Long> findCount(RouteLog routeLog);

    /**
     * 删除路由日志
     *
     * @param ids 路由日志id
     * @return 被删除的路由日志
     */
    Flux<RouteLog> delete(String ids);
}
