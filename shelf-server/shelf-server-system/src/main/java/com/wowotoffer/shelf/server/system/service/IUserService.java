package com.wowotoffer.shelf.server.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.common.core.entity.system.SystemUser;

/**
 * @author of
 * @version 1.0
 * @date 2021/5/5 19:50
 */
public interface IUserService extends IService<SystemUser> {
    /**
     * 查找用户详细信息
     *
     * @param request request
     * @param user    用户对象，用于传递查询条件
     * @return IPage
     */
    IPage<SystemUser> findUserDetailList(SystemUser user, QueryRequest request);

    /**
     * 更新用户登录时间
     *
     * @param username username
     */
    void updateLoginTime(String username);
}
