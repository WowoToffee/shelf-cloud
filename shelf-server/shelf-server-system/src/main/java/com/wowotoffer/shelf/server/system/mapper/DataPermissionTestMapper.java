package com.wowotoffer.shelf.server.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wowotoffer.shelf.common.core.entity.system.DataPermissionTest;
import com.wowotoffer.shelf.common.datasource.starter.annotation.DataPermission;

/**
 * @author of
 */
@DataPermission(methods = {"selectPage"})
public interface DataPermissionTestMapper extends BaseMapper<DataPermissionTest> {

}
