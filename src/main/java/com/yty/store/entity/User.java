package com.yty.store.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Yang
 * @create 2023-05-30 13:55
 * @Description:
 */
@Data
public class User extends BaseEntity implements Serializable {
    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
}
