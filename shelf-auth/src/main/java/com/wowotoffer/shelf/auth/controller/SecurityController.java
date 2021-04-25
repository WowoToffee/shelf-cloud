package com.wowotoffer.shelf.auth.controller;

import com.wowotoffer.shelf.auth.service.ValidateCodeService;
import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.entity.constant.StringConstant;
import com.wowotoffer.shelf.common.core.exception.ValidateCodeException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/19 12:55
 */
@Controller
@RequiredArgsConstructor
public class SecurityController {

    private final ConsumerTokenServices consumerTokenServices;
    private final ValidateCodeService validateCodeService;


    /**
     * 获取当前登录用户信息
     *
      * @param principal
     * @return
     */
    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    /**
     * 获取验证码
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ValidateCodeException
     */
    @ResponseBody
    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }

    /**
     * 登录
     *
     * @return
     */
    @RequestMapping("login")
    public String login() {
        return "login";
    }

    /**
     * 登出
     *
     * @param token
     * @return
     */
    @ResponseBody
    @DeleteMapping("signout")
    public ShelfResponse signout(@RequestHeader("Authorization") String token) {
        token = StringUtils.replace(token, "bearer ", StringConstant.EMPTY);
        consumerTokenServices.revokeToken(token);
        return new ShelfResponse().message("signout");
    }
}
