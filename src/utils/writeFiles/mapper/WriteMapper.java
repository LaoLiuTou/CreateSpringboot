package utils.writeFiles.mapper;

import utils.ProjectBean;
import utils.writeFiles.UpLowUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class WriteMapper {


    Logger log = Logger.getLogger("Creater");

	/**
	 * 添加Dao
	 * 
	 * @return
	 */
	public String writeDao(String pKey,String tableName){
		String lowerName= UpLowUtil.toUpperCaseFirstOne(tableName.toLowerCase()) ;
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
			+"/mapper/"+tableName.toLowerCase()+"/I"+lowerName+"Mapper.java";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
			+"/mapper/"+tableName.toLowerCase()+"/";
		try {
			createFiles(dirs,url,pKey,tableName);
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
	public  void createFiles(String dirs,String url,String pKey,String tableName) throws IOException{
		String lowerName= UpLowUtil.toUpperCaseFirstOne(tableName.toLowerCase()) ;
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".mapper."+tableName.toLowerCase()+";\n\n");
            sb.append("import java.util.List;\n");
            sb.append("import java.util.Map;\n");
            sb.append("import org.apache.ibatis.annotations.Mapper;\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".entity."+tableName.toLowerCase()+"."+lowerName+";\n\n");
            sb.append("/**\n");
            sb.append(" * @author LT\n");
            sb.append(" */\n");
            sb.append("@Mapper\n");
            sb.append("public interface I"+lowerName+"Mapper {\n");
            sb.append("	/**\n");
            sb.append(" 	* 通过id选取\n");
            sb.append(" 	* @return\n");
            sb.append("    */\n");
            sb.append("	"+lowerName+" select"+tableName+"By"+UpLowUtil.toUpperCaseFirstOne(pKey.toLowerCase())+"(String id);\n\n");
            sb.append("	/**\n");
            sb.append(" 	* 通过查询参数获取信息\n");
            sb.append(" 	* @return\n");
            sb.append("    */ \n");
            sb.append("	List<"+lowerName+"> select"+tableName+"ByParam(Map paramMap); \n\n");
            sb.append("	/**\n"); 
            sb.append(" 	* 通过查询参数获取总条数\n");
            sb.append(" 	* @return\n");
            sb.append("    */ \n");
            sb.append("	int selectCount"+tableName+"ByParam(Map paramMap); \n\n");
            sb.append("	/**\n");
            sb.append(" 	* 更新 \n");
            sb.append(" 	* @return \n"); 
            sb.append("    */ \n");
            sb.append("	int update"+tableName+"("+lowerName+" "+tableName.toLowerCase()+");\n\n");
            sb.append("	/**\n");
            sb.append(" 	* 添加 \n");
            sb.append(" 	* @return\n");  
            sb.append("    */ \n");
            sb.append("	int add"+tableName+"("+lowerName+" "+tableName.toLowerCase()+");\n\n");
            sb.append("	/**\n");
            sb.append(" 	* 批量添加 \n");
            sb.append(" 	* @return\n");  
            sb.append("    */ \n");
            sb.append("	int muladd"+tableName+"(List<"+lowerName+"> list);\n\n");
            sb.append("	/**\n");
            sb.append(" 	* 删除 \n");
            sb.append(" 	* @return \n"); 
            sb.append("    */ \n");
            sb.append("	int delete"+tableName+"(String id);\n\n");
            sb.append("}\n\n");
            
            out.write(sb.toString().getBytes("utf-8"));
            log.info(sb.toString()) ;      
            log.info("创建文件I"+UpLowUtil.toUpperCaseFirstOne(tableName.toLowerCase())+"Mapper.java成功！") ;
        out.close();
    }


}
