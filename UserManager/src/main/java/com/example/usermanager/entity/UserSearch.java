package com.example.usermanager.entity;

import lombok.Data;

/**
 * ClassName: UserSearch
 * Package: com.example.usermanager.entity
 *
 * @Author: QINKUI
 * @createTime: 2024年03月11日 12:12:26
 */
@Data
public class UserSearch {
    private String userName;//用户名关键字

    private Integer roleId;//角色

    private Integer pageNo;//当前页

    private Integer pageSize;//每页显示的条数
}
