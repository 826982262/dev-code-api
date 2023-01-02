package com.nev.cg.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class BuildConfigInfo {

    //pojoPackage
    public static String PACKAGE_MODEL = "model";
    //pojoPackage
    public static String PACKAGE_ENTITY = "entity";
    //mapperPackage
    public static String PACKAGE_MAPPER = "mapper";

    public static String PACKAGE_MAPPING = "mapper.mapping";
    //serviceInterfacePackage
    public static String PACKAGE_SERVICE_INTERFACE = "service";
    //serviceInterfaceImplPackage
    public static String PACKAGE_SERVICE_INTERFACE_IMPL = "service.Impl";

    //controllerPackage
    public  static String PACKAGE_CONTROLLER = "controller";
    public List<String> TABLE_NAME;
    //    项目名
    public  String PREJECT_NAME;

    public String MODULE_NAME;
    //
    public static String GROUP_NAME="com.nev";
    private SwaggerInfo swaggerInfo;
   private MysqlInfo mysqlInfo;
   public String getMysqlUrl(){
       return "jdbc:mysql://"+mysqlInfo.getSqlHost()+":"+mysqlInfo.getSqlPort()+"/"+mysqlInfo.getDbName();
   }
    public String getPACKAGE_MAPPING() {
        return BuildConfigInfo.GROUP_NAME+"."+this.MODULE_NAME+"."+PACKAGE_MAPPING;
    }

    public String getPACKAGE_MODEL() {
        return BuildConfigInfo.GROUP_NAME+"."+this.MODULE_NAME+"."+PACKAGE_MODEL;
    }

    public  String getPACKAGEENTITY() {
        return BuildConfigInfo.GROUP_NAME+"."+this.MODULE_NAME+"."+PACKAGE_ENTITY;
    }

    public String getPACKAGE_MAPPER() {
        return BuildConfigInfo.GROUP_NAME+"."+this.MODULE_NAME+ "."+PACKAGE_MAPPER;
    }

    public String getPACKAGE_SERVICE_INTERFACE() {
        return BuildConfigInfo.GROUP_NAME+"."+this.MODULE_NAME+"."+PACKAGE_SERVICE_INTERFACE;
    }

    public String getPACKAGE_SERVICE_INTERFACE_IMPL() {
        return BuildConfigInfo.GROUP_NAME+"."+this.MODULE_NAME+"."+PACKAGE_SERVICE_INTERFACE_IMPL;
    }

    public String getPACKAGE_CONTROLLER() {
        return BuildConfigInfo.GROUP_NAME+"."+this.MODULE_NAME+"."+PACKAGE_CONTROLLER;
    }

    public String getMysqlDriver() {
       return mysqlInfo.getDriver();

   }



}
