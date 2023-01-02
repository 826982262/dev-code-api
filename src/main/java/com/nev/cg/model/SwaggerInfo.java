package com.nev.cg.model;

import lombok.Data;

@Data
public class SwaggerInfo {
    //是否使用swagger
    public  Boolean SWAGGER;

    //服务名字
    public  String SERVICENAME;

    //swagger-ui路径
    public  String SWAGGERUI_PATH;
}
