package utils.writeFiles.xml;

import utils.ProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;


public class WriteXml {

	Logger log = Logger.getLogger("Creater");

	/**
	 * 创建文件
	 * 
	 * @return
	 */
	
	public String writeXml(String tableName,String pKey,String dbType){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/";
		try {
			createFiles(dirs,url,pKey,tableName,dbType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			status="failure";
			e.printStackTrace();
		}
		 
		return status;
	}
	
	/**
	 * 创建
	 * 
	 * @return
	 */
	public  void createFiles(String dirs,String url,String pKey,String tableName,String dbType) throws IOException{
		 
		ProjectBean pb=new ProjectBean();
        File file=new File(url);
        File dir=new File(dirs);
        if(!file.exists()){
        	dir.mkdirs();
            file.createNewFile();
        }
        String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
            //FileOutputStream out=new FileOutputStream(file,true);        
            FileOutputStream out=new FileOutputStream(file);        
            StringBuffer sb=new StringBuffer();
             
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
            sb.append("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"\n");             
            sb.append("\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n");
            sb.append("<mapper namespace=\"com."+pb.getProjectName().toLowerCase()+".mapper."+tableName.toLowerCase()+".I"+lowerName+"Mapper\"> \n");
            if(!pKey.equals("")&&pKey!=null){
            	sb.append("	<select id=\"select"+tableName+"By"+toUpperCaseFirstOne(pKey.toLowerCase())+"\" parameterType=\"String\" resultType=\""+lowerName+"\">\n");
            	sb.append("		SELECT * FROM "+tableName+" WHERE "+pKey.toUpperCase()+"=#{"+pKey.toLowerCase()+"}\n");
            	sb.append("	</select>\n");
            	sb.append("\n");
            	sb.append("	<delete id=\"delete"+tableName+"\" parameterType=\"String\"> \n");
            	sb.append("		DELETE FROM "+tableName+" WHERE "+pKey.toUpperCase()+"=#{"+pKey.toLowerCase()+"}\n");
            	sb.append("	</delete>\n");
            }
            sb.append("\n");
            
    		
            sb.append("	<select id=\"select"+tableName+"ByParam\" parameterType=\"java.util.Map\" resultType=\""+lowerName+"\">\n"); 
            
            if(dbType.equals("oracle")){
            	sb.append("		SELECT *\n"); 
            	sb.append("		FROM ( SELECT A.*, ROWNUM RN \n"); 
                sb.append("		FROM (SELECT * FROM "+tableName+"\n"); 
        	}
        	else if(dbType.equals("sqlserver")){
                sb.append("		SELECT * FROM( \n"); 
                sb.append("		SELECT TOP (#toPage#-#fromPage#) * FROM \n"); 
                sb.append("		(SELECT TOP (#toPage#) * FROM "+tableName+" \n"); 
        	}
        	else if(dbType.equals("mysql")){
        		sb.append("		SELECT *\n"); 
                sb.append("		FROM "+tableName+"\n");  
        	}
            
            
            
            sb.append("		<trim prefix=\"WHERE\" prefixOverrides=\"AND|OR\">\n"); 
            out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;     
        out.close();
    }
 
	/**
	 * 动态添加selcet中间内容
	 * 
	 * @return
	 */
	public  void appendSelectFiles(String tableName,String colName,String type,String dbType) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        /*if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
            sb.append("			<if test=\""+colName.toLowerCase()+"From != null\"> "+colName.toUpperCase()+" &gt;=#{"+colName.toLowerCase()+"From} </if>\n");
            sb.append("			<if test=\""+colName.toLowerCase()+"To != null\"> "+colName.toUpperCase()+" &lt;=#{"+colName.toLowerCase()+"To} </if>\n");
        }*/
         
        if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
        	
        	if(dbType.equals("oracle")){
        		sb.append("			<if test=\""+colName.toLowerCase()+"From != null\"> AND "+colName.toUpperCase()+" &gt;=to_date('#{"+colName.toLowerCase()+"From}','yyyy-mm-dd hh24:mi:ss') </if>\n");
                sb.append("			<if test=\""+colName.toLowerCase()+"To != null\"> AND "+colName.toUpperCase()+" &lt;=to_date('#{"+colName.toLowerCase()+"To}','yyyy-mm-dd hh24:mi:ss') </if>\n");
        	}
        	else if(dbType.equals("sqlserver")){
        		sb.append("			<if test=\""+colName.toLowerCase()+"From != null\"> AND "+colName.toUpperCase()+" &gt;=#{"+colName.toLowerCase()+"From} </if>\n");
                sb.append("			<if test=\""+colName.toLowerCase()+"To != null\"> AND "+colName.toUpperCase()+" &lt;=#{"+colName.toLowerCase()+"To} </if>\n");
        	}
        	else if(dbType.equals("mysql")){
        		sb.append("			<if test=\""+colName.toLowerCase()+"From != null\"> AND unix_timestamp("+colName.toUpperCase()+") &gt;=unix_timestamp(#{"+colName.toLowerCase()+"From}) </if>\n");
                sb.append("			<if test=\""+colName.toLowerCase()+"To != null\"> AND unix_timestamp("+colName.toUpperCase()+") &lt;=unix_timestamp(#{"+colName.toLowerCase()+"To}) </if>\n");
        	}
        	 
        }
        sb.append("			<if test=\""+colName.toLowerCase()+" != null\"> AND "+colName.toUpperCase()+" =#{"+colName.toLowerCase()+"} </if>\n"); 
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	
	/**
	 * 添加select尾，count 头
	 * 
	 * @return
	 */
	public  void addSelectEnd(String tableName,String pKey,String dbType) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        	sb.append("		</trim>  \n"); 
        /*if(!pKey.equals("")&&pKey!=null){
        	//sb.append("		ORDER BY "+pKey.toUpperCase()+" DESC \n");
        	sb.append("			<if test=\"orderBy!= null\"> ORDER BY   #{orderBy} </if>\n");
        }
        if(dbType.equals("oracle")){
        	sb.append("		) A WHERE ROWNUM &lt;= #{toPage} )   WHERE RN &gt;#{fromPage} \n");
    	}
    	else if(dbType.equals("sqlserver")){
    		sb.append("		) TEMP1 ORDER BY TEMP1."+pKey.toUpperCase()+" ASC )  TEMP2 \n");
        	sb.append("		ORDER BY TEMP2.SMSINDEX DESC \n");
    	}
    	else if(dbType.equals("mysql")){
    		sb.append("		LIMIT #{fromPage},#{toPage} \n");
    	}*/
        
    	
    	
    	sb.append("	</select>\n\n");
        sb.append("	<select id=\"selectCount"+tableName+"ByParam\" parameterType=\"java.util.Map\" resultType=\"int\">\n"); 
        sb.append("		SELECT COUNT("+pKey+") \n"); 
        sb.append("		FROM "+tableName+" \n");  
        sb.append("		<trim prefix=\"WHERE\" prefixOverrides=\"AND|OR\">\n"); 
        
        out.write(sb.toString().getBytes("utf-8"));
             
        out.close();
    }
	
 
	
