package com.wowotoffer.shelf.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wowotoffer.shelf.common.core.entity.system.Menu;

import java.util.List;

/**
 * @author of
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 获取用户权限集
     *
     * @param username 用户名
     * @return 用户权限集
     */
    List<Menu> findUserPermissions(String username);

    /**
     * 获取用户菜单
     *
     * @param username 用户名
     * @return 用户菜单
     */
    List<Menu> findUserMenus(String username);
}