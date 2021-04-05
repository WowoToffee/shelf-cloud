package com.wowotoffer.shelf.gateway.enhance.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author of
 * @version 1.0
 * @date 2021/4/3 22:17
 */
@Data
@Builder
@Document
@NoArgsConstructor
@AllArgsConstructor
public class RouteUser {
    @Id
    private String id;

    private String username;
    @JsonIgnore
    private String password;

    private String roles;

    private String createTime;
}
