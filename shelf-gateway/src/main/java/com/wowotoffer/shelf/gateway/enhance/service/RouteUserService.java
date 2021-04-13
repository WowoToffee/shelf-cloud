package com.wowotoffer.shelf.gateway.enhance.service;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.gateway.enhance.entity.RouteUser;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/12 21:58
 */
public interface RouteUserService {
    /**
     * 根据用户名获取路由用户
     *
     * @param username 用户名
     * @return 路由用户
     */
    Mono<RouteUser> findByUsername(String username);

    /**
     * 分页
     *
     * @param request
     * @param routeUser
     * @return
     */
    Flux<RouteUser> findPages(QueryRequest request, RouteUser routeUser);

    /**
     * 查找路由用户分页数据count
     *
     * @param routeUser routeUser
     * @return count
     */
    Mono<Long> findCount(RouteUser routeUser);

    /**
     * 新增
     *
     * @param routeUser
     * @return
     */
    Mono<RouteUser> create(RouteUser routeUser);

    /**
     * 编辑
     *
     * @param routeUser
     * @return
     */
    Mono<RouteUser> update(RouteUser routeUser);

    /**
     * 删除路由用户
     *
     * @param ids 路由用户id
     * @return 被删除的路由用户
     */
    Flux<RouteUser> delete(String ids);
}
