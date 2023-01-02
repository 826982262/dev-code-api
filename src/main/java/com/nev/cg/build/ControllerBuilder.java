package com.nev.cg.build;

import com.nev.cg.model.BuildConfigInfo;

import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Controller构建
 * @Date 2019/6/14 19:13
 *****/
public class ControllerBuilder {

    /***
     * 构建Controller
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap, BuildConfigInfo buildConfigInfo){
        //生成Controller层文件
        BuilderFactory.builder(modelMap,
                "/template/controller",
                "Controller.java",
                buildConfigInfo.getPACKAGE_CONTROLLER(),
                "Controller.java",buildConfigInfo);
    }

}
