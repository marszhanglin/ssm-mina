package com.mvc.model;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String password;

    private String email;

    private Date birthday;

    private Date createTime;

    private String iosessionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getIosessionId() {
        return iosessionId;
    }

    public void setIosessionId(String iosessionId) {
        this.iosessionId = iosessionId == null ? null : iosessionId.trim();
    }
}