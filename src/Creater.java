import jdbc.JdbcBean;
import utils.CopyFiles;
import utils.ProjectBean;
import utils.writeFiles.application.WriteApp;
import utils.writeFiles.application.WriteAppConfig;
import utils.writeFiles.controller.WriteController;
import utils.writeFiles.druid.WriteDruid;
import utils.writeFiles.entity.WriteBean;
import utils.writeFiles.iml.WriteIml;
import utils.writeFiles.interceptor.WriteInterceptor;
import utils.writeFiles.logger.WriteLogger;
import utils.writeFiles.mapper.WriteMapper;
import utils.writeFiles.pom.WritePom;
import utils.writeFiles.service.WriteService;
import utils.writeFiles.service.WriteServiceImpl;
import utils.writeFiles.test.WriteTest;
import utils.writeFiles.token.WriteToken;
import utils.writeFiles.xml.WriteXml;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Creater {



    static String dbType="mysql";
    static int size = getTables().size();
    static List tableList = getTables();
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        Logger log = Logger.getLogger("Creater");
        //copyFiles
        copyFiles();
        //controller
        writeController();
        //Mabatis table xml
        xml();

        //Java  Bean
        bean();

        //Java Dao
        dao();

        //Java service
        service();

        //Java serviceImpl
        serviceImpl();

        //Druid
        WriteDruid.writeDruid();

        //token
        WriteToken.writeToken();

        //Application
        WriteApp.writeApp();

        //WriteAppConfig
        WriteInterceptor.writeInterceptor();

        //jdbc
        WriteAppConfig.writeAppConfig();

        //Logger
        WriteLogger.writeLogger();

        //iml
        WriteIml.writeIml();

        //Pom
        WritePom.writePom();

        //test
        WriteTest.writeTest();

        log.info("Over!!!");
    }
    /**
     * 复制文件
     * @return
     * @throws IOException
     */
    public static void copyFiles() throws IOException{
        //copy files
        ProjectBean pb=new ProjectBean();
        CopyFiles cf=new CopyFiles();
        cf.copyDir(System.getProperty("user.dir")+"/staticFiles",pb.getProjectUrl()+"/"+pb.getProjectName());
    }

    /**
     * 写controller
     * @return
     */
    public static void writeController() throws IOException  {
        WriteController wc = new WriteController();
        for(int i=0;i<size;i++){
            String tableName=tableList.get(i).toString();
            String pKey=getPKey(tableName);
            int columnSize = getColumn(tableName).size();
            Map columnMap = getColumn(tableName);
            wc.writeController(tableName, pKey);
            wc.addAddFunction(tableName, pKey);
            wc.addmulAddFunction(tableName, pKey);
            wc.addDeleteFunction(tableName, pKey);
            wc.addSelectFunction(tableName, pKey);
            wc.addUpdateFunction(tableName, pKey);
            wc.addListFunctionStart(tableName, pKey);
            for(int j=0;j<columnSize;j++){
                List l=(List) columnMap.get(j+1);
                wc.addListFunctionParam(tableName, (String)l.get(0), (String)l.get(2));
            }
            wc.addListFunctionEnd(tableName, pKey);


        }
    }


    /**
     * 写Ibatis  xml
     * @return
     * @throws IOException
     */
    public static void xml() throws IOException  {
        //Ibatis table xml
        WriteXml wx=new WriteXml();
        for(int i=0;i<size;i++){
            String tableName=tableList.get(i).toString();
            String pKey=getPKey(tableName);
            wx.writeXml(tableName,pKey,dbType) ;
            int columnSize = getColumn(tableName).size();
            Map columnMap = getColumn(tableName);
            //循环列
            for(int j=0;j<columnSize;j++){
                List l=(List) columnMap.get(j+1);
                wx.appendSelectFiles(tableName, (String)l.get(0), (String)l.get(2),dbType) ;
            }
            wx.addSelectEnd(tableName, pKey,dbType);
            //循环列
            for(int j=0;j<columnSize;j++){
                List l=(List) columnMap.get(j+1);
                wx.appendSelectFiles(tableName, (String)l.get(0), (String)l.get(2),dbType) ;
            }
            wx.addCountEnd(tableName, pKey);
            //循环列
            for(int j=0;j<columnSize;j++){
                List l=(List) columnMap.get(j+1);
                //if(!pKey.equals((String)l.get(0)))
                wx.appendUpdateFiles(tableName, (String)l.get(0)) ;
            }
            wx.addUpdateEnd(tableName, pKey);
            //循环列
            for(int j=0;j<columnSize;j++){
                List l=(List) columnMap.get(j+1);
                if(!((String)l.get(0)).toUpperCase().equals(pKey.toUpperCase()))
                    wx.appendInsertFiles1(tableName, (String)l.get(0));
            }
            wx.appendInsertFiles2(tableName,pKey);
            //循环列
            for(int j=0;j<columnSize;j++){
                List l=(List) columnMap.get(j+1);
                if(!((String)l.get(0)).toUpperCase().equals(pKey.toUpperCase()))
                    wx.appendInsertFiles3(tableName, (String)l.get(0));
            }
            //wx.addInsertEnd(tableName,pKey,dbType);

            wx.addmulStart(tableName, pKey);
            //循环列
            for(int j=0;j<columnSize;j++){
                List l=(List) columnMap.get(j+1);
                //if(!((String)l.get(0)).toUpperCase().equals(pKey.toUpperCase()))
                wx.addmulInsertFiles1(tableName, (String)l.get(0));
            }
            wx.addmulInsertFiles2(tableName,pKey);
            //循环列
            for(int j=0;j<columnSize;j++){
                List l=(List) columnMap.get(j+1);
                //if(!((String)l.get(0)).toUpperCase().equals(pKey.toUpperCase()))
                wx.addmulInsertFiles3(tableName, (String)l.get(0));
            }
            wx.addmulInsertEnd(tableName,pKey,dbType);
        }

    }

    /**
     * 写JavaBean  xml
     * @return
     * @throws IOException
     */
    public static void bean() throws IOException  {
        //Java  Bean
        WriteBean wb = new WriteBean();
        //循环table
        for(int i=0;i<size;i++){
            String tableName=tableList.get(i).toString();
            wb.writeBean(tableName) ;
            int columnSize = getColumn(tableName).size();
            Map columnMap = getColumn(tableName);
            //循环列
            for(int j=0;j<columnSize;j++){
                List l=(List) columnMap.get(j+1);
                wb.appendFiles(tableName, (String)l.get(0), (String)l.get(1), (String)l.get(2));
            }
            wb.addEnd(tableName);
        }
    }

    /**
     * 写java dao
     * @return
     */
    public static void dao()  {
        WriteMapper wd = new WriteMapper();
        for(int i=0;i<size;i++){
            String tableName=tableList.get(i).toString();
            String pKey=getPKey(tableName);
            wd.writeDao(pKey,tableName);
        }
    }

    /**
     * 写java service
     * @return
     */
    public static void service()  {
        WriteService wd = new WriteService();
        for(int i=0;i<size;i++){
            String tableName=tableList.get(i).toString();
            String pKey=getPKey(tableName);
            wd.writeService(pKey,tableName);
        }
    }

    /**
     * 写java service Impl
     * @return
     */
    public static void serviceImpl()  {
        WriteServiceImpl wdi = new WriteServiceImpl();
        for(int i=0;i<size;i++){
            String tableName=tableList.get(i).toString();
            String pKey=getPKey(tableName);
            wdi.writeServiceImpl(pKey,tableName);
        }
    }



    /////////////////////////////////////////////////////////
    /**
     * 选取tables
     *
     * @return
     */
    public static List getTables(){
        List tableList = new ArrayList();
        JdbcBean jb=new JdbcBean();

        Connection con = null; //表示数据库的连接对象
        Statement stmt = null;  //表示数据库的更新操作
        ResultSet result = null; //表示接收数据库的查询结果
        try {
            Class.forName(jb.getDbdriver());
            con = DriverManager.getConnection(jb.getDburl(),jb.getDbuser(),jb.getDbpassword()); //2、连接数据库

            stmt = con.createStatement(); //3、Statement 接口需要通过Connection 接口进行实例化操作
            result = stmt.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = '"
                    +jb.getDburl().split("/")[jb.getDburl().split("/").length-1]+"'"); //执行SQL 语句，查询数据库

            while (result.next()){

                String table_name=result.getString("table_name");
                tableList.add(table_name);


            }
            result.close();
            con.close(); // 4、关闭数据库
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        //tableList.add("user");
        //tableList.add("rl_lov");
        //tableList.add("sickness");

        return tableList;

    }
    /**
     * 选取tables的列
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map getColumn(String tableName){
        Map colMap = new HashMap();
        JdbcBean jb=new JdbcBean();

        Connection con = null;
        Statement stmt = null;
        ResultSet result = null;
        try {
            Class.forName(jb.getDbdriver());
            con = DriverManager.getConnection(jb.getDburl(),jb.getDbuser(),jb.getDbpassword());
            stmt = con.createStatement();
		        /*result = stmt.executeQuery("SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
		        		+tableName+"' and table_schema = '"+"jb_af_10005"+"'");*/

            result = stmt.executeQuery("SELECT COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = '"
                    +tableName+"' AND TABLE_SCHEMA ='"+jb.getDburl().split("/")[jb.getDburl().split("/").length-1]+"'");
            while (result.next()){

                String data_type=result.getString("DATA_TYPE");
                String column_name=result.getString("COLUMN_NAME");
                String comments=result.getString("COLUMN_COMMENT");

                List colList = new ArrayList();
                colList.add(column_name);
                colList.add(comments);
                colList.add(data_type);

                colMap.put(result.getRow(), colList);


            }
            result.close();
            con.close(); //关闭数据库
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return colMap;

    }

    /**
     * 选取tables的主键
     *
     * @return
     */
    public static String getPKey(String tableName){
        String column_name = "";
        JdbcBean jb=new JdbcBean();

        Connection con = null;
        Statement stmt = null;
        ResultSet result = null;
        try {
            Class.forName(jb.getDbdriver());
            con = DriverManager.getConnection(jb.getDburl(),jb.getDbuser(),jb.getDbpassword());
            stmt = con.createStatement();
            result = stmt.executeQuery("select COLUMN_KEY,COLUMN_NAME from INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='"
                    +tableName+"' AND COLUMN_KEY='PRI'  AND TABLE_SCHEMA ='"+jb.getDburl().split("/")[jb.getDburl().split("/").length-1]+"'");

            while (result.next()){

                column_name=result.getString("COLUMN_NAME");

            }
            result.close();
            con.close(); //关闭数据库
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return column_name;

    }


}
