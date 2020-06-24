package utils.writeFiles.interceptor;

import utils.ProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class WriteInterceptor {
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加Bean
     *
     * @return
     */
    public static String writeInterceptor(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                    +"/"+"InterceptorConfig.java";
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
        sb.append("package com."+pb.getProjectName().toLowerCase()+";\n\n");

        sb.append("import com."+pb.getProjectName().toLowerCase()+".token.AuthenticationInterceptor;\n");
        sb.append("import org.springframework.context.annotation.Bean;\n");
        sb.append("import org.springframework.context.annotation.Configuration;\n");
        sb.append("import org.springframework.web.cors.CorsConfiguration;\n");
        sb.append("import org.springframework.web.cors.UrlBasedCorsConfigurationSource;\n");
        sb.append("import org.springframework.web.filter.CorsFilter;\n");
        sb.append("import org.springframework.web.servlet.config.annotation.InterceptorRegistry;\n");
        sb.append("import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;\n");
        sb.append("import java.util.ArrayList;\n");
        sb.append("import java.util.List;\n\n");
        sb.append("/**\n");
        sb.append(" * @author LT\n");
        sb.append(" */\n");
        sb.append("@Configuration\n");
        sb.append("public class InterceptorConfig implements WebMvcConfigurer {\n");
        sb.append("    //支持跨域请求\n");
        sb.append("    /*@Override\n");
        sb.append("    public void addCorsMappings(CorsRegistry registry) {\n");
        sb.append("        registry.addMapping(\"/**\")\n");
        sb.append("                .allowedOrigins(\"*\")\n");
        sb.append("                .allowCredentials(true)\n");
        sb.append("                .allowedHeaders(\"*\")\n");
        sb.append("                .allowedMethods(\"GET\", \"POST\", \"DELETE\", \"PUT\",\"PATCH\")\n");
        sb.append("                .maxAge(3600);\n");
        sb.append("    }*/\n\n");


        sb.append("    private CorsConfiguration buildConfig() {\n");
        sb.append("        CorsConfiguration corsConfiguration = new CorsConfiguration();\n");
        sb.append("        List<String> list = new ArrayList<>();\n");
        sb.append("        list.add(\"*\");\n");
        sb.append("        corsConfiguration.setAllowedOrigins(list);\n");
        sb.append("        corsConfiguration.addAllowedOrigin(\"*\");\n");
        sb.append("        corsConfiguration.addAllowedHeader(\"*\");\n");
        sb.append("        corsConfiguration.addAllowedMethod(\"*\");\n");
        sb.append("        return corsConfiguration;\n");
        sb.append("    }\n\n");
        sb.append("    @Bean\n");
        sb.append("    public CorsFilter corsFilter() {\n");
        sb.append("        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();\n");
        sb.append("        source.registerCorsConfiguration(\"/**\", buildConfig());\n");
        sb.append("        return new CorsFilter(source);\n");
        sb.append("    }\n\n");

        sb.append("    //注册拦截器\n");
        sb.append("    @Override\n");
        sb.append("    public void addInterceptors(InterceptorRegistry registry) {\n");
        sb.append("        registry.addInterceptor(authenticationInterceptor())\n");
        sb.append("                .addPathPatterns(\"/**\")\n");
        sb.append("                .excludePathPatterns(\"/js/**\",\"/login/**\",\"/login\",\"/index.html\");\n");
        sb.append("    }\n");
        sb.append("    @Bean\n");
        sb.append("    public AuthenticationInterceptor authenticationInterceptor() {\n");
        sb.append("        return new AuthenticationInterceptor();\n");
        sb.append("    }\n");
        sb.append("}\n");

        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+"InterceptorConfig.java成功！") ;
        out.close();
    }

}