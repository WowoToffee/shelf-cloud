package com.wowotoffer.shelf.gateway.enhance.service;

import com.wowotoffer.shelf.gateway.enhance.entity.RouteUser;
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
}
