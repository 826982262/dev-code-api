package com.nev.cg.build;

import com.nev.cg.model.BuildConfigInfo;

import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:ServiceImpl构建
 * @Date 2019/6/14 19:13
 *****/
public class ServiceImplBuilder {

    /***
     * ServiceImpl构建
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap , BuildConfigInfo buildConfigInfo){
        //生成ServiceImpl层文件
        BuilderFactory.builder(modelMap,
                "/template/service/impl",
                "ServiceImpl.java",
                buildConfigInfo.getPACKAGE_SERVICE_INTERFACE_IMPL(),
                "ServiceImpl.java",buildConfigInfo);
    }

}
