package com.wxw.code.sync.demo;

import com.wxw.code.sync.annotation.CreatorTableCol;

import java.util.Date;

public class TableListDemo {

    @CreatorTableCol(sort = 1,name = "姓名", key = "name")
    private String name;

    @CreatorTableCol(sort = 2,name = "年龄", key = "age")
    private String age;

    @CreatorTableCol(sort = 3,name = "手机号", key = "mobile", hasPermi = "tableList:mobile")
    private String mobile;

    @CreatorTableCol(sort = 4,name = "性别", key = "sex")
    private String sex;

    @CreatorTableCol(sort = 5,name = "创建时间", key = "createTime")
    private Date createTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
