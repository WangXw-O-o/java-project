package com.wxw.code.sync.js;

import com.wxw.code.sync.common.LineBuilder;
import com.wxw.code.sync.common.Common;

public class JsCreator {

    //查询地址
    private String queryListUrl;
    //导出地址
    private String exportUrl;

    public String createJsFile() {
        LineBuilder lb = new LineBuilder();
        lb.append("import requeststr from '@/utils/requeststr';")
                .append("")
                .append("export function "+ Common.QUERY_LIST_FUN_NAME +"(data) {")
                    .append("return requeststr({")
                        .append("url: '"+this.queryListUrl+"',")
                        .append("method: 'post',")
                        .append("data: data,")
                    .append("})")
                .append("}")
                .append("")
                .append("export function "+Common.EXPORT_LIST_FUN_NAME+"(data) {")
                    .append("return requeststr({")
                        .append("url: '"+this.exportUrl+"',")
                        .append("method: 'post',")
                        .append("data: data,")
                    .append("})")
                .append("}")
        ;
        return lb.toString();
    }

    public String getQueryListUrl() {
        return queryListUrl;
    }

    public void setQueryListUrl(String queryListUrl) {
        this.queryListUrl = queryListUrl;
    }

    public String getExportUrl() {
        return exportUrl;
    }

    public void setExportUrl(String exportUrl) {
        this.exportUrl = exportUrl;
    }

}
