package com.wowotoffer.shelf.common.security.starter.annotation;

import com.wowotoffer.shelf.common.security.starter.configure.ShelfCloudResourceServerConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/18 21:35
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ShelfCloudResourceServerConfigure.class)
public @interface EnableShelfCloudResourceServer {
}
