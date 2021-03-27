package com.wowotoffer.shelf.auth.filter;

import com.wowotoffer.shelf.auth.service.ValidateCodeService;
import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.entity.constant.EndpointConstant;
import com.wowotoffer.shelf.common.core.entity.constant.GrantTypeConstant;
import com.wowotoffer.shelf.common.core.entity.constant.ParamsConstant;
import com.wowotoffer.shelf.common.core.exception.ValidateCodeException;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * @author of
 * @version 1.0
 * @date 2021/3/27 17:46
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter{

    @Autowired
    private ValidateCodeService validateCodeService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        RequestMatcher matcher = new AntPathRequestMatcher(EndpointConstant.OAUTH_TOKEN, HttpMethod.POST.toString());
        if(matcher.matches(request) && StringUtils.equalsIgnoreCase(request.getParameter(ParamsConstant.GRANT_TYPE), GrantTypeConstant.PASSWORD)){
            try {
                validateCode(request);
                filterChain.doFilter(request, response);
            }catch (Exception e){
                ShelfResponse shelfResponse = new ShelfResponse();
                ShelfUtil.makeFailureResponse(response, shelfResponse.message(e.getMessage()));
            }
        }else {
            filterChain.doFilter(request, response);
        }
    }

    /**
     * 校验图片验证码
     * @param httpServletRequest
     * @throws ValidateCodeException
     */
    private void validateCode(HttpServletRequest httpServletRequest) throws ValidateCodeException {
        String code = httpServletRequest.getParameter(ParamsConstant.VALIDATE_CODE_CODE);
        String key = httpServletRequest.getParameter(ParamsConstant.VALIDATE_CODE_KEY);
        validateCodeService.check(key, code);
    }
}
