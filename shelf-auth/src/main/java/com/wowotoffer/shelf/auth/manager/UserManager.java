package com.wowotoffer.shelf.auth.manager;

import com.wowotoffer.shelf.auth.mapper.MenuMapper;
import com.wowotoffer.shelf.auth.mapper.UserMapper;
import com.wowotoffer.shelf.auth.mapper.UserRoleMapper;
import com.wowotoffer.shelf.common.core.entity.constant.ShelfConstant;
import com.wowotoffer.shelf.common.core.entity.system.Menu;
import com.wowotoffer.shelf.common.core.entity.system.SystemUser;
import com.wowotoffer.shelf.common.core.entity.system.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/23 20:52
 */
@Service
@RequiredArgsConstructor
public class UserManager {
    private final UserMapper userMapper;
    private final MenuMapper menuMapper;
    private final UserRoleMapper userRoleMapper;

    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);

        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }

    /**
     * 注册用户
     *
     * @param username username
     * @param password password
     * @return SystemUser SystemUser
     */
    @Transactional(rollbackFor = Exception.class)
    public SystemUser registUser(String username, String password) {
        SystemUser systemUser = new SystemUser();
        systemUser.setUsername(username);
        systemUser.setPassword(password);
        systemUser.setCreateTime(new Date());
        systemUser.setStatus(SystemUser.STATUS_VALID);
        systemUser.setSex(SystemUser.SEX_UNKNOW);
        systemUser.setAvatar(SystemUser.DEFAULT_AVATAR);
        systemUser.setDescription("注册用户");
        this.userMapper.insert(systemUser);

        UserRole userRole = new UserRole();
        userRole.setUserId(systemUser.getUserId());
        // 注册用户角色 ID
        userRole.setRoleId(ShelfConstant.REGISTER_ROLE_ID);
        this.userRoleMapper.insert(userRole);
        return systemUser;
    }
}
