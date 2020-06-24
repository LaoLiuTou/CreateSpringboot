package utils.writeFiles.test;

import utils.ProjectBean;
import utils.writeFiles.UpLowUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class WriteTest {
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加App
     *
     * @return
     */
    public static String writeTest(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        String lowerName= UpLowUtil.toUpperCaseFirstOne(pb.getProjectName().toLowerCase()) ;
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/test/java/com/"+pb.getProjectName().toLowerCase()
                    +"/"+lowerName+"ApplicationTests.java";
            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/test/java/com/"+pb.getProjectName().toLowerCase()
                    +"/";
            createTestFiles(dirs,url);



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
    public static  void createTestFiles(String dirs,String url) throws IOException{

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

        sb.append("import org.junit.Test;\n");
        sb.append("import org.junit.runner.RunWith;\n");
        sb.append("import org.springframework.boot.test.context.SpringBootTest;\n");
        sb.append("import org.springframework.test.context.junit4.SpringRunner;\n\n");
        sb.append("@RunWith(SpringRunner.class)\n");
        sb.append("@SpringBootTest\n");
        sb.append("public class "+lowerName+"ApplicationTests {\n\n");
        sb.append("    @Test\n");
        sb.append("    public void contextLoads() {\n");
        sb.append("    }\n\n");
        sb.append("}\n");

        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+lowerName+"ApplicationTests.java 成功！") ;
        out.close();
    }



}
