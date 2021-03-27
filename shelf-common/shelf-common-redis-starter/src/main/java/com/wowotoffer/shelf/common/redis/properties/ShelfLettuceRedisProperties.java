package com.wowotoffer.shelf.common.redis.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author  of
 * @date  2021/3/27 15:22
 * @version 1.0
 */
@ConfigurationProperties(prefix = "shelf.lettuce.redis")
public class ShelfLettuceRedisProperties {
    /**
     * 是否开启Lettuce Redis(Redis集群处理)
     */
    private Boolean enable = true;

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "ShelfLettuceRedisProperties{" +
                "enable=" + enable +
                '}';
    }
}
