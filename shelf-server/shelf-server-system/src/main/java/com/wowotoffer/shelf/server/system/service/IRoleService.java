package com.wowotoffer.shelf.server.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.common.core.entity.system.Role;

import java.util.List;

/**
 * @author OF
 * @create 2021-05-08 16:48
 */
public interface IRoleService extends IService<Role> {

    /**
     * 查找角色分页数据
     *
     * @param role    role
     * @param request request
     * @return 角色分页数据
     */
    IPage<Role> findRoles(Role role, QueryRequest request);

    /**
     * 获取用户角色
     *
     * @param username 用户名
     * @return 角色集
     */
    List<Role> findUserRole(String username);

    /**
     * 获取所有角色
     *
     * @return 角色列表
     */
    List<Role> findAllRoles();

    /**
     * 通过名称获取角色
     *
     * @param roleName 角色名称
     * @return 角色
     */
    Role findByName(String roleName);

    /**
     * 创建角色
     *
     * @param role role
     */
    void createRole(Role role);

    /**
     * 删除角色
     *
     * @param roleIds 角色id数组
     */
    void deleteRoles(String[] roleIds);

    /**
     * 更新角色
     *
     * @param role role
     */
    void updateRole(Role role);
}

