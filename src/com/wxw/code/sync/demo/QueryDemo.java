package com.wxw.code.sync.demo;

import com.wxw.code.sync.annotation.CreatorForm;
import com.wxw.code.sync.annotation.CreatorFormItem;
import com.wxw.code.sync.common.InputTypeEnum;

@CreatorForm(searchPermi = "demoQueryList:list", exportPermi = "demoQueryList:export")
public class QueryDemo {

    @CreatorFormItem(sort = 1, name = "姓名", key = "name", type = InputTypeEnum.INPUT, hasPermi = "query:name")
    private String name;

    @CreatorFormItem(sort = 2, name = "年龄", key = "age", type = InputTypeEnum.INPUT)
    private String age;

    @CreatorFormItem(sort = 3, name = "手机号", key = "mobile", type = InputTypeEnum.INPUT)
    private String mobile;

    @CreatorFormItem(sort = 4, name = "性别", key = "sex", type = InputTypeEnum.SELECT, selectOption = "1,男,2,女")
    private String sex;

    @CreatorFormItem(sort = 5, dateName = "创建时间", name = "开始时间", key = "startDate", type = InputTypeEnum.DATE_PICKER, dateType = 1)
    private String startDate;

    @CreatorFormItem(sort = 5, dateName = "创建时间", name = "结束时间", key = "endDate", type = InputTypeEnum.DATE_PICKER, dateType = 2)
    private String endDate;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
