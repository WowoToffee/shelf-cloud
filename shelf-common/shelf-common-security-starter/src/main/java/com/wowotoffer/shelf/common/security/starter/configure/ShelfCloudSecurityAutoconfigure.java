package com.wowotoffer.shelf.common.security.starter.configure;

import com.wowotoffer.shelf.common.core.entity.constant.ShelfConstant;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import com.wowotoffer.shelf.common.security.starter.heandler.ShelfAccessDeniedHandler;
import com.wowotoffer.shelf.common.security.starter.heandler.ShelfAuthExceptionEntryPoint;
import com.wowotoffer.shelf.common.security.starter.properties.ShelfCloudSecurityProperties;
import feign.RequestInterceptor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Base64Utils;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/18 21:39
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableConfigurationProperties(ShelfCloudSecurityProperties.class)
@ConditionalOnProperty(value = "shelf.cloud.security.enable", havingValue = "true", matchIfMissing = true)
public class ShelfCloudSecurityAutoconfigure {
    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public ShelfAccessDeniedHandler accessDeniedHandler() {
        return new ShelfAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public ShelfAuthExceptionEntryPoint authenticationEntryPoint() {
        return new ShelfAuthExceptionEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ShelfCloudSecurityInterceptorConfigure shelfCloudSecurityInterceptorConfigure() {
        return new ShelfCloudSecurityInterceptorConfigure();
    }

    /**
     * Feign 互相调用header中添加token
     * @return
     */
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate->{
            String token = new String(Base64Utils.encode(ShelfConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(ShelfConstant.GATEWAY_TOKEN_HEADER, token);
            String authorizationToken = ShelfUtil.getCurrentTokenValue();
            if(StringUtils.isNotBlank(authorizationToken)){
                requestTemplate.header(HttpHeaders.AUTHORIZATION, ShelfConstant.OAUTH2_TOKEN_TYPE + authorizationToken);
            }
        };
    }
}
