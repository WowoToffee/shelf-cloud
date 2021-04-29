package com.wowotoffer.shelf.auth.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @author of
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:shelf-auth.properties"})
@ConfigurationProperties(prefix = "shelf.auth")
public class ShelfAuthProperties {
    /**
     * 验证码配置
     */
    private ShelfValidateCodeProperties code = new ShelfValidateCodeProperties();
    /**
     * JWT加签密钥
     */
    private String jwtAccessKey;
    /**
     * 是否使用 JWT令牌
     */
    private Boolean enableJwt;

    /**
     * 社交登录所使用的 Client
     */
    private String socialLoginClientId;
}
