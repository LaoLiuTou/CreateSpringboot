package utils.writeFiles.logger;

import jdbc.JdbcBean;
import utils.ProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;

public class WriteLogger {
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加App
     *
     * @return
     */
    public static String writeLogger(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/log4j2-spring.xml";
            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/";
            createLoggerFiles(dirs,url);



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
    public static  void createLoggerFiles(String dirs,String url) throws IOException{

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

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<Configuration status=\"ERROR\" monitorInterval=\"600\">\n");
        sb.append("    <!-- configure.status 为设置日志输出级别，级别如下：OFF 、FATAL 、ERROR、WARN、INFO、DEBUG、TRACE、ALL -->\n");
        sb.append("    <!-- configure.monitorInterval 监控间隔 指log4j2每隔600秒（10分钟），自动监控该配置文件是否有变化，如果变化，则自动根据文件内容重新配置 -->\n\n");
        sb.append("    <Properties>\n");
        sb.append("        <property name=\"pattern\">%d{yyyy/MM/dd HH:mm:ss.SSS} (%F:%L) [%p] %t %c: %m%n</property>\n");
        sb.append("        <property name=\"basePath\">logs</property>\n");
        sb.append("        <property name=\"projectName\">"+pb.getProjectName()+"</property>\n");
        sb.append("    </Properties>\n\n");
        sb.append("    <!--配置appenders源：日志输出的地址-->\n");
        sb.append("    <Appenders>\n");
        sb.append("        <Console name=\"console\" target=\"SYSTEM_OUT\">\n");
        sb.append("            <PatternLayout pattern=\"${pattern}\"/>\n");
        sb.append("        </Console>\n");
        sb.append("        <RollingRandomAccessFile name=\"fileLogger\"\n");
        sb.append("        fileName=\"${basePath}/${projectName}/${projectName}.log\"\n");
        sb.append("        filePattern=\"${basePath}/${projectName}/${projectName}-%d{yyyy-MM-dd}.log\"\n");
        sb.append("        append=\"true\">\n");
        sb.append("            <PatternLayout pattern=\"${pattern}\"/>\n");
        sb.append("            <Policies>\n");
        sb.append("                <TimeBasedTriggeringPolicy interval=\"1\" modulate=\"true\"/>\n");
        sb.append("                <SizeBasedTriggeringPolicy size=\"10MB\"/>\n");
        sb.append("            </Policies>\n");
        sb.append("        </RollingRandomAccessFile>\n");
        sb.append("    </Appenders>\n");

        sb.append("    <!--配置logers：级别、使用的输出地-->\n");
        sb.append("    <Loggers>\n");
        sb.append("        <Logger name=\"com."+pb.getProjectName().toLowerCase()+".mapper\" level=\"debug\" additivity=\"true\">\n");
        sb.append("        </Logger>\n");
        sb.append("        <Root level=\"info\" additivity=\"false\">\n");
        sb.append("            <appender-ref ref=\"console\"/>\n");
        sb.append("            <appender-ref ref=\"fileLogger\"/>\n");
        sb.append("        </Root>\n");
        sb.append("    </Loggers>\n");
        sb.append("</Configuration>\n");

        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+"application.properties 成功！") ;
        out.close();
    }



}

