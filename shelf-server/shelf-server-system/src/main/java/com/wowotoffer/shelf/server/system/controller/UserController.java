package com.wowotoffer.shelf.server.system.controller;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.entity.system.LoginLog;
import com.wowotoffer.shelf.common.core.entity.system.SystemUser;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import com.wowotoffer.shelf.server.system.service.ILoginLogService;
import com.wowotoffer.shelf.server.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
    private final ILoginLogService loginLogService;

    @GetMapping("success")
    public void loginSuccess(HttpServletRequest request) {
        String currentUsername = ShelfUtil.getCurrentUsername();
        this.userService.updateLoginTime(currentUsername);

        LoginLog loginLog = new LoginLog();
        loginLog.setUsername(currentUsername);
        loginLog.setSystemBrowserInfo(request.getHeader("user-agent"));
        this.loginLogService.saveLoginLog(loginLog);
    }


    @GetMapping
    @PreAuthorize("hasAuthority('user:view')")
    public ShelfResponse userList(QueryRequest queryRequest, SystemUser user) {
        Map<String, Object> dataTable = ShelfUtil.getDataTable(userService.findUserDetailList(user, queryRequest));
        return new ShelfResponse().data(dataTable);
    }
}
