package com.wowotoffer.shelf.gateway.enhance.service.impl;

import com.wowotoffer.shelf.gateway.enhance.entity.RouteUser;
import com.wowotoffer.shelf.gateway.enhance.mapper.RouteUserMapper;
import com.wowotoffer.shelf.gateway.enhance.service.RouteUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/12 21:58
 */
@Service
@RequiredArgsConstructor
public class RouteUserServiceImpl implements RouteUserService {

    private RouteUserMapper routeUserMapper;

    @Override
    public Mono<RouteUser> findByUsername(String username) {
        return routeUserMapper.findByUsername(username);
    }
}
