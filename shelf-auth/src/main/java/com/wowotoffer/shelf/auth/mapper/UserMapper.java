package com.wowotoffer.shelf.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wowotoffer.shelf.common.entity.system.SystemUser;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/23 20:41
 */
public interface UserMapper extends BaseMapper<SystemUser> {
    /**
     * 根据姓名查找用户
     *
     * @param name
     * @return
     */
    SystemUser findByName(String name);
}
