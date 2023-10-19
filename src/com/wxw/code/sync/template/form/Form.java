package com.wxw.code.sync.template.form;

import com.wxw.code.sync.common.LineBuilder;

import java.util.List;

public class Form {
    public static final String FORM_START = "<el-form :inline=\"true\" :rules=\"rules\" :model=\"ruleForm\" ref=\"ruleForm\" label-width=\"100px\" class=\"demo-form-inline\">";
    public static final String FORM_END = "</el-form>";

    /**
     * 搜索按钮权限
     */
    private String searchPermi;
    /**
     * 导出按钮权限
     */
    private String exportPermi;
    /**
     * 是否导出
     */
    private boolean isExport;
    /**
     * 表单项列表
     */
    private List<FormItem> formItems;

    public void buildForm(LineBuilder lb) {
        lb.appendTag(FORM_START);
        if (this.formItems == null || this.formItems.isEmpty()) {
            throw new RuntimeException("表单列表为空！！！");
        }
        formItems.forEach(formItem -> formItem.buildFormItem(lb));
        lb.appendTag("<el-form-item>")
                .appendTag("<el-button  v-if=\"hasPermi(['"+this.searchPermi+"'])\" type=\"primary\" icon=\"el-icon-search\" @click=\"search\">")
                .append("搜索")
                .appendTag("</el-button>")
                .appendTag("<el-button @click=\"resetForm('ruleForm')\">")
                .appendTag("重置")
                .appendTag("</el-button>");

        if (isExport) {
            lb.appendTag("<el-button v-if=\"hasPermi(['"+this.exportPermi+"'])\" type=\"primary\" @click=\"handleExport\" :disabled=\"isExport\">")
                    .appendTag("导出excel")
                    .appendTag("</el-button>");
        }

        lb.appendTag("</el-form-item>");
        lb.appendTag(FORM_END);
    }

    public String getSearchPermi() {
        return searchPermi;
    }

    public void setSearchPermi(String searchPermi) {
        this.searchPermi = searchPermi;
    }

    public String getExportPermi() {
        return exportPermi;
    }

    public void setExportPermi(String exportPermi) {
        this.exportPermi = exportPermi;
    }

    public List<FormItem> getFromItems() {
        return formItems;
    }

    public void setFromItems(List<FormItem> formItems) {
        this.formItems = formItems;
    }

    public List<FormItem> getFormItems() {
        return formItems;
    }

    public void setFormItems(List<FormItem> formItems) {
        this.formItems = formItems;
    }

    public boolean isExport() {
        return isExport;
    }

    public void setExport(boolean export) {
        isExport = export;
    }
}
