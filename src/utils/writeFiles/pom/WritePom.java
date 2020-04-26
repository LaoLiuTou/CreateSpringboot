package utils.writeFiles.pom;

import jdbc.JdbcBean;
import utils.ProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class WritePom {
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加App
     *
     * @return
     */
    public static String writePom(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/pom.xml";

            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/";
            createPomFiles(dirs,url);



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
    public static  void createPomFiles(String dirs,String url) throws IOException{

        ProjectBean pb=new ProjectBean();
        JdbcBean jb=new JdbcBean();
        File file=new File(url);
        File dir=new File(dirs);
        if(!file.exists()){
            dir.mkdirs();
            file.createNewFile();
        }
        //FileOutputStream out=new FileOutputStream(file,true);
        FileOutputStream out=new FileOutputStream(file);
        StringBuffer sb=new StringBuffer();
        String lowerName= toUpperCaseFirstOne(pb.getProjectName().toLowerCase()) ;
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
        sb.append("         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd\">\n");
        sb.append("    <modelVersion>4.0.0</modelVersion>\n");
        sb.append("    <parent>\n");
        sb.append("        <groupId>org.springframework.boot</groupId>\n");
        sb.append("        <artifactId>spring-boot-starter-parent</artifactId>\n");
        sb.append("        <version>2.1.7.RELEASE</version>\n");
        sb.append("        <relativePath/>\n");
        sb.append("    </parent>\n");
        sb.append("    <groupId>com."+lowerName+"</groupId>\n");
        sb.append("    <artifactId>"+pb.getProjectName()+"</artifactId>\n");
        sb.append("    <version>0.0.1-SNAPSHOT</version>\n");
        sb.append("    <name>"+pb.getProjectName()+"</name>\n");
        sb.append("    <description>auto create "+pb.getProjectName()+" project for Spring Boot</description>\n\n");
        sb.append("    <properties>\n");
        sb.append("        <java.version>1.8</java.version>\n");
        sb.append("    </properties>\n");
        sb.append("    <!--打包为war-->\n");
        sb.append("    <!--<packaging>war</packaging>-->\n");
        sb.append("    <dependencies>\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>org.springframework.boot</groupId>\n");
        sb.append("            <artifactId>spring-boot-starter-web</artifactId>\n");
        sb.append("            <!--打包为war-->\n");
        sb.append("            <!--<exclusions>\n");
        sb.append("             <exclusion>\n");
        sb.append("                 <groupId>org.springframework.boot</groupId>\n");
        sb.append("                 <artifactId>spring-boot-starter-tomcat</artifactId>\n");
        sb.append("             </exclusion>\n");
        sb.append("            </exclusions>-->\n");
        sb.append("        </dependency>\n\n");
        sb.append("        <!--打包为war-->\n");
        sb.append("        <!--<dependency>\n");
        sb.append("            <groupId>javax.servlet</groupId>\n");
        sb.append("            <artifactId>javax.servlet-api</artifactId>\n");
        sb.append("            <version>3.1.0</version>\n");
        sb.append("            <scope>provided</scope>\n");
        sb.append("        </dependency>-->\n\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>org.mybatis.spring.boot</groupId>\n");
        sb.append("            <artifactId>mybatis-spring-boot-starter</artifactId>\n");
        sb.append("            <version>2.1.0</version>\n");
        sb.append("        </dependency>\n\n\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>mysql</groupId>\n");
        sb.append("            <artifactId>mysql-connector-java</artifactId>\n");
        sb.append("            <scope>runtime</scope>\n");
        sb.append("        </dependency>\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>org.springframework.boot</groupId>\n");
        sb.append("            <artifactId>spring-boot-starter-test</artifactId>\n");
        sb.append("            <scope>test</scope>\n");
        sb.append("        </dependency>\n\n");
        sb.append("        <!-- Druid 数据连接池依赖 -->\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>com.alibaba</groupId>\n");
        sb.append("            <artifactId>druid-spring-boot-starter</artifactId>\n");
        sb.append("            <version>1.1.10</version>\n");
        sb.append("        </dependency>\n\n");
        sb.append("        <!-- 分页插件 -->\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>com.github.pagehelper</groupId>\n");
        sb.append("            <artifactId>pagehelper-spring-boot-starter</artifactId>\n");
        sb.append("            <version>1.2.5</version>\n");
        sb.append("        </dependency>\n\n");
        sb.append("        <!-- 导入log4j2依赖 -->\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>org.springframework.boot</groupId>\n");
        sb.append("            <artifactId>spring-boot-starter</artifactId>\n");
        sb.append("            <exclusions>\n");
        sb.append("             <exclusion>\n");
        sb.append("                 <groupId>org.springframework.boot</groupId>\n");
        sb.append("                 <artifactId>spring-boot-starter-logging</artifactId>\n");
        sb.append("             </exclusion>\n");
        sb.append("            </exclusions>\n");
        sb.append("        </dependency>\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>org.springframework.boot</groupId>\n");
        sb.append("            <artifactId>spring-boot-starter-log4j2</artifactId>\n");
        sb.append("        </dependency>\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>log4j</groupId>\n");
        sb.append("            <artifactId>log4j</artifactId>\n");
        sb.append("            <version>1.2.17</version>\n");
        sb.append("        </dependency>\n\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>com.auth0</groupId>\n");
        sb.append("            <artifactId>java-jwt</artifactId>\n");
        sb.append("            <version>3.4.1</version>\n");
        sb.append("        </dependency>\n\n\n");
        sb.append("        <!-- properties-->\n");
        sb.append("        <dependency>\n");
        sb.append("            <groupId>org.springframework.boot</groupId>\n");
        sb.append("            <artifactId>spring-boot-configuration-processor</artifactId>\n");
        sb.append("            <optional>true</optional>\n");
        sb.append("        </dependency>\n\n\n");
        sb.append("    </dependencies>\n\n\n");
        sb.append("    <build>\n");
        sb.append("        <plugins>\n");
        sb.append("            <plugin>\n");
        sb.append("                 <groupId>org.springframework.boot</groupId>\n");
        sb.append("                 <artifactId>spring-boot-maven-plugin</artifactId>\n");
        sb.append("            </plugin>\n");
        sb.append("        </plugins>\n");
        sb.append("    </build>\n\n");
        sb.append("</project>\n");



        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+"pom.xml 成功！") ;
        out.close();
    }




    //首字母转小写
    public static String toLowerCaseFirstOne(String s)
    {
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    //首字母转大写
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

}

