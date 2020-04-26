package utils.writeFiles.druid;

import utils.ProjectBean;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

public class WriteDruid {
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加druid
     *
     * @return
     */
    public static String writeDruid(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                +"/druid/"+"DruidConfig.java";
            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                +"/druid/";
            createDruidConfigFiles(dirs,url);

            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                    +"/druid/"+"DruidSource.java";
            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                    +"/druid/";
            createDruidSourceFiles(dirs,url);

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
    public static  void createDruidConfigFiles(String dirs,String url) throws IOException{
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

        sb.append("package com."+pb.getProjectName().toLowerCase()+".druid;\n\n");

        sb.append("import com.alibaba.druid.support.http.StatViewServlet;\n");
        sb.append("import com.alibaba.druid.support.http.WebStatFilter;\n");
        sb.append("import org.springframework.boot.web.servlet.FilterRegistrationBean;\n");
        sb.append("import org.springframework.boot.web.servlet.ServletRegistrationBean;\n");
        sb.append("import org.springframework.context.annotation.Bean;\n");
        sb.append("import org.springframework.context.annotation.Configuration;\n\n");
        sb.append("/**\n");
        sb.append(" * @author LT\n");
        sb.append(" */\n");
        sb.append("@Configuration\n");
        sb.append("public class DruidConfig {\n");
        sb.append(" @Bean\n");
        sb.append(" public ServletRegistrationBean<StatViewServlet> registrationBean() {\n");
        sb.append("     ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet());        //添加初始化参数：initParams\n");
        sb.append("     servletRegistrationBean.addUrlMappings(\"/druid/*\");\n");
        sb.append("     //白名单：\n");
        sb.append("     servletRegistrationBean.addInitParameter(\"allow\", \"192.168.1.27\");\n");
        sb.append("     //IP黑名单 (存在共同时，deny优先于allow) : 如果满足deny的话提示:Sorry, you are not permitted to view this page.\n");
        sb.append("     servletRegistrationBean.addInitParameter(\"deny\", \"127.0.0.1\");\n");
        sb.append("     //登录查看信息的账号密码.\n");
        sb.append("     servletRegistrationBean.addInitParameter(\"loginUsername\", \"admin\");\n");
        sb.append("     servletRegistrationBean.addInitParameter(\"loginPassword\", \"123456\");\n");
        sb.append("     //是否能够重置数据.\n");
        sb.append("     servletRegistrationBean.addInitParameter(\"resetEnable\", \"false\");\n");
        sb.append("     return servletRegistrationBean;\n");
        sb.append(" }\n\n");
        sb.append(" @Bean\n");
        sb.append(" public FilterRegistrationBean<WebStatFilter> filterRegistrationBean() {\n");
        sb.append("     FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());\n");
        sb.append("     //添加过滤规则.\n");
        sb.append("     filterRegistrationBean.addUrlPatterns(\"/*\");\n");
        sb.append("     //添加不需要忽略的格式信息.\n");
        sb.append("     filterRegistrationBean.addInitParameter(\"exclusions\", \"*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*\");\n");
        sb.append("     return filterRegistrationBean;\n");
        sb.append(" }\n");
        sb.append("}\n\n");



        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+"DruidConfig.java 成功！") ;
        out.close();
    }

