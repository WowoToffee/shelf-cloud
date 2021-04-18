package com.wowotoffer.shelf.common.security.starter.interceptor;

import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.entity.constant.ShelfConstant;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import com.wowotoffer.shelf.common.security.starter.properties.ShelfCloudSecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/18 21:44
 */
public class ShelfServerProtectInterceptor implements HandlerInterceptor {

    private ShelfCloudSecurityProperties properties;

    public void setProperties(ShelfCloudSecurityProperties properties) {
        this.properties = properties;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 是否开启网管
        if (!properties.getOnlyFetchByGateway()) {
            return true;
        }
        String token = request.getHeader(ShelfConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(ShelfConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        // 网管校验
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        }else {
            ShelfResponse shelfResponse = new ShelfResponse();
            ShelfUtil.makeJsonResponse(response, HttpServletResponse.SC_FORBIDDEN, shelfResponse.message("请通过网关获取资源"));
            return false;
        }

    }
}
