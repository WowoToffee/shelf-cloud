package com.wowotoffer.shelf.common.security.starter.heandler;

import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/18 21:43
 */
@Slf4j
public class ShelfAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String requestUri = request.getRequestURI();
        int status = HttpServletResponse.SC_UNAUTHORIZED;
        String message = "访问令牌不合法";
        log.error("客户端访问{}请求失败: {}", requestUri, message, authException);
        ShelfUtil.makeJsonResponse(response, status, new ShelfResponse().message(message));
    }
}