    /**
     * 添加
     *
     * @return
     */
    public static  void createDruidSourceFiles(String dirs,String url) throws IOException{
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

        sb.append("package com."+pb.getProjectName().toLowerCase()+".druid;\n\n");

        sb.append("import com.alibaba.druid.pool.DruidDataSource;\n");
        sb.append("import org.springframework.boot.context.properties.ConfigurationProperties;\n");
        sb.append("import org.springframework.context.annotation.Configuration;\n");
        sb.append("import org.springframework.context.annotation.Primary;\n");
        sb.append("import org.springframework.context.annotation.PropertySource;\n");
        sb.append("import javax.sql.DataSource;\n");
        sb.append("import java.sql.SQLException;\n\n");
        sb.append("/**\n");
        sb.append(" * @author LT\n");
        sb.append(" */\n");
        sb.append("@Configuration\n");
        sb.append("@PropertySource(value = {\"classpath:druid.properties\"})\n");
        sb.append("@ConfigurationProperties(prefix = \"spring.druid\")\n");
        sb.append("public class DruidSource {\n");
        sb.append("    private String dbUrl;\n");
        sb.append("    private String username;\n");
        sb.append("    private String password;\n");
        sb.append("    private String driverClassName;\n");
        sb.append("    private int initialSize;\n");
        sb.append("    private int minIdle;\n");
        sb.append("    private int maxActive;\n");
        sb.append("    private int maxWait;\n");
        sb.append("    private int timeBetweenEvictionRunsMillis;\n");
        sb.append("    private int minEvictableIdleTimeMillis;\n");
        sb.append("    private String validationQuery;\n");
        sb.append("    private boolean testWhileIdle;\n");
        sb.append("    private boolean testOnBorrow;\n");
        sb.append("    private boolean testOnReturn;\n");
        sb.append("    private boolean poolPreparedStatements;\n");
        sb.append("    private int maxPoolPreparedStatementPerConnectionSize;\n");
        sb.append("    private String filters;\n");
        sb.append("    private String connectionProperties;\n\n");
        sb.append("    public String getDbUrl() {\n");
        sb.append("        return dbUrl;\n");
        sb.append("    }\n");
        sb.append("    public void setDbUrl(String dbUrl) {\n");
        sb.append("        this.dbUrl = dbUrl;\n");
        sb.append("    }\n");
        sb.append("    public String getUsername() {\n");
        sb.append("        return username;\n");
        sb.append("    }\n");
        sb.append("    public void setUsername(String username) {\n");
        sb.append("        this.username = username;\n");
        sb.append("    }\n");
        sb.append("    public String getPassword() {\n");
        sb.append("        return password;\n");
        sb.append("    }\n");
        sb.append("    public void setPassword(String password) {\n");
        sb.append("        this.password = password;\n");
        sb.append("    }\n");
        sb.append("    public String getDriverClassName() {\n");
        sb.append("        return driverClassName;\n");
        sb.append("    }\n");
        sb.append("    public void setDriverClassName(String driverClassName) {\n");
        sb.append("        this.driverClassName = driverClassName;\n");
        sb.append("    }\n");
        sb.append("    public int getInitialSize() {\n");
        sb.append("        return initialSize;\n");
        sb.append("    }\n");
        sb.append("    public void setInitialSize(int initialSize) {\n");
        sb.append("        this.initialSize = initialSize;\n");
        sb.append("    }\n");
        sb.append("    public int getMinIdle() {\n");
        sb.append("        return minIdle;\n");
        sb.append("    }\n");
        sb.append("    public void setMinIdle(int minIdle) {\n");
        sb.append("        this.minIdle = minIdle;\n");
        sb.append("    }\n");
        sb.append("    public int getMaxActive() {\n");
        sb.append("        return maxActive;\n");
        sb.append("    }\n");
        sb.append("    public void setMaxActive(int maxActive) {\n");
        sb.append("        this.maxActive = maxActive;\n");
        sb.append("    }\n");
        sb.append("    public int getMaxWait() {\n");
        sb.append("        return maxWait;\n");
        sb.append("    }\n");
        sb.append("    public void setMaxWait(int maxWait) {\n");
        sb.append("        this.maxWait = maxWait;\n");
        sb.append("    }\n");
        sb.append("    public int getTimeBetweenEvictionRunsMillis() {\n");
        sb.append("        return timeBetweenEvictionRunsMillis;\n");
        sb.append("    }\n");
        sb.append("    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {\n");
        sb.append("        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;\n");
        sb.append("    }\n");
        sb.append("    public int getMinEvictableIdleTimeMillis() {\n");
        sb.append("        return minEvictableIdleTimeMillis;\n");
        sb.append("    }\n");
        sb.append("    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {\n");
        sb.append("        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;\n");
        sb.append("    }\n");
        sb.append("    public String getValidationQuery() {\n");
        sb.append("        return validationQuery;\n");
        sb.append("    }\n");
        sb.append("    public void setValidationQuery(String validationQuery) {\n");
        sb.append("        this.validationQuery = validationQuery;\n");
        sb.append("    }\n");
        sb.append("    public boolean isTestWhileIdle() {\n");
        sb.append("        return testWhileIdle;\n");
        sb.append("    }\n");
        sb.append("    public void setTestWhileIdle(boolean testWhileIdle) {\n");
        sb.append("        this.testWhileIdle = testWhileIdle;\n");
        sb.append("    }\n");
        sb.append("    public boolean isTestOnBorrow() {\n");
        sb.append("        return testOnBorrow;\n");
        sb.append("    }\n");
        sb.append("    public void setTestOnBorrow(boolean testOnBorrow) {\n");
        sb.append("        this.testOnBorrow = testOnBorrow;\n");
        sb.append("    }\n");
        sb.append("    public boolean isTestOnReturn() {\n");
        sb.append("        return testOnReturn;\n");
        sb.append("    }\n");
        sb.append("    public void setTestOnReturn(boolean testOnReturn) {\n");
        sb.append("        this.testOnReturn = testOnReturn;\n");
        sb.append("    }\n");
        sb.append("    public boolean isPoolPreparedStatements() {\n");
        sb.append("        return poolPreparedStatements;\n");
        sb.append("    }\n");
        sb.append("    public void setPoolPreparedStatements(boolean poolPreparedStatements) {\n");
        sb.append("        this.poolPreparedStatements = poolPreparedStatements;\n");
        sb.append("    }\n");
        sb.append("    public int getMaxPoolPreparedStatementPerConnectionSize() {\n");
        sb.append("        return maxPoolPreparedStatementPerConnectionSize;\n");
        sb.append("    }\n");
        sb.append("    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {\n");
        sb.append("        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;\n");
        sb.append("    }\n");
        sb.append("    public String getFilters() {\n");
        sb.append("        return filters;\n");
        sb.append("    }\n");
        sb.append("    public void setFilters(String filters) {\n");
        sb.append("        this.filters = filters;\n");
        sb.append("    }\n");
        sb.append("    public String getConnectionProperties() {\n");
        sb.append("        return connectionProperties;\n");
        sb.append("    }\n");
        sb.append("    public void setConnectionProperties(String connectionProperties) {\n");
        sb.append("        this.connectionProperties = connectionProperties;\n");
        sb.append("    }\n");
        sb.append("    //@Bean     //声明其为Bean实例\n");
        sb.append("    @Primary  //在同样的DataSource中，首先使用被标注的DataSource\n");
        sb.append("    public DataSource dataSource() throws SQLException {\n");
        sb.append("        DruidDataSource datasource = new DruidDataSource();\n");
        sb.append("        datasource.setUrl(this.dbUrl);\n");
        sb.append("        datasource.setUsername(username);\n");
        sb.append("        datasource.setPassword(password);\n");
        sb.append("        datasource.setDriverClassName(driverClassName);\n");
        sb.append("        datasource.setInitialSize(initialSize);\n");
        sb.append("        datasource.setMinIdle(minIdle);\n");
        sb.append("        datasource.setMaxActive(maxActive);\n");
        sb.append("        datasource.setMaxWait(maxWait);\n");
        sb.append("        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);\n");
        sb.append("        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);\n");
        sb.append("        datasource.setValidationQuery(validationQuery);\n");
        sb.append("        datasource.setTestWhileIdle(testWhileIdle);\n");
        sb.append("        datasource.setTestOnBorrow(testOnBorrow);\n");
        sb.append("        datasource.setTestOnReturn(testOnReturn);\n");
        sb.append("        datasource.setPoolPreparedStatements(poolPreparedStatements);\n");
        sb.append("        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);\n");
        sb.append("        datasource.setFilters(filters);\n");
        sb.append("        return datasource;\n");
        sb.append("    }\n");
        sb.append("}\n");

        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+"DruidSource.java 成功！") ;
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
