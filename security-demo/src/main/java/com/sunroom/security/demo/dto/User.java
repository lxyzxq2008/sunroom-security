package com.sunroom.security.demo.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.sunroom.security.demo.validator.MyConstraint;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

public class User {

    /**
     * 使用JSONView的步骤：
     * 1. 使用接口来声明多个视图
     * 2. 在值对象的get方法上指定视图
     * 3. 在Controller方法上指定视图
     */
    public interface UserSimpleView {};
    public interface UserDetailView extends UserSimpleView {};

    @MyConstraint(message = "这是一个测试")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String id;
    /**
     * 在前后端分离的架构中，时间传递时按照时间戳来进行传递
     * 前台和后台最终想要的格式，有前后台自己进行转换处理
     */
    @Past(message = "生日必须是过去时间")
    private Date birthday;

    @JsonView(UserSimpleView.class)
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @JsonView(UserSimpleView.class)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
