package com.wxw.code.sync.template.vue;

import com.wxw.code.sync.common.LineBuilder;
import com.wxw.code.sync.common.Common;

import java.util.Map;

public class VueClass {

    private String name;
    private String funcUrl;
    private boolean isExport;
    private Map<String, String> ruleFormMap;

    public void buildVueClass(LineBuilder lb) {
        lb.append("import {hasPermi} from \"@/utils/auth\";")
                .append("import {")
                    .append(Common.QUERY_LIST_FUN_NAME+", " +Common.EXPORT_LIST_FUN_NAME)
                .append("} from \""+funcUrl+"\";")
                .append("export default {")
                .append("name: '"+name+"',");

        buildVueData(lb, ruleFormMap);

        lb.append("created() {")
                    .append("this.getSearchList();")
                .append("},")
                .append("computed: {")
                .append("//权限判定")
                .append("hasPermi() {")
                .append("return value => {")
                .append("return hasPermi(value);")
                .append("};")
                .append("}")
                .append("},");
        VueClassMethods.buildVueClass(isExport, lb);
        lb.append("}");
    }

    public void buildVueData(LineBuilder lb, Map<String, String> ruleFormMap) {
        if (ruleFormMap == null || ruleFormMap.isEmpty()) {
            throw new RuntimeException("表单内容为空！！！");
        }
        lb.append("data() {")
                .append("return {")
                .append("rules: {},")
                .append("tableData: [],")
                .append("isExport: true,")
                .append("ruleForm: {");
        ruleFormMap.forEach((k, v) -> {
            String line = k + ": '" + v + "',";
            lb.append(line);
        });
        lb.append("},").append("}").append("},");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFuncUrl() {
        return funcUrl;
    }

    public void setFuncUrl(String funcUrl) {
        this.funcUrl = funcUrl;
    }

    public boolean isExport() {
        return isExport;
    }

    public void setExport(boolean export) {
        isExport = export;
    }

    public Map<String, String> getRuleFormMap() {
        return ruleFormMap;
    }

    public void setRuleFormMap(Map<String, String> ruleFormMap) {
        this.ruleFormMap = ruleFormMap;
    }
}
