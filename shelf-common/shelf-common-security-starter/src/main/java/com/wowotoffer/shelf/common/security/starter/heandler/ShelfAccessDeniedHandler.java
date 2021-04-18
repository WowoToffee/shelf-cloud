package com.wowotoffer.shelf.common.security.starter.heandler;

import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/18 21:42
 */
public class ShelfAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ShelfResponse shelfResponse = new ShelfResponse();
        ShelfUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, shelfResponse.message("没有权限访问该资源"));
    }
}
