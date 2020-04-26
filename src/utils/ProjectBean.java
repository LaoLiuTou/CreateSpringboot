package utils;

import jdbc.JdbcBean;

import java.io.FileInputStream;
import java.util.Properties;


public class ProjectBean {
    
	/**
	 * @return the dbdriver
	 */
	public String getProjectName() {
		return getProperties("projectName");
	}
	
	/**
	 * @return the dburl
	 */
	public String getProjectUrl() {
		return getProperties("projectUrl") ;
	}
	
	/**
	 * @param str
	 */
	private String getProperties(String str) {
		// TODO Auto-generated method stub
	    
		// //////读取配置文件
		Properties properties = new Properties();
		String base = JdbcBean.class.getResource("/")
				.getPath();
		try {
			properties.load(new FileInputStream(base
					+ "jdbc/jdbc.properties"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String propertiesStr = properties.getProperty(str);
		
		return propertiesStr;
	} 
}
