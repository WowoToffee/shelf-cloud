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
    IPage<SystemUser> findUserDetailList(SystemUser user, QueryRequest request);
}
