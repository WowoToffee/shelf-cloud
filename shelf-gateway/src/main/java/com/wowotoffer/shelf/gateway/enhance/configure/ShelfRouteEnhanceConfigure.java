package com.wowotoffer.shelf.gateway.enhance.configure;

import com.wowotoffer.shelf.common.core.entity.constant.ShelfConstant;
import com.wowotoffer.shelf.gateway.enhance.runner.ShelfRouteEnhanceRunner;
import com.wowotoffer.shelf.gateway.enhance.service.BlackListService;
import com.wowotoffer.shelf.gateway.enhance.service.RateLimitRuleService;
import com.wowotoffer.shelf.gateway.enhance.service.RouteEnhanceCacheService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/4 10:46
 */
@EnableAsync
@Configuration
@EnableReactiveMongoRepositories(basePackages = "com.wowotoffer.shelf.gateway.enhance.mapper")
@ConditionalOnProperty(name = "shelf.gateway.enhance", havingValue = "true")
public class ShelfRouteEnhanceConfigure {

    @Bean(ShelfConstant.ASYNC_POOL)
    public ThreadPoolTaskExecutor asyncThreadPoolTaskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setKeepAliveSeconds(30);
        executor.setThreadNamePrefix("Shelf-Gateway-Async-Thread");
        executor.setWaitForTasksToCompleteOnShutdown(true);
        executor.setAwaitTerminationSeconds(60);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Bean
    public ApplicationRunner shelfRoutenEhanceRunner(RouteEnhanceCacheService cacheService,
                                                    BlackListService blackListService,
                                                    RateLimitRuleService rateLimitRuleService) {
        return new ShelfRouteEnhanceRunner(cacheService, blackListService, rateLimitRuleService);
    }
}
