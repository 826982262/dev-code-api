package com.nev.cg.build;

import com.nev.cg.model.BuildConfigInfo;
import freemarker.template.Template;

import java.io.File;
import java.util.Map;

/****
 * @Author:shenkunlin
 * @Description:构建对象的工厂
 * @Date 2019/6/14 23:21
 *****/
public class BuilderFactory {


    /***
     * 构建Controller
     * @param modelMap
     */
    public static void builder(Map<String,Object> modelMap,//数据模型
                               String templatePath, //模板路径
                               String templateFile, //模板文件
                               String storePath,    //存储路径
                               String suffix, BuildConfigInfo buildConfigInfo){      //生成文件后缀名字
        try {
            //获取模板对象
            Template template = TemplateUtil.loadTemplate(ControllerBuilder.class.getResource(templatePath).getPath(), templateFile);

            //创建文件夹
            String path = storePath.replace(".","/");
            File file = new File("code/src/main/java/"+path);
            if(!file.exists()){
                file.mkdirs();
            }

            //创建文件
            System.out.println("创建文件"+"code/src/main/java/"+path+"/"+modelMap.get("Table")+suffix);
            TemplateUtil.writer(template,modelMap,"code/src/main/java/"+path+"/"+modelMap.get("Table")+suffix,buildConfigInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void pomBuilder(Map<String,Object> modelMap,//数据模型
                               String templatePath, //模板路径
                               String templateFile, //模板文件
                               String storePath,    //存储路径
                               String suffix, BuildConfigInfo buildConfigInfo){      //生成文件后缀名字
        try {
            //获取模板对象
            Template template = TemplateUtil.loadTemplate(ControllerBuilder.class.getResource(templatePath).getPath(), templateFile);

            //创建文件夹
//            String path = storePath.replace(".","/");
            File file = new File("code");
            if(!file.exists()){
                file.mkdirs();
            }

            //创建文件
            TemplateUtil.pomWriter(template,modelMap,"code/pom.xml",buildConfigInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void ResourcesBuilder(Map<String,Object> modelMap,//数据模型
                               String templatePath, //模板路径
                               String templateFile, //模板文件
                               String storePath,    //存储路径
                               String suffix, BuildConfigInfo buildConfigInfo){      //生成文件后缀名字
        try {
            //获取模板对象
            Template template = TemplateUtil.loadTemplate(ControllerBuilder.class.getResource(templatePath).getPath(), templateFile);

            //创建文件夹
            String path = storePath.replace(".","/");
            File file = new File("code/src/main/resources"+path);
            if(!file.exists()){
                file.mkdirs();
            }

            //创建文件
            if (templateFile.equals("application.yml")){
                TemplateUtil.writer(template,modelMap,"code/src/main/resources/"+templateFile,buildConfigInfo);
            }else {
                TemplateUtil.writer(template,modelMap,"code/src/main/resources"+path+"/"+modelMap.get("Table")+suffix,buildConfigInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
