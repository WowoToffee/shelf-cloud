package com.wowotoffer.shelf.common.configure;

import com.wowotoffer.shelf.common.handler.ShelfAccessDeniedHandler;
import com.wowotoffer.shelf.common.handler.ShelfAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/22 21:39
 */
public class ShelfAuthExceptionConfigure {
    /**
     *  IOC 没有是进行注入
     */
    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public ShelfAccessDeniedHandler accessDeniedHandler(){
        return new ShelfAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public ShelfAuthExceptionEntryPoint authenticationEntryPoint() {
        return new ShelfAuthExceptionEntryPoint();
    }
}
