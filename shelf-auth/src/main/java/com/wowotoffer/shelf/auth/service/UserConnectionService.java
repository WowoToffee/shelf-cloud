package com.wowotoffer.shelf.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wowotoffer.shelf.auth.entity.UserConnection;

import java.util.List;

/**
 * @author OF
 * @create 2021-04-25 10:39
 */
public interface UserConnectionService extends IService<UserConnection> {

    /**
     * 根据条件查询关联关系
     *
     * @param username 用户名
     * @return 关联关系
     */
    List<UserConnection> selectByCondition(String username);

    /**
     * 根据条件查询关联关系
     *
     * @param providerName   平台名称
     * @param providerUserId 平台用户ID
     * @return 关联关系
     */
    UserConnection selectByCondition(String providerName, String providerUserId);

    /**
     * 新增
     *
     * @param userConnection userConnection
     */
    void createUserConnection(UserConnection userConnection);

    /**
     * 删除
     *
     * @param username     username 用户名
     * @param providerName providerName 平台名称
     */
    void deleteByCondition(String username, String providerName);
}
