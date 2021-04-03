package com.wowotoffer.shelf.common.doc.gateway.configure;

import com.wowotoffer.shelf.common.doc.gateway.filter.ShelfDocGatewayHeaderFilter;
import com.wowotoffer.shelf.common.doc.gateway.properties.ShelfDocGatewayProperties;
import org.apache.xerces.parsers.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/3 20:01
 */
@Configuration
@EnableConfigurationProperties(ShelfDocGatewayProperties.class)
@ConditionalOnProperty(value = "shelf.doc.gateway.enable", havingValue = "true", matchIfMissing = true)
public class ShelfDocGatewayAutoConfigure {

    private final ShelfDocGatewayProperties shelfDocGatewayProperties;
    private SecurityConfiguration securityConfiguration;
//    private UiConfiguration uiConfiguration;

    public ShelfDocGatewayAutoConfigure(ShelfDocGatewayProperties shelfDocGatewayProperties) {
        this.shelfDocGatewayProperties = shelfDocGatewayProperties;
    }

    @Autowired(required = false)
    public void setSecurityConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

//    @Bean
//    public ShelfDocGatewayResourceConfigure febsDocGatewayResourceConfigure(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
//        return new ShelfDocGatewayResourceConfigure(routeLocator, gatewayProperties);
//    }


    @Bean
    public ShelfDocGatewayHeaderFilter shelfDocGatewayHeaderFilter(){
        return new ShelfDocGatewayHeaderFilter();
    }

}
