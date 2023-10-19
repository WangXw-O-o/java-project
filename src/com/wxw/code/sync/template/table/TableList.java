package com.wxw.code.sync.template.table;

import com.wxw.code.sync.common.LineBuilder;

import java.util.List;

public class TableList {

    private List<TableListItem> colList;

    public void buildTableList(LineBuilder lb) {
        lb.appendTag("<div class=\"panel\">")
                .appendTag("<div class=\"panel-body container-fluid example-wrap\">")
                .appendTag("<div class=\"row row-lg example\">")
                .appendTag("<div class=\"col-lg-12\">")
                .appendTag("<el-table border height=\"500\" :data=\"tableData.list\">");

        if (colList == null || colList.isEmpty()) {
            throw new RuntimeException("列表项为空！！！");
        }

        for (TableListItem item : colList) {
            String premi = "";
            if (item.getHasPermi() != null && !item.getHasPermi().isEmpty()) {
                premi = "v-if=\"hasPermi(['"+item.getHasPermi()+"'])\" ";
            }
            lb.appendTagWithoutTabCheck("<el-table-column prop=\""+item.getKey()+"\" label=\""+item.getName()+"\" width=\"180\" "+premi+" />");
        }

        lb.appendTag("</el-table>")
                .appendTag("</div>")
                .appendTag("</div>")
                .appendTag("<div class=\"row row-lg\">")
                .appendTag("<div class=\"col-md-6\">")
                .appendTag("<el-pagination :total=\"tableData.total\" :page-sizes=\"[10,20,30,40]\"")
                .appendTag("@size-change=\"handleSizeChange\"")
                .appendTag("@current-change=\"handleCurrentChange\"")
                .appendTag("layout=\"prev, pager, next, jumper, sizes, total\" background>")
                .appendTag("</el-pagination>")
                .appendTag("</div>")
                .appendTag("</div>")
                .appendTag("</div>")
                .appendTag("</div>")
        ;

    }

    public List<TableListItem> getColList() {
        return colList;
    }

    public void setColList(List<TableListItem> colList) {
        this.colList = colList;
    }
}
