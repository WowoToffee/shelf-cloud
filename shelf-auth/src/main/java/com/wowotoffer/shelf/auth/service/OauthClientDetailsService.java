package com.wowotoffer.shelf.auth.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wowotoffer.shelf.auth.entity.OauthClientDetails;
import com.wowotoffer.shelf.common.core.entity.QueryRequest;
import com.wowotoffer.shelf.common.core.exception.ShelfException;

/**
 * @author OF
 * @create 2021-04-25 13:46
 */
public interface OauthClientDetailsService extends IService<OauthClientDetails> {
    /**
     * 查询（分页）
     *
     * @param request            QueryRequest
     * @param oauthClientDetails oauthClientDetails
     * @return IPage<OauthClientDetails>
     */
    IPage<OauthClientDetails> findOauthClientDetails(QueryRequest request, OauthClientDetails oauthClientDetails);

    /**
     * 根据主键查询
     *
     * @param clientId clientId
     * @return OauthClientDetails
     */
    OauthClientDetails findById(String clientId);

    /**
     * 新增
     *
     * @param oauthClientDetails oauthClientDetails
     * @throws ShelfException FebsException
     */
    void createOauthClientDetails(OauthClientDetails oauthClientDetails) throws ShelfException;

    /**
     * 修改
     *
     * @param oauthClientDetails oauthClientDetails
     */
    void updateOauthClientDetails(OauthClientDetails oauthClientDetails);

    /**
     * 删除
     *
     * @param clientIds clientIds
     */
    void deleteOauthClientDetails(String clientIds);
}
