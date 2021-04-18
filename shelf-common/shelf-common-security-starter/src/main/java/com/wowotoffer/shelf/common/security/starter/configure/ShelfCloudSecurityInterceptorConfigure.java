package com.wowotoffer.shelf.common.security.starter.configure;

import com.wowotoffer.shelf.common.security.starter.interceptor.ShelfServerProtectInterceptor;
import com.wowotoffer.shelf.common.security.starter.properties.ShelfCloudSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/18 21:48
 */
public class ShelfCloudSecurityInterceptorConfigure implements WebMvcConfigurer {
    private ShelfCloudSecurityProperties properties;

    @Autowired
    public void setProperties(ShelfCloudSecurityProperties properties) {
        this.properties = properties;
    }

    @Bean
    public HandlerInterceptor shelfServerProtectInterceptor(){
        ShelfServerProtectInterceptor ShelfServerProtectInterceptor = new ShelfServerProtectInterceptor();
        ShelfServerProtectInterceptor.setProperties(properties);
        return ShelfServerProtectInterceptor;
    }
    @Override
    @SuppressWarnings("all")
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(shelfServerProtectInterceptor());
    }
}
