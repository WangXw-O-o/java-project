package com.wxw.code.sync.common;

public enum InputTypeEnum {
    INPUT("input", "输入表单"),
    SELECT("select", "下拉框"),
    OPTION("option", "下拉框"),
    DATE_PICKER("datePicker", "时间区间查询"),
    ;

    private String type;
    private String name;

    InputTypeEnum(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
