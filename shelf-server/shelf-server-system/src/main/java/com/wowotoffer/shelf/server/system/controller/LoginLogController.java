package com.wowotoffer.shelf.server.system.controller;

import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.common.core.entity.ShelfResponse;
import com.wowotoffer.shelf.common.core.entity.constant.StringConstant;
import com.wowotoffer.shelf.common.core.entity.system.LoginLog;
import com.wowotoffer.shelf.common.core.utils.ShelfUtil;
import com.wowotoffer.shelf.server.system.annotation.ControllerEndpoint;
import com.wowotoffer.shelf.server.system.service.ILoginLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Map;

/**
 * @author OF
 * @create 2021-05-07 17:14
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("loginLog")
public class LoginLogController {

    private final ILoginLogService loginLogService;

    @GetMapping
    public ShelfResponse loginLogList(LoginLog loginLog, QueryRequest request) {
        Map<String, Object> dataTable = ShelfUtil.getDataTable(this.loginLogService.findLoginLogs(loginLog, request));
        return new ShelfResponse().data(dataTable);
    }

    @GetMapping("currentUser")
    public ShelfResponse getUserLastSevenLoginLogs() {
        String currentUsername = ShelfUtil.getCurrentUsername();
        List<LoginLog> userLastSevenLoginLogs = this.loginLogService.findUserLastSevenLoginLogs(currentUsername);
        return new ShelfResponse().data(userLastSevenLoginLogs);
    }

    @DeleteMapping("{ids}")
    @PreAuthorize("hasAuthority('loginlog:delete')")
    @ControllerEndpoint(operation = "删除登录日志", exceptionMessage = "删除登录日志失败")
    public void deleteLogs(@NotBlank(message = "{required}") @PathVariable String ids) {
        String[] loginLogIds = ids.split(StringConstant.COMMA);
        this.loginLogService.deleteLoginLogs(loginLogIds);
    }
}
