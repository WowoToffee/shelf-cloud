package com.wowotoffer.shelf.common.annotation;

import com.wowotoffer.shelf.common.configure.ShelfAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/22 21:43
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ShelfAuthExceptionConfigure.class)
public @interface EnableShelfAuthExceptionHandler {
}
