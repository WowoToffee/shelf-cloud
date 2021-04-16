package com.wowotoffer.shelf.common.doc.gateway.configure;

import com.wowotoffer.shelf.common.doc.gateway.filter.ShelfDocGatewayHeaderFilter;
import com.wowotoffer.shelf.common.doc.gateway.handler.ShelfDocGatewayHandler;
import com.wowotoffer.shelf.common.doc.gateway.properties.ShelfDocGatewayProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger.web.UiConfiguration;

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
    private UiConfiguration uiConfiguration;

    public ShelfDocGatewayAutoConfigure(ShelfDocGatewayProperties shelfDocGatewayProperties) {
        this.shelfDocGatewayProperties = shelfDocGatewayProperties;
    }

    @Autowired(required = false)
    public void setSecurityConfiguration(SecurityConfiguration securityConfiguration) {
        this.securityConfiguration = securityConfiguration;
    }

    @Autowired(required = false)
    public void setUiConfiguration(UiConfiguration uiConfiguration) {
        this.uiConfiguration = uiConfiguration;
    }

    @Bean
    public ShelfDocGatewayResourceConfigure shelfDocGatewayResourceConfigure(RouteLocator routeLocator, GatewayProperties gatewayProperties) {
        return new ShelfDocGatewayResourceConfigure(routeLocator, gatewayProperties);
    }

    @Bean
    public ShelfDocGatewayHeaderFilter shelfDocGatewayHeaderFilter(){
        return new ShelfDocGatewayHeaderFilter();
    }

    @Bean
    public ShelfDocGatewayHandler shelfDocGatewayHandler(SwaggerResourcesProvider swaggerResources) {
        ShelfDocGatewayHandler shelfDocGatewayHandler = new ShelfDocGatewayHandler();
        shelfDocGatewayHandler.setSecurityConfiguration(securityConfiguration);
        shelfDocGatewayHandler.setUiConfiguration(uiConfiguration);
        shelfDocGatewayHandler.setSwaggerResources(swaggerResources);
        shelfDocGatewayHandler.setProperties(shelfDocGatewayProperties);
        return shelfDocGatewayHandler;
    }
}