	/**
	 * 添加 count 尾  update  头
	 * 
	 * @return
	 */
	public  void addCountEnd(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("		</trim>  \n"); 
        sb.append("	</select>\n\n");
        sb.append("	<update id=\"update"+tableName+"\" parameterType=\""+lowerName+"\">\n");     
        sb.append("		UPDATE "+tableName+" \n");  
        sb.append("		<trim prefix=\"SET\" suffixOverrides=\",\">\n");
        
        out.write(sb.toString().getBytes("utf-8"));
             
        out.close();
    }
	
	/**
	 * 动态添加update中间内容
	 * 
	 * @return
	 */
	public  void appendUpdateFiles(String tableName,String colName) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
        sb.append("			<if test=\""+colName.toLowerCase()+" != null\">"  +colName.toUpperCase()+"=#{"+colName.toLowerCase()+"}, </if>\n");
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	
	/**
	 * 添加update尾，insert 头
	 * 
	 * @return
	 */
	public  void addUpdateEnd(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
        sb.append("		</trim>\n");  
        if(!pKey.equals("")&&pKey!=null){
        	sb.append("		WHERE "+pKey.toUpperCase()+"=#{"+pKey.toLowerCase()+"}\n"); 
        }
        sb.append("	</update>\n\n");
       
        sb.append("	<insert id=\"add"+tableName+"\" parameterType=\""+lowerName+"\" keyProperty=\"id\" useGeneratedKeys=\"true\">\n"); 
        sb.append("		INSERT INTO "+tableName+" \n");  
        
        sb.append("		<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >\n"); 
        
        out.write(sb.toString().getBytes("utf-8"));
             
        out.close();
    }
	
