package com.wowotoffer.shelf.auth.service;

import com.wowotoffer.shelf.auth.entity.BindUser;
import com.wowotoffer.shelf.auth.entity.UserConnection;
import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.exception.ShelfException;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import java.util.List;

/**
 * @author OF
 * @create 2021-04-25 9:43
 */
public interface SocialLoginService {
    /**
     * 解析第三方登录请求
     *
     * @param oauthType 第三方平台类型
     * @return AuthRequest
     * @throws ShelfException 异常
     */
    AuthRequest renderAuth(String oauthType) throws ShelfException;

    /**
     * 处理第三方登录（绑定页面）
     *
     * @param oauthType 第三方平台类型
     * @param callback  回调
     * @return FebsResponse
     * @throws ShelfException 异常
     */
    ShelfResponse resolveBind(String oauthType, AuthCallback callback) throws ShelfException;


    /**
     * 处理第三方登录（登录页面）
     *
     * @param oauthType 第三方平台类型
     * @param callback  回调
     * @return FebsResponse
     * @throws ShelfException 异常
     */
    ShelfResponse resolveLogin(String oauthType, AuthCallback callback) throws ShelfException;

    /**
     * 绑定并登录
     *
     * @param bindUser 绑定用户
     * @param authUser 第三方平台对象
     * @return OAuth2AccessToken 令牌对象
     * @throws ShelfException 异常
     */
    OAuth2AccessToken bindLogin(BindUser bindUser, AuthUser authUser) throws ShelfException;

    /**
     * 注册并登录
     *
     * @param registUser 注册用户
     * @param authUser   第三方平台对象
     * @return OAuth2AccessToken 令牌对象
     * @throws ShelfException 异常
     */
    OAuth2AccessToken signLogin(BindUser registUser, AuthUser authUser) throws ShelfException;

    /**
     * 绑定
     *
     * @param bindUser 绑定对象
     * @param authUser 第三方平台对象
     * @throws ShelfException 异常
     */
    void bind(BindUser bindUser, AuthUser authUser) throws ShelfException;

    /**
     * 解绑
     *
     * @param bindUser  绑定对象
     * @param oauthType 第三方平台对象
     * @throws ShelfException 异常
     */
    void unbind(BindUser bindUser, String oauthType) throws ShelfException;

    /**
     * 根据用户名获取绑定关系
     *
     * @param username 用户名
     * @return 绑定关系集合
     */
    List<UserConnection> findUserConnections(String username);
}
