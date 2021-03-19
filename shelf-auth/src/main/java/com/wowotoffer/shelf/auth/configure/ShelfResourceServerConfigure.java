package com.wowotoffer.shelf.auth.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

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

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }


}
