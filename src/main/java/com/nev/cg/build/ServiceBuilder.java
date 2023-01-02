package com.nev.cg.build;

import com.nev.cg.model.BuildConfigInfo;

import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Service构建
 * @Date 2019/6/14 19:13
 *****/
public class ServiceBuilder {


    /***
     * 构建Service
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap, BuildConfigInfo buildConfigInfo){
        //生成Service层文件
        BuilderFactory.builder(modelMap,
                "/template/service",
                "Service.java",
                buildConfigInfo.getPACKAGE_SERVICE_INTERFACE(),
                "Service.java",buildConfigInfo);
    }

}
