package com.wowotoffer.shelf.common.handler;

import com.alibaba.fastjson.JSONObject;
import com.wowotoffer.shelf.common.entity.ShelfResponse;
import com.wowotoffer.shelf.common.utils.ShelfUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义了响应的格式（处理401异常）
 *
 * @author of
 * @version 1.0
 * @date 2021/3/22 21:29
 */
public class ShelfAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ShelfResponse shelfResponse = new ShelfResponse();
        ShelfUtil.makeResponse(
                response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, shelfResponse.message("token无效")
        );
    }
}
