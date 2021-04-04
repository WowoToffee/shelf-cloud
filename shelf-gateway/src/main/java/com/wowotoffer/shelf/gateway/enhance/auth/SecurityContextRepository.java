package com.wowotoffer.shelf.gateway.enhance.auth;

import com.google.common.net.HttpHeaders;
import com.wowotoffer.shelf.common.core.entity.constant.ShelfConstant;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/3 22:40
 */
@Component
@RequiredArgsConstructor
public class SecurityContextRepository implements ServerSecurityContextRepository {
    private final AuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        throw new UnsupportedOperationException("暂不支持");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        ServerHttpRequest request = serverWebExchange.getRequest();

        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if(StringUtils.isNotBlank(authHeader) && StringUtils.startsWith(authHeader, ShelfConstant.OAUTH2_TOKEN_TYPE)){
            String authToken = StringUtils.substringAfter(authHeader, ShelfConstant.OAUTH2_TOKEN_TYPE).trim();
            Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
            return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        }else {
            return Mono.empty();
        }
    }
}
