package com.wowotoffer.shelf.auth.manager;

import com.wowotoffer.shelf.auth.mapper.MenuMapper;
import com.wowotoffer.shelf.auth.mapper.UserMapper;
import com.wowotoffer.shelf.common.entity.system.Menu;
import com.wowotoffer.shelf.common.entity.system.SystemUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/23 20:52
 */
@Service
public class UserManager {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    public SystemUser findByName(String username) {
        return userMapper.findByName(username);
    }

    public String findUserPermissions(String username) {
        List<Menu> userPermissions = menuMapper.findUserPermissions(username);

        return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(","));
    }
}
