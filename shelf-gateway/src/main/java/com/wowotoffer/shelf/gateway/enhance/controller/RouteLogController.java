package com.wowotoffer.shelf.gateway.enhance.controller;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.gateway.enhance.entity.RouteLog;
import com.wowotoffer.shelf.gateway.enhance.service.RouteLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/13 20:39
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("route/auth/log")
public class RouteLogController {
    private final RouteLogService routeLogService;

    @GetMapping("data")
    public Flux<RouteLog> findRouteLogsPages(QueryRequest request, RouteLog routeLog){
        return routeLogService.findPages(request, routeLog);
    }

    @GetMapping("count")
    public Mono<Long> findRouteLogsCount(RouteLog routeLog) {
        return routeLogService.findCount(routeLog);
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin')")
    public Flux<RouteLog> deleteRouteLogs(String ids) {
        return routeLogService.delete(ids);
    }
}
