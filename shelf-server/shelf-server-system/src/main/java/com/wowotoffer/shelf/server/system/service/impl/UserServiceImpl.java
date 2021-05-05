package com.wowotoffer.shelf.server.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.common.core.entity.constant.ShelfConstant;
import com.wowotoffer.shelf.common.core.entity.system.SystemUser;
import com.wowotoffer.shelf.common.core.utils.SortUtil;
import com.wowotoffer.shelf.server.system.mapper.UserMapper;
import com.wowotoffer.shelf.server.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author of
 * @version 1.0
 * @date 2021/5/5 19:51
 */
@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class UserServiceImpl extends ServiceImpl<UserMapper, SystemUser> implements IUserService {

    @Override
    public IPage<SystemUser> findUserDetailList(SystemUser user, QueryRequest request) {
        Page<SystemUser> page = new Page<>(request.getPageNum(), request.getPageSize());
        SortUtil.handlePageSort(request, page, "userId", ShelfConstant.ORDER_ASC, false);
        return this.baseMapper.findUserDetailPage(page, user);
    }
}
