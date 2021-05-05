package com.wowotoffer.shelf.server.system.controller;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.entity.system.SystemUser;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import com.wowotoffer.shelf.server.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author of
 * @version 1.0
 * @date 2021/5/5 19:49
 */
@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final IUserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('user:view')")
    public ShelfResponse userList(QueryRequest queryRequest, SystemUser user) {
        Map<String, Object> dataTable = ShelfUtil.getDataTable(userService.findUserDetailList(user, queryRequest));
        return new ShelfResponse().data(dataTable);
    }
}
