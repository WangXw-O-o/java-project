package com.wxw.code.sync.template;

import com.wxw.code.sync.common.LineBuilder;
import com.wxw.code.sync.annotation.CreatorForm;
import com.wxw.code.sync.annotation.CreatorFormItem;
import com.wxw.code.sync.annotation.CreatorTableCol;
import com.wxw.code.sync.common.InputTypeEnum;
import com.wxw.code.sync.template.form.Form;
import com.wxw.code.sync.template.form.FormItem;
import com.wxw.code.sync.template.table.TableList;
import com.wxw.code.sync.template.table.TableListItem;
import com.wxw.code.sync.template.vue.VueClass;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class TemplateCreator<F, T> {

    //表单对象实体
    private final Class<F> formClass;
    //列表对象实体
    private final Class<T> tableClass;

    //vue class 名称
    private String vueClassName;
    //文件的路径
    private String jsImportPath;
    //是否有导出功能
    private boolean isExport;
    //form表单
    private Form form;
    //查询结果列表
    private TableList tableList;
    //vue脚本对象
    private VueClass vueClass;
    //表单项Map
    private Map<String, String> ruleFormMap;

    public TemplateCreator(Class<F> formClass, Class<T> tableClass) {
        this.formClass = formClass;
        this.tableClass = tableClass;
    }

    public String create() {
        this.form = getForm();
        this.tableList = getTableList();
        this.vueClass = getVueClass();
        return createTemplate();
    }

    private String createTemplate() {
        LineBuilder lb = new LineBuilder();

        lb.appendTag("<template>")
                .appendTag("<div class=\"app-container\">");

        form.buildForm(lb);
        tableList.buildTableList(lb);

        lb.appendTag("</div>")
                .appendTag("</template>");

        lb.append("<script>");
        vueClass.buildVueClass(lb);
        lb.append("</script>");
        return lb.toString();
    }

    private Form getForm() {
        Form form = new Form();
        form.setExport(isExport);
        CreatorForm annotation = formClass.getAnnotation(CreatorForm.class);
        form.setSearchPermi(annotation.searchPermi());
        form.setExportPermi(annotation.exportPermi());
        form.setFromItems(getFormItemList());
        return form;
    }

    private List<FormItem> getFormItemList()  {
        List<FormItem> list = new ArrayList<>();

        FormItem datePickerItem = new FormItem();
        boolean hasDatePicker = false;

        Field[] fields = formClass.getDeclaredFields();
        for (Field field : fields) {
            CreatorFormItem creatorFormItem = field.getAnnotation(CreatorFormItem.class);
            //时间选择器特殊处理
            if (creatorFormItem.type().equals(InputTypeEnum.DATE_PICKER)) {
                hasDatePicker = true;
                datePickerItem.setName(creatorFormItem.dateName());
                datePickerItem.setFormSort(creatorFormItem.sort());
                datePickerItem.setType(creatorFormItem.type());
                createDatePicker(creatorFormItem, datePickerItem);
                continue;
            }
            //其他表单处理
            FormItem formItem = new FormItem();
            list.add(formItem);

            formItem.setFormSort(creatorFormItem.sort());
            formItem.setKey(creatorFormItem.key());
            formItem.setName(creatorFormItem.name());
            formItem.setType(creatorFormItem.type());
            formItem.setHasPermi(creatorFormItem.hasPermi());
            //解析下拉框选项
            if (creatorFormItem.type().equals(InputTypeEnum.SELECT)) {
                List<FormItem> optionList = new ArrayList<>();
                formItem.setSelectOption(optionList);

                String[] optionArray = creatorFormItem.selectOption().split(",");
                for (int i = 0; i < optionArray.length; i++) {
                    FormItem option = new FormItem();
                    option.setKey(optionArray[i]);
                    option.setName(optionArray[++i]);
                    optionList.add(option);
                }
            }
        }
        if (hasDatePicker) {
            list.add(datePickerItem);
        }
        return list.stream().sorted(Comparator.comparing(FormItem::getFormSort)).collect(Collectors.toList());
    }

    private void createDatePicker(CreatorFormItem creatorFormItem, FormItem datePickerItem) {
        if (creatorFormItem.dateType() == 1) {
            FormItem dateStart = new FormItem();
            dateStart.setKey(creatorFormItem.key());
            dateStart.setName(creatorFormItem.name());
            datePickerItem.setStartDate(dateStart);
        } else if (creatorFormItem.dateType() == 2) {
            FormItem dateEnd = new FormItem();
            dateEnd.setKey(creatorFormItem.key());
            dateEnd.setName(creatorFormItem.name());
            datePickerItem.setEndDate(dateEnd);
        } else {
            throw new RuntimeException("未知表单时间选择器排序类型！");
        }
    }

    private TableList getTableList() {
        TableList tableList = new TableList();
        tableList.setColList(getTableListItemList());
        return tableList;
    }

    private List<TableListItem> getTableListItemList() {
        List<TableListItem> list = new ArrayList<>();

        Field[] fields =  tableClass.getDeclaredFields();
        for (Field field : fields) {
            CreatorTableCol creatorTableCol = field.getAnnotation(CreatorTableCol.class);
            TableListItem tableListItem = new TableListItem();
            list.add(tableListItem);

            tableListItem.setSort(creatorTableCol.sort());
            tableListItem.setKey(creatorTableCol.key());
            tableListItem.setName(creatorTableCol.name());
            tableListItem.setHasPermi(creatorTableCol.hasPermi());
        }
        return list.stream().sorted(Comparator.comparing(TableListItem::getSort)).collect(Collectors.toList());
    }

    private Map<String, String> getRuleFormMap() {
        if (this.ruleFormMap != null) {
            return this.ruleFormMap;
        }
        if (this.form == null) {
            return null;
        }
        this.ruleFormMap = new HashMap<>();
        List<FormItem> formItems = this.form.getFromItems();
        for (FormItem item : formItems) {
            if (item.getType().equals(InputTypeEnum.DATE_PICKER)) {
                FormItem startDate = item.getStartDate();
                FormItem endDate = item.getEndDate();
                this.ruleFormMap.put(startDate.getKey(), "");
                this.ruleFormMap.put(endDate.getKey(), "");
                continue;
            }
            this.ruleFormMap.put(item.getKey(), "");
        }
        return this.ruleFormMap;
    }

    public VueClass getVueClass() {
        if (isEmpty(this.vueClassName)) {
            throw new RuntimeException("请设置vue class name：vueClassName");
        }
        if (isEmpty(this.jsImportPath)) {
            throw new RuntimeException("请设置文件路径：filePath");
        }
        VueClass vueClass = new VueClass();
        vueClass.setExport(isExport);
        vueClass.setFuncUrl(jsImportPath);
        vueClass.setRuleFormMap(getRuleFormMap());
        vueClass.setName(this.vueClassName);
        return vueClass;
    }

    private boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public String getVueClassName() {
        return vueClassName;
    }

    public void setVueClassName(String vueClassName) {
        this.vueClassName = vueClassName;
    }

    public boolean isExport() {
        return isExport;
    }

    public void setExport(boolean export) {
        isExport = export;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    public void setTableList(TableList tableList) {
        this.tableList = tableList;
    }

    public String getJsImportPath() {
        return jsImportPath;
    }

    public void setJsImportPath(String jsImportPath) {
        this.jsImportPath = jsImportPath;
    }

    public void setVueClass(VueClass vueClass) {
        this.vueClass = vueClass;
    }

    public void setRuleFormMap(Map<String, String> ruleFormMap) {
        this.ruleFormMap = ruleFormMap;
    }
}
