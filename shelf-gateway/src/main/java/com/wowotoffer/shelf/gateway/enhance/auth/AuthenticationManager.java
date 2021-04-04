package com.wowotoffer.shelf.gateway.enhance.auth;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * 认证管理器，使用token进行用户认证
 *
 * @author of
 * @version 1.0
 * @date 2021/4/3 22:34
 */
@Component
@RequiredArgsConstructor
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtTokenHelper tokenHelper;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = authentication.getCredentials().toString();
        String userName;
        try {
            userName = tokenHelper.getUsernameFromToken(token);
        } catch (Exception e) {
            userName = null;
        }
        if (StringUtils.isNotBlank(userName) && tokenHelper.validateToken(token)) {
            Claims claims = tokenHelper.getAllClaimsFromToken(token);
            String permissions = claims.get("permission", String.class);

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userName, null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(permissions)
            );
            return Mono.just(auth);
        } else {

            return Mono.empty();
        }
    }
}