	/**
	 * 动态添加insert中间内容
	 * 
	 * @return
	 */
	public  void appendInsertFiles1(String tableName,String colName) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("			<if test=\""+colName.toLowerCase()+" != null\">"  +colName.toUpperCase()+", </if>\n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	/**
	 * 动态添加insert中间内容
	 * 
	 * @return
	 */
	public  void appendInsertFiles2(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("		</trim>\n");  
        sb.append("		<trim prefix=\"VALUES (\" suffix=\")\" suffixOverrides=\",\" >\n"); 
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	/**
	 * 动态添加insert中间内容
	 * 
	 * @return
	 */
	public  void appendInsertFiles3(String tableName,String colName) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
        sb.append("			<if test=\""+colName.toLowerCase()+" != null\">"  +"#{"+colName.toLowerCase()+"}, </if>\n");
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	
	/**
	 * 添加update 尾
	 * 
	 * @return
	 */
	public  void addInsertEnd(String tableName,String pKey,String dbType) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("		</trim>\n");  
        if(!pKey.equals("")&&pKey!=null){
        	if(dbType.equals("oracle")){
        		sb.append("		<selectKey order=\"BEFORE\" resultType=\"long\" keyProperty=\""+pKey.toLowerCase()+"\" > \n");  
        	}
        	else{
        		sb.append("		<selectKey order=\"AFTER\" resultType=\"long\" keyProperty=\""+pKey.toLowerCase()+"\" > \n");  
        	}
        	if(dbType.equals("oracle")){
        		sb.append("			SELECT SEQ_"+tableName.toUpperCase()+".CURRVAL AS "+pKey+" FROM DUAL\n"); 
        	}
        	else if(dbType.equals("sqlserver")){
        		sb.append("			SELECT @@identity\n");  
        	}
        	else if(dbType.equals("mysql")){
        		sb.append("			SELECT LAST_INSERT_ID()\n");  
        	}
	        sb.append("		</selectKey> \n"); 
        }
        sb.append("	</insert>\n");
        
    }
	/**
	 * 添加尾，mulinsert 头
	 * 
	 * @return
	 */
	public  void addmulStart(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("		</trim>\n");   
        sb.append("	</insert>\n\n");
        sb.append("	<insert id=\"muladd"+tableName+"\" parameterType=\"java.util.List\" keyProperty=\"id\" useGeneratedKeys=\"true\">\n"); 
        sb.append("		<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"\"\n"); 
        sb.append("			close=\"\" separator=\";\">\n"); 
        sb.append("			INSERT INTO "+tableName+" \n"); 
        sb.append("			<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\" >\n"); 
			
        out.write(sb.toString().getBytes("utf-8"));
             
        out.close();
    }
	
	/**
	 * 动态添加mulinsert中间内容
	 * 
	 * @return
	 */
	public  void addmulInsertFiles1(String tableName,String colName) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("				<if test=\"item."+colName.toLowerCase()+" != null\">"  +colName.toUpperCase()+", </if>\n");
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	/**
	 * 动态添加mulinsert中间内容
	 * 
	 * @return
	 */
	public  void addmulInsertFiles2(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("			</trim>\n");  
        sb.append("			<trim prefix=\"VALUES (\" suffix=\")\" suffixOverrides=\",\" >\n"); 
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	/**
	 * 动态添加mulinsert中间内容
	 * 
	 * @return
	 */
	public  void addmulInsertFiles3(String tableName,String colName) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
        File file=new File(url);
         
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        
        sb.append("				<if test=\"item."+colName.toLowerCase()+" != null\">"  +"#{item."+colName.toLowerCase()+"}, </if>\n");
        
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;       
        out.close();
    }
	
	/**
	 * 添加mulinsert 尾
	 * 
	 * @return
	 */
	public  void addmulInsertEnd(String tableName,String pKey,String dbType) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/resources/mybatis/mapper/"+tableName.toLowerCase()+"/"+lowerName+".xml";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("			</trim>\n");   
        sb.append("		</foreach>\n");   
        sb.append("	</insert>\n");
        sb.append("</mapper>\n"); 
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;      
        log.info("创建文件"+tableName+".xml 成功！") ;       
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
