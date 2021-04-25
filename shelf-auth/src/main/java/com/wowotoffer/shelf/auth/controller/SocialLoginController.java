package com.wowotoffer.shelf.auth.controller;

import com.wowotoffer.shelf.auth.entity.BindUser;
import com.wowotoffer.shelf.auth.entity.UserConnection;
import com.wowotoffer.shelf.auth.service.SocialLoginService;
import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.entity.constant.StringConstant;
import com.wowotoffer.shelf.common.core.exception.ShelfException;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

/**
 * @author OF
 * @create 2021-04-25 9:43
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("social")
public class SocialLoginController {
    private static final String TYPE_LOGIN = "login";
    private static final String TYPE_BIND = "bind";

    private final SocialLoginService socialLoginService;

    @Value("${shelf.frontUrl}")
    private String frontUrl;

    /**
     * 登录
     *
     * @param oauthType 第三方登录类型
     */
    @ResponseBody
    @GetMapping("/login/{oauthType}/{type}")
    public void renderAuth(@PathVariable String oauthType, @PathVariable String type, HttpServletResponse response) throws ShelfException, IOException {
        AuthRequest authRequest = socialLoginService.renderAuth(oauthType);
        response.sendRedirect(authRequest.authorize(oauthType + StringConstant.DOUBLE_COLON + AuthStateUtils.createState() + StringConstant.DOUBLE_COLON + type));
    }

    /**
     * 登录成功后的回调
     *
     * @param oauthType 第三方登录类型
     * @param callback  携带返回的信息
     * @return String
     */
    @GetMapping("/{oauthType}/callback")
    public String login(@PathVariable String oauthType, AuthCallback callback, String state, Model model) {
        try {
            ShelfResponse shelfResponse = null;
            String type = StringUtils.substringAfterLast(state, StringConstant.DOUBLE_COLON);
            if (StringUtils.equals(type, TYPE_BIND)) {
                shelfResponse = socialLoginService.resolveBind(oauthType, callback);
            } else {
                shelfResponse = socialLoginService.resolveLogin(oauthType, callback);
            }
            model.addAttribute("response", shelfResponse);
            model.addAttribute("frontUrl", frontUrl);
            return "result";
        } catch (Exception e) {
            String errorMessage = ShelfUtil.containChinese(e.getMessage()) ? e.getMessage() : "第三方登录失败";
            model.addAttribute("error", errorMessage);
            return "fail";
        }
    }

    /**
     * 绑定并登录
     *
     * @param bindUser bindUser
     * @param authUser authUser
     * @return ShelfResponse
     */
    @ResponseBody
    @PostMapping("bind/login")
    public ShelfResponse bindLogin(@Valid BindUser bindUser, AuthUser authUser) throws ShelfException {
        OAuth2AccessToken oAuth2AccessToken = this.socialLoginService.bindLogin(bindUser, authUser);
        return new ShelfResponse().data(oAuth2AccessToken);
    }

    /**
     * 注册并登录
     *
     * @param registUser registUser
     * @param authUser   authUser
     * @return ShelfResponse
     */
    @ResponseBody
    @PostMapping("sign/login")
    public ShelfResponse signLogin(@Valid BindUser registUser, AuthUser authUser) throws ShelfException {
        OAuth2AccessToken oAuth2AccessToken = this.socialLoginService.signLogin(registUser, authUser);
        return new ShelfResponse().data(oAuth2AccessToken);
    }

    /**
     * 绑定
     *
     * @param bindUser bindUser
     * @param authUser authUser
     */
    @ResponseBody
    @PostMapping("bind")
    public void bind(BindUser bindUser, AuthUser authUser) throws ShelfException {
        this.socialLoginService.bind(bindUser, authUser);
    }

    /**
     * 解绑
     *
     * @param bindUser  bindUser
     * @param oauthType oauthType
     */
    @ResponseBody
    @DeleteMapping("unbind")
    public void unbind(BindUser bindUser, String oauthType) throws ShelfException {
        this.socialLoginService.unbind(bindUser, oauthType);
    }

    /**
     * 根据用户名获取绑定关系
     *
     * @param username 用户名
     * @return ShelfResponse
     */
    @ResponseBody
    @GetMapping("connections/{username}")
    public ShelfResponse findUserConnections(@NotBlank(message = "{required}") @PathVariable String username) {
        List<UserConnection> userConnections = this.socialLoginService.findUserConnections(username);
        return new ShelfResponse().data(userConnections);
    }
}