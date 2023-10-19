package com.wxw.code;

import com.wxw.code.sync.demo.QueryDemo;
import com.wxw.code.sync.demo.TableListDemo;
import com.wxw.code.sync.js.JsCreator;
import com.wxw.code.sync.template.TemplateCreator;

import java.io.*;

public class CodeFileCreator {

    /**
     * .vue文件路径，自己建好文件夹，没写对应的逻辑
     */
    private static final String vueFilePath = "E:\\idea\\workspace\\qhb_agent\\agt_2.0_web_h5\\src\\views\\demo\\demo.vue";
    /**
     * vue class 的 name，js文件名
     */
    private static final String name = "demoQuery";
    /**
     * .js文件路径，自己建好文件夹，没写对应的逻辑，文件名称分开定义，关系到导入路径的问题
     */
    private static final String jsFilePath = "E:\\idea\\workspace\\qhb_agent\\agt_2.0_web_h5\\src\\api\\demo\\" + name + ".js";
    /**
     * .js 文件保存路径，相对路径，用于导入js方法
     * 如： "@/api/demo/demoQuery"
     */
    private static final String importJsPath = "@/api/"
            +"demo" //自定义部分
            +"/" + name;
    /**
     * 查询列表api url
     */
    private static final String queryListUrl = "/demo/query/list";
    /**
     * 导出列表api url
     */
    private static final String exportListUrl = "/demo/query/export";
    /**
     * 是否有导出功能
     */
    private static final boolean isExport = true;

    public static void main(String[] args) throws IOException {
         createVueFile();
         createJsFile();
    }

    public static void createJsFile() throws IOException {
        JsCreator jsCreator = new JsCreator();
        jsCreator.setQueryListUrl(queryListUrl);
        jsCreator.setExportUrl(exportListUrl);
        createFile(jsFilePath, jsCreator.createJsFile());
    }

    public static void createVueFile() throws IOException {
        TemplateCreator<QueryDemo, TableListDemo> templateCreator =
                new TemplateCreator<>(QueryDemo.class, TableListDemo.class);
        templateCreator.setVueClassName(name);
        templateCreator.setJsImportPath(importJsPath);
        templateCreator.setExport(isExport);
        createFile(vueFilePath, templateCreator.create());
    }

    public static void createFile(String localPath, String data) throws IOException {
        File file = new File(localPath);
        boolean newFile = file.createNewFile();
        if (newFile) {
            System.out.println("文件创建成功！");
        } else {
            System.out.println("文件已存在！");
        }
        try (
                BufferedWriter bw = new BufferedWriter(new FileWriter(file));
                ) {
            bw.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
