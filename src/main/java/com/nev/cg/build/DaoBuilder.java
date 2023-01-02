package com.nev.cg.build;

import com.nev.cg.model.BuildConfigInfo;

import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:Dao构建
 * @Date 2019/6/14 19:13
 *****/
public class DaoBuilder {


    /***
     * 构建Dao
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap, BuildConfigInfo buildConfigInfo){
        //生成Dao层文件
        BuilderFactory.builder(modelMap,
                "/template/mapper",
                "Mapper.java",
                buildConfigInfo.getPACKAGE_MAPPER(),
                "Mapper.java",buildConfigInfo);
    }

}
