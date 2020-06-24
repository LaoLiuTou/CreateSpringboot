package utils.writeFiles.application;

import utils.ProjectBean;
import utils.writeFiles.UpLowUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class WriteApp {
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加App
     *
     * @return
     */
    public static String writeApp(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        String lowerName= UpLowUtil.toUpperCaseFirstOne(pb.getProjectName().toLowerCase()) ;
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                    +"/"+lowerName+"Application.java";
            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                    +"/";
            createAppFiles(dirs,url);



        } catch (IOException e) {
            // TODO Auto-generated catch block
            status="failure";
            e.printStackTrace();
        }

        return status;
    }
    /**
     * 添加
     *
     * @return
     */
    public static  void createAppFiles(String dirs,String url) throws IOException{

        ProjectBean pb=new ProjectBean();
        File file=new File(url);
        File dir=new File(dirs);
        if(!file.exists()){
            dir.mkdirs();
            file.createNewFile();
        }
        //FileOutputStream out=new FileOutputStream(file,true);
        FileOutputStream out=new FileOutputStream(file);
        StringBuffer sb=new StringBuffer();
        String lowerName= UpLowUtil.toUpperCaseFirstOne(pb.getProjectName().toLowerCase()) ;
        sb.append("package com."+pb.getProjectName().toLowerCase()+";\n\n");
        sb.append("import org.springframework.boot.ApplicationArguments;\n");
        sb.append("import org.springframework.boot.ApplicationRunner;\n");
        sb.append("import org.springframework.boot.SpringApplication;\n");
        sb.append("import org.springframework.boot.autoconfigure.SpringBootApplication;\n");
        sb.append("import org.springframework.boot.builder.SpringApplicationBuilder;\n");
        sb.append("import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;\n\n");
        sb.append("/**\n");
        sb.append(" * @author LT\n");
        sb.append(" */\n");
        sb.append("//jar\n");
        sb.append("@SpringBootApplication\n");
        sb.append("public class "+lowerName+"Application  implements ApplicationRunner {\n");
        sb.append("    public static void main(String[] args) {\n");
        sb.append("        SpringApplication.run("+lowerName+"Application.class, args);\n");
        sb.append("    }\n");
        sb.append("    @Override\n");
        sb.append("    public void run(ApplicationArguments args) throws Exception {\n");
        sb.append("        System.out.println(\"->->->->->->->->\");\n");
        sb.append("        System.out.println(\"->->->->->->->->->\");\n");
        sb.append("        System.out.println(\"->->->->->->->->->->\");\n");
        sb.append("        System.out.println(\"->->->->->->->->->->->\");\n");
        sb.append("        System.out.println(\"->->->->->->->->->->->->\");\n");
        sb.append("        System.out.println(\"->->->->->->->->->->->->->\");\n");
        sb.append("        System.out.println(\"->->->->->->->->->->->->->->\");\n");
        sb.append("        System.out.println(\"->->->->->->->->->->->->->->->项目启动成功！\");\n");
        sb.append("    }\n");
        sb.append("}\n\n");
        sb.append("//war\n");
        sb.append("/*@SpringBootApplication\n");
        sb.append("public class "+lowerName+"Application extends SpringBootServletInitializer {\n");
        sb.append("    @Override\n");
        sb.append("    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){\n");
        sb.append("        return springApplicationBuilder.sources("+lowerName+"Application.class);\n");
        sb.append("    }\n");
        sb.append("    public static void main(String[] args) {\n");
        sb.append("        SpringApplication.run("+lowerName+"Application.class, args);\n");
        sb.append("    }\n");
        sb.append("}*/\n");

        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+lowerName+"Application.java 成功！") ;
        out.close();
    }


}