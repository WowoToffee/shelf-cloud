package com.wowotoffer.shelf.common.core.handler;

import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.exception.ShelfException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常
 *
 * @author of
 * @version 1.0
 * @date 2021/3/22 21:48
 */
@Slf4j
public class BaseExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ShelfResponse handleException(Exception e) {
        log.error("系统内部异常，异常信息", e);
        return new ShelfResponse().message("系统内部异常");
    }

    @ExceptionHandler(value = ShelfException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ShelfResponse handleShelfAuthException(ShelfException e) {
        log.error("系统错误", e);
        return new ShelfResponse().message(e.getMessage());
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ShelfResponse handleAccessDeniedException(){
        return new ShelfResponse().message("没有权限访问该资源");
    }
}
