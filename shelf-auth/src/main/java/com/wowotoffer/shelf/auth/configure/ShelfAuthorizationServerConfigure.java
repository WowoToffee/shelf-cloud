package com.wowotoffer.shelf.auth.configure;

import com.wowotoffer.shelf.auth.service.ShelfUserDetailService;
import com.wowotoffer.shelf.auth.translator.ShelfWebResponseExceptionTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * /oauth/authorize：授权端点
 * /oauth/token：令牌端点
 * /oauth/confirm_access：用户确认授权提交端点
 * /oauth/error：授权服务错误信息端点
 * /oauth/check_token：用于资源服务访问的令牌解析端点
 * /oauth/token_key：提供公有密匙的端点，如果你使用JWT令牌的话
 *
 * @author of
 * @version 1.0
 * @date 2021/3/19 11:45
 */
@Configuration
@EnableAuthorizationServer
public class ShelfAuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final RedisConnectionFactory redisConnectionFactory;
    private final ShelfUserDetailService userDetailService;
    private final PasswordEncoder passwordEncoder;
    private final ShelfWebResponseExceptionTranslator exceptionTranslator;

    public ShelfAuthorizationServerConfigure(AuthenticationManager authenticationManager, RedisConnectionFactory redisConnectionFactory, ShelfUserDetailService userDetailService, PasswordEncoder passwordEncoder, ShelfWebResponseExceptionTranslator exceptionTranslator) {
        this.authenticationManager = authenticationManager;
        this.redisConnectionFactory = redisConnectionFactory;
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
        this.exceptionTranslator = exceptionTranslator;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                // client_id
                .withClient("shelf")
                // client_secret
                .secret(passwordEncoder.encode("123456"))
                // 该client允许的授权类型
                .authorizedGrantTypes("password", "refresh_token")
                // 允许的授权范围
                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(exceptionTranslator);
    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 令牌属性
     *
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(tokenStore());
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 24);
        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return tokenServices;
    }
}
