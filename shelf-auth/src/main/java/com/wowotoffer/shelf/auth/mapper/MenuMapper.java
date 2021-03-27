package com.wowotoffer.shelf.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wowotoffer.shelf.common.core.entity.system.Menu;

import java.util.List;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/23 20:42
 */
public interface MenuMapper extends BaseMapper<Menu> {
    List<Menu> findUserPermissions(String username);
}
