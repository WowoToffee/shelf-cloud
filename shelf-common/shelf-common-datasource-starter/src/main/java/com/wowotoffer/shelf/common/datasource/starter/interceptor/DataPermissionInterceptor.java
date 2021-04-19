package com.wowotoffer.shelf.common.datasource.starter.interceptor;

import com.baomidou.mybatisplus.extension.handlers.AbstractSqlParserHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/19 21:32
 */
public class DataPermissionInterceptor extends AbstractSqlParserHandler implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }
}
