package com.wxw.code.sync.template.form;

import com.wxw.code.sync.common.LineBuilder;
import com.wxw.code.sync.common.InputTypeEnum;

import java.util.List;

public class FormItem {

    private InputTypeEnum type;
    private String name;
    private String key;
    private String hasPermi;
    private FormItem startDate;
    private FormItem endDate;
    private List<FormItem> selectOption;
    private int formSort;

    public FormItem() {
    }

    public FormItem(String name, String key) {
        this.name = name;
        this.key = key;
    }

    public LineBuilder buildFormItem(LineBuilder lb) {
        if (this.type == null) {
            throw new RuntimeException("表单类型为空！！！");
        }
        switch (this.type) {
            case INPUT -> { return buildInput(lb); }
            case SELECT -> { return buildSelect(lb); }
            case DATE_PICKER -> { return buildDate(lb); }
            default -> throw new RuntimeException("暂时不支持其他类型的表单生成！!!");
        }
    }

    private LineBuilder buildInput(LineBuilder lb) {
        String pre = "";
        if (this.hasPermi != null && !this.hasPermi.isEmpty()) {
            pre = " v-if=\"hasPermi(['" + this.hasPermi + "'])\"";
        }
        lb.appendTag("<el-form-item label=\""+this.name+"\" prop=\""+this.key+"\""+pre+">")
                .appendTag("<el-input v-model=\"ruleForm."+this.key+"\" placeholder=\"请输入\" clearable>")
                .appendTag("</el-input>")
                .appendTag("</el-form-item>");
        return lb;
    }

    private LineBuilder buildSelect(LineBuilder lb) {
        String pre = "";
        if (this.hasPermi != null && !this.hasPermi.isEmpty()) {
            pre = " v-if=\"hasPermi(['" + this.hasPermi + "'])\"";
        }
        lb.appendTag("<el-form-item label=\""+this.name+"\" prop=\""+this.key+pre+"\">")
                .appendTag("<el-select v-model=\"ruleForm."+this.key+"\" style=\"width: 150px\" clearable>");

        lb.appendTagWithoutTabCheck("<el-option disabled value=\"\">请选择</el-option>");
        for (FormItem item : this.selectOption) {
            lb.appendTagWithoutTabCheck("<el-option label=\""+item.getName()+"\" value=\""+item.getKey()+"\"></el-option>");
        }
        lb.appendTag("</el-select>")
                .appendTag("</el-form-item>");
        return lb;
    }

    private LineBuilder buildDate(LineBuilder lb) {
        if (this.startDate == null || this.endDate == null) {
            throw new RuntimeException("时间选择器的开始时间或结束时间为空！！！");
        }

        String pre = "";
        if (this.hasPermi != null && !this.hasPermi.isEmpty()) {
            pre = " v-if=\"hasPermi(['" + this.hasPermi + "'])\"";
        }

        lb.appendTag("<el-form-item label=\""+this.getName() + pre +"\">");

        buildDatePicker(startDate, lb);
        lb.appendTagWithoutTabCheck("-");
        buildDatePicker(endDate, lb);

        lb.appendTag("</el-form-item>");
        return lb;
    }

    private void buildDatePicker(FormItem formItem, LineBuilder lb) {
        lb.appendTag("<el-form-item prop=\""+ formItem.getKey()+"\" clearable>")
                .appendTag("<el-date-picker prop=\""+ formItem.getKey()+"\" format=\"yyyy-MM-dd\" value-format=\"yyyy-MM-dd\" " +
                        "v-model=\"ruleForm."+ formItem.getKey()+"\" type=\"datetime\" placeholder=\""+ formItem.getName()+"\" " +
                        "name=\""+ formItem.getKey()+"\">")
                .appendTag("</el-date-picker>")
                .appendTag("</el-form-item>");
    }

    public InputTypeEnum getType() {
        return type;
    }

    public void setType(InputTypeEnum type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getHasPermi() {
        return hasPermi;
    }

    public void setHasPermi(String hasPermi) {
        this.hasPermi = hasPermi;
    }

    public List<FormItem> getSelectOption() {
        return selectOption;
    }

    public void setSelectOption(List<FormItem> selectOption) {
        this.selectOption = selectOption;
    }

    public FormItem getStartDate() {
        return startDate;
    }

    public void setStartDate(FormItem startDate) {
        this.startDate = startDate;
    }

    public FormItem getEndDate() {
        return endDate;
    }

    public void setEndDate(FormItem endDate) {
        this.endDate = endDate;
    }

    public int getFormSort() {
        return formSort;
    }

    public void setFormSort(int formSort) {
        this.formSort = formSort;
    }
}
