package com.wowotoffer.shelf.auth.configure;

import com.wowotoffer.shelf.common.core.handler.ShelfAccessDeniedHandler;
import com.wowotoffer.shelf.common.core.handler.ShelfAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 处理spring security oauth2
 * 客户端只能通过OAuth2协议发放的令牌来从资源服务器中获取受保护的资源。
 * @author of
 * @version 1.0
 * @date 2021/3/19 10:36
 */
@Configuration
@EnableResourceServer
public class ShelfResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Autowired
    private ShelfAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private ShelfAuthExceptionEntryPoint exceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/captcha").permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(exceptionEntryPoint)
                .accessDeniedHandler(accessDeniedHandler);
    }
}
