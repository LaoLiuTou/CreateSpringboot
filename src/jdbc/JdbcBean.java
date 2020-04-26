package jdbc;

import java.io.FileInputStream;
import java.util.Properties;
 

public class JdbcBean {
      

     
	/**
	 * @return the dbdriver
	 */
	public String getDbdriver() {
		return getProperties("dbdriver");
	}
	
	/**
	 * @return the dburl
	 */
	public String getDburl() {
		return getProperties("dburl") ;
	}
	
	/**
	 * @return the dbuser
	 */
	public String getDbuser() {
		return getProperties("dbuser") ;
	}
	
	/**
	 * @return the dbpassword
	 */
	public String getDbpassword() {
		return getProperties("dbpassword") ;
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
