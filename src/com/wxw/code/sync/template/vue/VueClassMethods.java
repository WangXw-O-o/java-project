package com.wxw.code.sync.template.vue;

import com.wxw.code.sync.common.LineBuilder;
import com.wxw.code.sync.common.Common;

public class VueClassMethods {

    public static void buildVueClass(boolean isExport, LineBuilder lb) {
        lb.append("methods: {")
                .append("resetForm(formName) {")
                    .append("this.$refs[formName].resetFields();")
                .append("},")

                .append("formatDate: function(row) {")
                    .append("if(row != null){")
                        .append("return this.$moment(row).format(\"YYYY-MM-DD HH:mm:ss\");")
                    .append("}")
                .append("},")

                .append("search() {")
                    .append("this.getSearchList();")
                .append("},")

                .append("handleSizeChange: function(val) {")
                    .append("this.ruleForm.pageSize = val;")
                    .append("this.getSearchList();")
                .append("},")

                .append("handleCurrentChange: function(val) {")
                    .append("this.ruleForm.pageNo = val;")
                    .append("this.getSearchList();")
                .append("},")

                .append("getSearchList() {")
                    .append(Common.QUERY_LIST_FUN_NAME+"(this.ruleForm).then(r => {")
                        .append("if (r.code === 0) {")
                            .append("this.tableData = r.data;")
                            .append("this.isExport = r.data.list.length <= 0;")
                        .append("} else {")
                            .append("this.isExport = false;")
                        .append("}")
                .append("});")
                .append("},");
        if (isExport) {
            lb.append("handleExport() {")
                    .append("let that= this;")
                    .append("this.$confirm('是否确认导出列表?', \"警告\", {")
                        .append("confirmButtonText: \"确定\",")
                        .append("cancelButtonText: \"取消\",")
                        .append("type: \"warning\"")
                    .append("}).then(function() {")
                        .append(Common.EXPORT_LIST_FUN_NAME+"(that.ruleForm).then(r => {")
                            .append("that.$message({")
                                .append("message: r.rtnInfo,")
                                .append("type: 'success'")
                            .append("})")
                        .append("})")
                    .append("}).catch(error=>console.info(error));")
               .append("},");
        }
        lb.append("}");
    }

}
