package com.wowotoffer.shelf.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author of
 * @version 1.0
 * @date 2021/3/19 12:52
 */
@Data
public class ShelfAuthUser implements Serializable {
    private static final long serialVersionUID = -1748289340320186418L;

    private String username;

    private String password;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
