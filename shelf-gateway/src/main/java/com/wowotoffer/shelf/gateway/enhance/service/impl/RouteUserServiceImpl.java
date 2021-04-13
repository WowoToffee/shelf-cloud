package com.wowotoffer.shelf.gateway.enhance.service.impl;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.common.core.utils.DateUtil;
import com.wowotoffer.shelf.gateway.enhance.entity.RouteUser;
import com.wowotoffer.shelf.gateway.enhance.mapper.RouteUserMapper;
import com.wowotoffer.shelf.gateway.enhance.service.RouteUserService;
import com.wowotoffer.shelf.gateway.enhance.utils.PageableExecutionUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/12 21:58
 */
@Service
@RequiredArgsConstructor
public class RouteUserServiceImpl implements RouteUserService {

    private final PasswordEncoder passwordEncoder;
    private RouteUserMapper routeUserMapper;
    private ReactiveMongoTemplate template;

    @Autowired(required = false)
    public void setRouteUserMapper(RouteUserMapper routeUserMapper) {
        this.routeUserMapper = routeUserMapper;
    }

    @Autowired(required = false)
    public void setTemplate(ReactiveMongoTemplate template) {
        this.template = template;
    }


    @Override
    public Mono<RouteUser> findByUsername(String username) {
        return routeUserMapper.findByUsername(username);
    }

    @Override
    public Flux<RouteUser> findPages(QueryRequest request, RouteUser routeUser) {
        Query queryRequest = getQuery(routeUser);

        return PageableExecutionUtil.getPages(queryRequest, request, RouteUser.class, template);
    }

    @Override
    public Mono<Long> findCount(RouteUser routeUser) {
        Query queryRequest = getQuery(routeUser);
        return template.count(queryRequest, RouteUser.class);
    }

    @Override
    public Mono<RouteUser> create(RouteUser routeUser) {
        routeUser.setPassword(passwordEncoder.encode(routeUser.getPassword()));
        routeUser.setCreateTime(DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        return template.save(routeUser);
    }

    @Override
    public Mono<RouteUser> update(RouteUser routeUser) {
        return this.routeUserMapper.findById(routeUser.getId())
                .flatMap(u -> {
                    u.setRoles(routeUser.getRoles());
                    return this.routeUserMapper.save(u);
                });
    }

    @Override
    public Flux<RouteUser> delete(String ids) {
        String[] idArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(ids, ",");
        return routeUserMapper.deleteByIdIn(Arrays.asList(idArray));
    }

    private Query getQuery(RouteUser routeUser){
        Query query = new Query();
        Criteria criteria = new Criteria();
        if (StringUtils.isNotBlank(routeUser.getUsername())) {
            criteria.and("username").is(routeUser.getUsername());
        }
        query.addCriteria(criteria);

        return query;
    }
}
