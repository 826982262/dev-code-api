package com.nev.cg.build;

import com.nev.cg.model.BuildConfigInfo;

import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Pojo构建
 * @Date 2019/6/14 19:13
 *****/
public class PomBuilder {


    /***
     * 构建Pojo
     * @param dataModel
     */
    public static void builder(Map<String,Object> dataModel, BuildConfigInfo buildConfigInfo){
        //生成Pojo层文件
        BuilderFactory.pomBuilder(dataModel,
                "/template/pom",
                "pom.xml",
                buildConfigInfo.getPREJECT_NAME(),
                ".xml",buildConfigInfo);
    }

}
