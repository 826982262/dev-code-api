package com.nev.cg.controller;


import com.nev.base.entity.Result;
import com.nev.cg.build.TemplateBuilder;
import com.nev.cg.model.BuildConfigInfo;
import com.nev.cg.model.MysqlInfo;
import com.nev.cg.model.SwaggerInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/code")
public class creatCodeCtroller {


    @PostMapping("")
    public Result build(){

        MysqlInfo mysqlInfo = MysqlInfo.builder()
                .dbName("changgou_all")
                .driver("com.mysql.cg.jdbc.Driver")
                .pwd("Nhy6%tgb")
                .sqlUname("root")
                .sqlHost("192.168.3.100")
                .sqlPort("3306")
                .build();

        BuildConfigInfo buildConfig = BuildConfigInfo.builder()
                .mysqlInfo(mysqlInfo)
                .PREJECT_NAME("test-api")
                .MODULE_NAME("test")
                .build();
        TemplateBuilder.builder(buildConfig);

        return Result.SUCCESS();

    }

    @PostMapping("/tables")
    public Result buildByTable(){
        List<String> tables = new ArrayList<>();
        tables.add("tb_ad");
        tables.add("tb_address");
        MysqlInfo mysqlInfo = MysqlInfo.builder()
                .dbName("changgou_all")
                .driver("com.mysql.cg.jdbc.Driver")
                .pwd("Nhy6%tgb")
                .sqlUname("root")
                .sqlHost("192.168.3.100")
                .sqlPort("3306")
                .build();

        BuildConfigInfo buildConfig = BuildConfigInfo.builder()
                .mysqlInfo(mysqlInfo)
                .TABLE_NAME(tables)
                .PREJECT_NAME("test-api")
                .MODULE_NAME("test")
                .build();
        TemplateBuilder.builderBYTableNames(buildConfig);

        return Result.SUCCESS();

    }
}
