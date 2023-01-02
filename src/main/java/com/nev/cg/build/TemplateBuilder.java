package com.nev.cg.build;

import com.nev.cg.model.BuildConfigInfo;
import com.nev.cg.util.JavaTypes;
import com.nev.cg.util.ModelInfo;
import com.nev.cg.util.StringUtils;
import lombok.Cleanup;

import java.sql.*;
import java.util.*;

public class TemplateBuilder {



    private  BuildConfigInfo buildConfigInfo;



    /***
     * 模板构建
     */
    public static void builder(BuildConfigInfo buildConfigInfo) {

        try {
//            Class<Driver> driver = (Class<Driver>) Class.forName(buildConfigInfo.getMysqlDriver());

//            DriverManager.deregisterDriver(driver);
            System.out.println(buildConfigInfo.getMysqlUrl());
            //获取数据库连接

           @Cleanup Connection conn = DriverManager.getConnection(buildConfigInfo.getMysqlUrl(),buildConfigInfo.getMysqlInfo().getSqlUname(),buildConfigInfo.getMysqlInfo().getPwd());
            DatabaseMetaData metaData = conn.getMetaData();

            //获取数据库类型：MySQL
            String databaseType = metaData.getDatabaseProductName();

            //针对MySQL数据库进行相关生成操作
            if (databaseType.equals("MySQL")) {
                //获取所有表结构

                //获取数据库名字
                String database = conn.getCatalog();

                //Swagger信息集合

                //循环所有表信息
                    ResultSet tableResultSet = metaData.getTables(null, "%", "%", new String[]{"TABLE"});



                while (tableResultSet.next()) {
                    //获取表名

                    String tableName = tableResultSet.getString("TABLE_NAME");

                    //名字操作,去掉tab_,tb_，去掉_并转驼峰
                    String table = StringUtils.replace_(StringUtils.replaceTab(tableName));
                    //大写对象
                    String Table = StringUtils.firstUpper(table);
                    System.out.println(Table);
                    //需要生成的Pojo属性集合
                    List<ModelInfo> models = new ArrayList<ModelInfo>();
                    //所有需要导包的类型
                    Set<String> typeSet = new HashSet<String>();

                    //获取表所有的列
                    ResultSet cloumnsSet = metaData.getColumns(database, buildConfigInfo.getMysqlInfo().getSqlUname(), tableName, null);
                    //获取主键
                    ResultSet keySet = metaData.getPrimaryKeys(database, buildConfigInfo.getMysqlInfo().getSqlUname(), tableName);
                    String key = "", keyType = "";
                    while (keySet.next()) {
                        key = keySet.getString(4);
                    }

                    //构建SwaggerModel对象
                    //属性集合

                    while (cloumnsSet.next()) {
                        //列的描述
                        String remarks = cloumnsSet.getString("REMARKS");
                        //获取列名
                        String columnName = cloumnsSet.getString("COLUMN_NAME");
                        //处理列名
                        String propertyName = StringUtils.replace_(columnName);
                        //获取类型，并转成JavaType
                        String javaType = JavaTypes.getType(cloumnsSet.getInt("DATA_TYPE"));
                        //创建该列的信息
                        models.add(new ModelInfo(javaType, JavaTypes.simpleName(javaType), propertyName, StringUtils.firstUpper(propertyName), remarks, key.equals(columnName), columnName, cloumnsSet.getString("IS_AUTOINCREMENT")));
                        //需要导包的类型
                        typeSet.add(javaType);
                        //主键类型
                        if (columnName.equals(key)) {
                            keyType = JavaTypes.simpleName(javaType);
                        }

                        //SwaggerModelProperties创建
                        Map<String, Object> modelMap = new HashMap<String, Object>();

                        //创建该表的JavaBean
                        modelMap.put("table", table);
                        modelMap.put("Table", Table);
                        modelMap.put("TableName", tableName);
                        modelMap.put("models", models);
                        modelMap.put("typeSet", typeSet);
                        modelMap.put("swagger",true);

                        //主键操作
                        modelMap.put("keySetMethod", "set" + StringUtils.firstUpper(StringUtils.replace_(key)));
                        modelMap.put("keyType", keyType);

                        //创建JavaBean
                        ModelBuilder.builder(modelMap,buildConfigInfo);

                        //创建Controller
                        ControllerBuilder.builder(modelMap,buildConfigInfo);

                        //创建Dao
                        DaoBuilder.builder(modelMap,buildConfigInfo);

                        //创建Service接口
                        ServiceBuilder.builder(modelMap,buildConfigInfo);

                        //创建ServiceImpl实现类
                        ServiceImplBuilder.builder(modelMap,buildConfigInfo);
                        MapperBuilder.builder(modelMap,buildConfigInfo);
                        EntityBuilder.builder(modelMap,buildConfigInfo);
                        //创建Feign

                        //添加swagger路径映射
                        String format = "string";
                        if (keyType.equalsIgnoreCase("integer") || keyType.equalsIgnoreCase("long")) {
                            format = "int64";
                        }
                    }


                }


            }

//            PomBuilder.builder(new HashMap<String, Object>(),buildConfigInfo);
            ApplicationBuilder.builder(new HashMap<String, Object>(),buildConfigInfo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }



    /***
     * 模板构建
     */
    public static void builderBYTableNames(BuildConfigInfo buildConfigInfo) {

        try {
//            Class<Driver> driver = (Class<Driver>) Class.forName(buildConfigInfo.getMysqlDriver());


//            DriverManager.deregisterDriver(driver);
            System.out.println(buildConfigInfo.getMysqlUrl());
            //获取数据库连接

            @Cleanup Connection conn = DriverManager.getConnection(buildConfigInfo.getMysqlUrl(),buildConfigInfo.getMysqlInfo().getSqlUname(),buildConfigInfo.getMysqlInfo().getPwd());
            DatabaseMetaData metaData = conn.getMetaData();

            //获取数据库类型：MySQL
            String databaseType = metaData.getDatabaseProductName();

            //针对MySQL数据库进行相关生成操作
            if (databaseType.equals("MySQL")) {
                //获取所有表结构

                //获取数据库名字
                String database = conn.getCatalog();

                //Swagger信息集合

                //循环所有表信息
                List<String> tableNames = buildConfigInfo.getTABLE_NAME();

                for (String tables : tableNames) {
                    System.out.println(tables);
                    ResultSet  tableResultSet = metaData.getTables(null, "%", tables, new String[]{"TABLE"});
                    //获取表名
                    while (tableResultSet.next()) {

                        //获取表名

                        String tableName = tableResultSet.getString("TABLE_NAME");

                        //名字操作,去掉tab_,tb_，去掉_并转驼峰
                        String table = StringUtils.replace_(StringUtils.replaceTab(tableName));
                        //大写对象
                        String Table = StringUtils.firstUpper(table);

                        //需要生成的Pojo属性集合
                        List<ModelInfo> models = new ArrayList<ModelInfo>();
                        //所有需要导包的类型
                        Set<String> typeSet = new HashSet<String>();

                        //获取表所有的列
                        ResultSet cloumnsSet = metaData.getColumns(database, buildConfigInfo.getMysqlInfo().getSqlUname(), tableName, null);
                        //获取主键
                        ResultSet keySet = metaData.getPrimaryKeys(database, buildConfigInfo.getMysqlInfo().getSqlUname(), tableName);
                        String key = "", keyType = "";
                        while (keySet.next()) {
                            key = keySet.getString(4);
                        }

                        //构建SwaggerModel对象
                        //属性集合

                        while (cloumnsSet.next()) {
                            //列的描述
                            String remarks = cloumnsSet.getString("REMARKS");
                            //获取列名
                            String columnName = cloumnsSet.getString("COLUMN_NAME");
                            //处理列名
                            String propertyName = StringUtils.replace_(columnName);
                            //获取类型，并转成JavaType
                            String javaType = JavaTypes.getType(cloumnsSet.getInt("DATA_TYPE"));
                            //创建该列的信息
                            models.add(new ModelInfo(javaType, JavaTypes.simpleName(javaType), propertyName, StringUtils.firstUpper(propertyName), remarks, key.equals(columnName), columnName, cloumnsSet.getString("IS_AUTOINCREMENT")));
                            //需要导包的类型
                            typeSet.add(javaType);
                            //主键类型
                            if (columnName.equals(key)) {
                                keyType = JavaTypes.simpleName(javaType);
                            }

                            //SwaggerModelProperties创建
                            Map<String, Object> modelMap = new HashMap<String, Object>();

                            //创建该表的JavaBean
                            modelMap.put("table", table);
                            modelMap.put("Table", Table);
                            modelMap.put("TableName", tableName);
                            modelMap.put("models", models);
                            modelMap.put("typeSet", typeSet);
                            modelMap.put("swagger",true);

                            //主键操作
                            modelMap.put("keySetMethod", "set" + StringUtils.firstUpper(StringUtils.replace_(key)));
                            modelMap.put("keyType", keyType);

                            //创建JavaBean
                            ModelBuilder.builder(modelMap,buildConfigInfo);

                            //创建Controller
                            ControllerBuilder.builder(modelMap,buildConfigInfo);

                            //创建Dao
                            DaoBuilder.builder(modelMap,buildConfigInfo);

                            //创建Service接口
                            ServiceBuilder.builder(modelMap,buildConfigInfo);

                            //创建ServiceImpl实现类
                            ServiceImplBuilder.builder(modelMap,buildConfigInfo);
                            MapperBuilder.builder(modelMap,buildConfigInfo);
                            EntityBuilder.builder(modelMap,buildConfigInfo);
                            //创建Feign

                            //添加swagger路径映射
                            String format = "string";
                            if (keyType.equalsIgnoreCase("integer") || keyType.equalsIgnoreCase("long")) {
                                format = "int64";
                            }
                        }

                    }

                }

            }
            PomBuilder.builder(new HashMap<String, Object>(),buildConfigInfo);
            ApplicationBuilder.builder(new HashMap<String, Object>(),buildConfigInfo);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
