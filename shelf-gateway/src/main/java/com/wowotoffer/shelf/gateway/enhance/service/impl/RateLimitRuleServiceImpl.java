package com.wowotoffer.shelf.gateway.enhance.service.impl;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.gateway.enhance.entity.RateLimitRule;
import com.wowotoffer.shelf.gateway.enhance.mapper.RateLimitRuleMapper;
import com.wowotoffer.shelf.gateway.enhance.service.RateLimitRuleService;
import com.wowotoffer.shelf.gateway.enhance.service.RouteEnhanceCacheService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/4 17:24
 */
@Service
@RequiredArgsConstructor
public class RateLimitRuleServiceImpl implements RateLimitRuleService {
    private final RouteEnhanceCacheService routeEnhanceCacheService;
    private RateLimitRuleMapper rateLimitRuleMapper;
    private ReactiveMongoTemplate template;

    @Autowired(required = false)
    public void setRateLimitRuleMapper(RateLimitRuleMapper rateLimitRuleMapper) {
        this.rateLimitRuleMapper = rateLimitRuleMapper;
    }

    @Autowired(required = false)
    public void setTemplate(ReactiveMongoTemplate template) {
        this.template = template;
    }


    @Override
    public Mono<RateLimitRule> create(RateLimitRule rateLimitRule) {
        return null;
    }

    @Override
    public Flux<RateLimitRule> findAll() {
        return null;
    }

    @Override
    public Flux<RateLimitRule> findByRequestUriAndRequestMethod(String requestUri, String requestMethod) {
        return null;
    }

    @Override
    public Flux<RateLimitRule> findPages(QueryRequest request, RateLimitRule rateLimitRule) {
        return null;
    }

    @Override
    public Mono<Long> findCount(RateLimitRule rateLimitRule) {
        return null;
    }

    @Override
    public Mono<RateLimitRule> update(RateLimitRule rateLimitRule) {
        return null;
    }

    @Override
    public Flux<RateLimitRule> delete(String ids) {
        return null;
    }
}
