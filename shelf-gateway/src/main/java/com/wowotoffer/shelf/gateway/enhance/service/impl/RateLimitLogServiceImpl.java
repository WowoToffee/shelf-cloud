package com.wowotoffer.shelf.gateway.enhance.service.impl;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.common.core.utils.DateUtil;
import com.wowotoffer.shelf.gateway.enhance.entity.RateLimitLog;
import com.wowotoffer.shelf.gateway.enhance.mapper.RateLimitLogMapper;
import com.wowotoffer.shelf.gateway.enhance.service.RateLimitLogService;
import com.wowotoffer.shelf.gateway.enhance.utils.AddressUtil;
import com.wowotoffer.shelf.gateway.enhance.utils.PageableExecutionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/13 21:24
 */
@Service
public class RateLimitLogServiceImpl implements RateLimitLogService {
    private RateLimitLogMapper rateLimitLogMapper;
    private ReactiveMongoTemplate template;

    @Autowired(required = false)
    public void setRateLimitLogMapper(RateLimitLogMapper rateLimitLogMapper) {
        this.rateLimitLogMapper = rateLimitLogMapper;
    }

    @Autowired(required = false)
    public void setTemplate(ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<RateLimitLog> create(RateLimitLog rateLimitLog) {
        rateLimitLog.setCreateTime(DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN));
        rateLimitLog.setLocation(AddressUtil.getCityInfo(rateLimitLog.getIp()));
        return rateLimitLogMapper.insert(rateLimitLog);
    }

    @Override
    public Flux<RateLimitLog> delete(String ids) {
        String[] idArray = StringUtils.splitByWholeSeparatorPreserveAllTokens(ids, ",");
        return rateLimitLogMapper.deleteByIdIn(Arrays.asList(idArray));
    }

    @Override
    public Flux<RateLimitLog> findPages(QueryRequest request, RateLimitLog rateLimitLog) {
        Query query = getQuery(rateLimitLog);
        return PageableExecutionUtil.getPages(query, request, RateLimitLog.class, template);
    }

    @Override
    public Mono<Long> findCount(RateLimitLog rateLimitLog) {
        Query query = getQuery(rateLimitLog);
        return template.count(query, RateLimitLog.class);
    }

    private Query getQuery(RateLimitLog rateLimitLog) {
        Query query = new Query();
        Criteria criteria = new Criteria();

        if (StringUtils.isNotBlank(rateLimitLog.getIp())) {
            criteria.and("ip").is(rateLimitLog.getIp());
        }
        if (StringUtils.isNotBlank(rateLimitLog.getRequestMethod())) {
            criteria.and("requestMethod").is(rateLimitLog.getRequestMethod());
        }
        if (StringUtils.isNotBlank(rateLimitLog.getRequestUri())) {
            criteria.and("requestUri").is(rateLimitLog.getRequestUri());
        }
        if (StringUtils.isNotBlank(rateLimitLog.getCreateTimeFrom())
                && StringUtils.isNotBlank(rateLimitLog.getCreateTimeTo())) {
            criteria.andOperator(
                    Criteria.where("createTime").gt(rateLimitLog.getCreateTimeFrom()),
                    Criteria.where("createTime").lt(rateLimitLog.getCreateTimeTo())
            );
        }
        query.addCriteria(criteria);
        return query;
    }
}
