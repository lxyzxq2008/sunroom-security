package com.sunroom.security.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class User {

    /**
     * 使用JSONView的步骤：
     * 1. 使用接口来声明多个视图
     * 2. 在值对象的get方法上指定视图
     * 3. 在Controller方法上指定视图
     */
    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    private String username;
    private String password;

    @JsonView(UserSimpleView.class)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonView(UserDetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
