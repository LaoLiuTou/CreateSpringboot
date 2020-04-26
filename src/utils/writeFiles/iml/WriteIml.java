package utils.writeFiles.iml;

import jdbc.JdbcBean;
import utils.ProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class WriteIml {
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加App
     *
     * @return
     */
    public static String writeIml(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+pb.getProjectName()+".iml";
            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/";
            createImlFiles(dirs,url);



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
    public static  void createImlFiles(String dirs,String url) throws IOException{

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


        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<module org.jetbrains.idea.maven.project.MavenProjectsManager.isMavenModule=\"true\" type=\"JAVA_MODULE\" version=\"4\">\n");
        sb.append("  <component name=\"FacetManager\">\n");
        sb.append("    <facet type=\"Spring\" name=\"Spring\">\n");
        sb.append("      <configuration />\n");
        sb.append("    </facet>\n");
        sb.append("    <facet type=\"web\" name=\"Web\">\n");
        sb.append("      <configuration>\n");
        sb.append("        <descriptors>\n");
        sb.append("          <deploymentDescriptor name=\"web.xml\" url=\"file://$MODULE_DIR$/src/main/webapp/WEB-INF/web.xml\" />\n");
        sb.append("        </descriptors>\n");
        sb.append("        <webroots>\n");
        sb.append("          <root url=\"file://$MODULE_DIR$/src/main/webapp\" relative=\"/\" />\n");
        sb.append("        </webroots>\n");
        sb.append("        <sourceRoots>\n");
        sb.append("          <root url=\"file://$MODULE_DIR$/src/main/java\" />\n");
        sb.append("          <root url=\"file://$MODULE_DIR$/src/main/resources\" />\n");
        sb.append("        </sourceRoots>\n");
        sb.append("      </configuration>\n");
        sb.append("    </facet>\n");
        sb.append("    <facet type=\"web\" name=\"Web2\">\n");
        sb.append("      <configuration>\n");
        sb.append("        <descriptors>\n");
        sb.append("          <deploymentDescriptor name=\"web.xml\" url=\"file://$MODULE_DIR$/src/main/webapp/WEB-INF/web.xml\" />\n");
        sb.append("        </descriptors>\n");
        sb.append("        <webroots>\n");
        sb.append("          <root url=\"file://$MODULE_DIR$/src/main/webapp\" relative=\"/\" />\n");
        sb.append("        </webroots>\n");
        sb.append("        <sourceRoots>\n");
        sb.append("          <root url=\"file://$MODULE_DIR$/src/main/java\" />\n");
        sb.append("          <root url=\"file://$MODULE_DIR$/src/main/resources\" />\n");
        sb.append("        </sourceRoots>\n");
        sb.append("      </configuration>\n");
        sb.append("    </facet>\n");
        sb.append("  </component>\n");
        sb.append("  <component name=\"NewModuleRootManager\" LANGUAGE_LEVEL=\"JDK_1_8\">\n");
        sb.append("    <output url=\"file://$MODULE_DIR$/target/classes\" />\n");
        sb.append("    <output-test url=\"file://$MODULE_DIR$/target/test-classes\" />\n");
        sb.append("    <content url=\"file://$MODULE_DIR$\">\n");
        sb.append("      <sourceFolder url=\"file://$MODULE_DIR$/src/main/java\" isTestSource=\"false\" />\n");
        sb.append("      <sourceFolder url=\"file://$MODULE_DIR$/src/main/resources\" type=\"java-resource\" />\n");
        sb.append("      <sourceFolder url=\"file://$MODULE_DIR$/src/test/java\" isTestSource=\"true\" />\n");
        sb.append("      <excludeFolder url=\"file://$MODULE_DIR$/target\" />\n");
        sb.append("    </content>\n");
        sb.append("    <orderEntry type=\"inheritedJdk\" />\n");
        sb.append("    <orderEntry type=\"sourceFolder\" forTests=\"false\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework.boot:spring-boot-starter-web:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework.boot:spring-boot-starter-json:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.9.9\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.9.9\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.fasterxml.jackson.module:jackson-module-parameter-names:2.9.9\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework.boot:spring-boot-starter-tomcat:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.apache.tomcat.embed:tomcat-embed-core:9.0.22\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.apache.tomcat.embed:tomcat-embed-el:9.0.22\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.apache.tomcat.embed:tomcat-embed-websocket:9.0.22\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.hibernate.validator:hibernate-validator:6.0.17.Final\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: javax.validation:validation-api:2.0.1.Final\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.jboss.logging:jboss-logging:3.3.2.Final\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.fasterxml:classmate:1.4.0\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-web:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-beans:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-webmvc:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-aop:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-context:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-expression:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.0\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework.boot:spring-boot-starter-jdbc:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.zaxxer:HikariCP:3.2.0\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-jdbc:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-tx:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.mybatis.spring.boot:mybatis-spring-boot-autoconfigure:2.1.0\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.mybatis:mybatis:3.5.2\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.mybatis:mybatis-spring:2.0.2\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"RUNTIME\" name=\"Maven: mysql:mysql-connector-java:8.0.17\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.springframework.boot:spring-boot-starter-test:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.springframework.boot:spring-boot-test:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.springframework.boot:spring-boot-test-autoconfigure:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: com.jayway.jsonpath:json-path:2.4.0\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: net.minidev:json-smart:2.3\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: net.minidev:accessors-smart:1.2\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.ow2.asm:asm:5.0.4\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: junit:junit:4.12\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.assertj:assertj-core:3.11.1\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.mockito:mockito-core:2.23.4\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: net.bytebuddy:byte-buddy:1.9.16\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: net.bytebuddy:byte-buddy-agent:1.9.16\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.objenesis:objenesis:2.6\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.hamcrest:hamcrest-core:1.3\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.hamcrest:hamcrest-library:1.3\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.skyscreamer:jsonassert:1.5.0\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: com.vaadin.external.google:android-json:0.0.20131108.vaadin1\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-core:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework:spring-jcl:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.springframework:spring-test:5.1.9.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: org.xmlunit:xmlunit-core:2.6.3\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: javax.xml.bind:jaxb-api:2.3.1\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"TEST\" name=\"Maven: javax.activation:javax.activation-api:1.2.0\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.alibaba:druid-spring-boot-starter:1.1.10\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.alibaba:druid:1.1.10\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.slf4j:slf4j-api:1.7.26\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework.boot:spring-boot-autoconfigure:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.github.pagehelper:pagehelper-spring-boot-starter:1.2.5\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.github.pagehelper:pagehelper-spring-boot-autoconfigure:1.2.5\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.github.pagehelper:pagehelper:5.1.4\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.github.jsqlparser:jsqlparser:1.0\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework.boot:spring-boot-starter:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework.boot:spring-boot:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: javax.annotation:javax.annotation-api:1.3.2\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" scope=\"RUNTIME\" name=\"Maven: org.yaml:snakeyaml:1.23\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework.boot:spring-boot-starter-log4j2:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.apache.logging.log4j:log4j-slf4j-impl:2.11.2\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.apache.logging.log4j:log4j-api:2.11.2\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.apache.logging.log4j:log4j-core:2.11.2\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.apache.logging.log4j:log4j-jul:2.11.2\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.slf4j:jul-to-slf4j:1.7.26\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: log4j:log4j:1.2.17\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.auth0:java-jwt:3.4.1\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.fasterxml.jackson.core:jackson-databind:2.9.9\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.fasterxml.jackson.core:jackson-annotations:2.9.0\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: com.fasterxml.jackson.core:jackson-core:2.9.9\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: commons-codec:commons-codec:1.11\" level=\"project\" />\n");
        sb.append("    <orderEntry type=\"library\" name=\"Maven: org.springframework.boot:spring-boot-configuration-processor:2.1.7.RELEASE\" level=\"project\" />\n");
        sb.append("  </component>\n");
        sb.append("</module>\n");

        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+pb.getProjectName()+".iml 成功！") ;
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

