package com.nev.cg.build;

import com.nev.cg.model.BuildConfigInfo;

import java.util.Map;


/****
 * @Author:shenkunlin
 * @Description:Controller构建
 * @Date 2019/6/14 19:13
 *****/
public class ApplicationBuilder {

    /***
     * 构建Controller
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap, BuildConfigInfo buildConfigInfo){
        System.out.println("生成application.yml");
        //生成Controller层文件
        BuilderFactory.ResourcesBuilder(modelMap,
                "/template/application",
                "application.yml",
                "",
                ".yml",buildConfigInfo);
    }

}
