package com.wowotoffer.shelf.gateway.enhance.service;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.gateway.enhance.entity.RateLimitRule;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/4 17:24
 */
public interface RateLimitRuleService {
    /**
     * 创建限流规则
     *
     * @param rateLimitRule 限流规则
     * @return 先例规则
     */
    Mono<RateLimitRule> create(RateLimitRule rateLimitRule);

    /**
     * 查找所有限流规则
     *
     * @return 限流规则
     */
    Flux<RateLimitRule> findAll();

    /**
     * 查找限流规则
     *
     * @param requestUri    requestUri
     * @param requestMethod requestMethod
     * @return 限流规则
     */
    Flux<RateLimitRule> findByRequestUriAndRequestMethod(String requestUri, String requestMethod);

    /**
     * 获取限流规则分页数据
     *
     * @param request       request
     * @param rateLimitRule rateLimitRule
     * @return 限流规则分页数据
     */
    Flux<RateLimitRule> findPages(QueryRequest request, RateLimitRule rateLimitRule);

    /**
     * 限流规则分页count
     *
     * @param rateLimitRule rateLimitRule
     * @return count
     */
    Mono<Long> findCount(RateLimitRule rateLimitRule);

    /**
     * 更新限流规则
     *
     * @param rateLimitRule rateLimitRule
     * @return 被更新的限流规则
     */
    Mono<RateLimitRule> update(RateLimitRule rateLimitRule);

    /**
     * 删除限流规则
     *
     * @param ids 限流规则id
     * @return 被删除的限流规则
     */
    Flux<RateLimitRule> delete(String ids);
}
