package com.wowotoffer.shelf.gateway.enhance.utils;

import com.wowotoffer.shelf.common.core.entity.constant.ShelfConstant;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/4 17:05
 */
public class RouteEnhanceCacheUtil {
    private static final String BLACKLIST_CHACHE_KEY_PREFIX = "shelf:route:blacklist:";
    private static final String RATELIMIT_CACHE_KEY_PREFIX = "shelf:route:ratelimit:";
    private static final String RATELIMIT_COUNT_KEY_PREFIX = "shelf:route:ratelimit:cout:";

    public static String getBlackListCacheKey(String ip) {
        if (ShelfConstant.LOCALHOST.equalsIgnoreCase(ip)) {
            ip = ShelfConstant.LOCALHOST_IP;
        }
        return String.format("%s%s", BLACKLIST_CHACHE_KEY_PREFIX, ip);
    }

    public static String getBlackListCacheKey() {
        return String.format("%sall", BLACKLIST_CHACHE_KEY_PREFIX);
    }

    public static String getRateLimitCacheKey(String uri, String method) {
        return String.format("%s%s:%s", RATELIMIT_CACHE_KEY_PREFIX, uri, method);
    }

    public static String getRateLimitCountKey(String uri, String ip) {
        return String.format("%s%s:%s", RATELIMIT_COUNT_KEY_PREFIX, uri, ip);
    }
}
