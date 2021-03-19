package com.wowotoffer.shelf.auth.controller;

import com.wowotoffer.shelf.common.entity.ShelfResponse;
import com.wowotoffer.shelf.common.exception.ShelfAuthException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/19 12:55
 */
@RestController
public class SecurityController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public ShelfResponse signout(HttpServletRequest request) throws ShelfAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        ShelfResponse shelfResponse = new ShelfResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new ShelfAuthException("退出登录失败");
        }
        return shelfResponse.message("退出登录成功");
    }
}
