package utils.writeFiles.application;

import jdbc.JdbcBean;
import utils.ProjectBean;
import utils.writeFiles.UpLowUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class WriteAppConfig {
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加App
     *
     * @return
     */
    public static String writeAppConfig(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/"+"application.properties";
            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/";
            createAppConfigFiles(dirs,url);



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
    public static  void createAppConfigFiles(String dirs,String url) throws IOException{

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

        sb.append("spring.application.name="+pb.getProjectName().toLowerCase()+"\n");
        sb.append("server.port=9999\n\n");

        sb.append("mybatis.type-aliases-package=com."+pb.getProjectName().toLowerCase()+".entity\n");
        sb.append("mybatis.config-location=classpath:mybatis/mybatis-config.xml\n");
        sb.append("mybatis.mapper-locations=classpath:mybatis/mapper/*/*.xml\n\n");

        sb.append("spring.datasource.type=com.alibaba.druid.pool.DruidDataSource\n");
        sb.append("spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver\n");
        sb.append("spring.datasource.url="+jb.getDburl()+"?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC\n");
        sb.append("spring.datasource.username="+jb.getDbuser()+"\n");
        sb.append("spring.datasource.password="+jb.getDbpassword()+"\n");

        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+"application.properties 成功！") ;
        out.close();
    }


}
