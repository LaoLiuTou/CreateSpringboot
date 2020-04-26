package utils.writeFiles.controller;

import utils.ProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.logging.Logger;


public class WriteController {

    Logger log = Logger.getLogger("Creater");
	/**
	 * 添加Action
	 * 
	 * @return
	 */
	public String writeController(String tableName,String pKey){
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
		url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
			+"/controller/"+tableName.toLowerCase()+"/"+lowerName+"Controller.java";
		dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
			+"/controller/"+tableName.toLowerCase()+"/";
		try {
			createFiles(dirs,url,tableName,pKey);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			status="failure";
			e.printStackTrace();
		}
		 
		return status;
	}
	/**
	 * 添加 class
	 * 
	 * @return
	 */
	public  void createFiles(String dirs,String url,String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".controller."+tableName.toLowerCase()+";\n\n");

            sb.append("import com.fasterxml.jackson.databind.JavaType;\n");
            sb.append("import com.fasterxml.jackson.databind.ObjectMapper;\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".entity."+tableName.toLowerCase()+"."+lowerName+";\n");
            sb.append("import com."+pb.getProjectName().toLowerCase()+".service."+tableName.toLowerCase()+".I"+lowerName+"Service;\n");
            sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
            sb.append("import org.springframework.http.MediaType;\n");
            sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
            sb.append("import org.springframework.web.bind.annotation.RequestParam; \n");
            sb.append("import org.springframework.web.bind.annotation.RestController;\n");
            sb.append("import com.github.pagehelper.PageHelper;\n");
            sb.append("import com.github.pagehelper.PageInfo;\n");
            sb.append("import javax.servlet.http.HttpServletRequest;\n");
            sb.append("import java.io.IOException;\n");
            sb.append("import java.text.ParseException;\n");
            sb.append("import java.text.SimpleDateFormat;\n");
            sb.append("import java.util.*;\n");
            sb.append("import java.util.logging.Logger;\n\n");
            sb.append("/**\n");
            sb.append(" * @author LT\n");
            sb.append(" */\n");
            sb.append("@RestController\n");
            sb.append("public class "+lowerName+"Controller {\n\n");
            sb.append("	private I"+lowerName+"Service i"+lowerName+"Service;\n");
            sb.append("	@Autowired\n");
            sb.append("	public void set"+lowerName+"Service (I"+lowerName+"Service i"+lowerName+"Service) {\n");
            sb.append("	   this.i"+lowerName+"Service = i"+lowerName+"Service;\n");
            sb.append("	}\n\n");
            sb.append("	private SimpleDateFormat sdf = new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss\");\n");
        	sb.append("	private Logger logger = Logger.getLogger(\""+pb.getProjectName()+"\");\n");
        	
            out.write(sb.toString().getBytes("utf-8"));
            log.info(sb.toString()) ;      
            log.info("创建文件"+lowerName+"Action.java成功！") ;        
        out.close();
    }
 
	/**
	 * 添加 add  函数
	 * 
	 * @return
	 */
	public  void addAddFunction(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
		 
        File file=new File(url);
        
        if(!file.exists())
            file.createNewFile();
        
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("\n");
        sb.append("	@RequestMapping(value=\"/add"+lowerName+"\", produces = MediaType.APPLICATION_JSON_VALUE)\n");
        sb.append("	public Map<String, Object> add("+lowerName+" "+tableName.toLowerCase()+"){\n");
        sb.append("		Map<String, Object> resultMap= new HashMap<>();\n");
        sb.append("		try {\n"); 
        sb.append("			i"+lowerName+"Service.add"+lowerName+"("+tableName.toLowerCase()+");\n"); 
        sb.append("			resultMap.put(\"status\", \"0\");\n"); 
        sb.append("			resultMap.put(\"msg\", "+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"());\n"); 
        sb.append("			logger.info(\"新建成功，主键：\"+"+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"());\n"); 
        sb.append("		} catch (Exception e) {\n"); 
        sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("			resultMap.put(\"msg\", \"新建失败！\");\n"); 
        sb.append("			logger.info(\"新建失败！\"+e.getLocalizedMessage());\n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n"); 
   
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	/**
	 * 添加 muladd  函数
	 * 
	 * @return
	 */
	public  void addmulAddFunction(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
				+"/controller/"+tableName+"/"+lowerName+"Controller.java";
		
		File file=new File(url);
		
		if(!file.exists())
			file.createNewFile();
		
		FileOutputStream out=new FileOutputStream(file,true); 
		StringBuffer sb=new StringBuffer();
        sb.append("\n");
		sb.append("	@RequestMapping(value=\"/muladd"+lowerName+"\", produces = MediaType.APPLICATION_JSON_VALUE)\n");
		sb.append("	public Map<String, Object>  muladd(HttpServletRequest request,"+lowerName+" "+tableName.toLowerCase()+"){\n");
		sb.append("		Map<String, Object> resultMap=new HashMap<>();\n");
		sb.append("		try {\n"); 
		sb.append("			String data=request.getParameter(\"data\");\n"); 
		sb.append("			ObjectMapper objectMapper = new ObjectMapper();\n"); 
		sb.append("			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(ArrayList.class, "+lowerName+".class);\n"); 
		sb.append("			List<"+lowerName+"> list = (List<"+lowerName+">)objectMapper.readValue(data, javaType);\n"); 
		//sb.append("			List<"+lowerName+"> list=(List<"+lowerName+">) JSONArray.toCollection(dataJA,"+lowerName+".class);\n"); 
		sb.append("			i"+lowerName+"Service.muladd"+lowerName+"(list);\n"); 
		sb.append("			resultMap.put(\"status\", \"0\");\n"); 
		sb.append("			resultMap.put(\"msg\", \"新建成功\");\n"); 
		sb.append("			logger.info(\"新建成功，主键：\"+"+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"());\n"); 
		sb.append("		} catch (Exception e) {\n"); 
		sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
		sb.append("			resultMap.put(\"msg\", \"新建失败！\");\n"); 
		sb.append("			logger.info(\"新建失败！\"+e.getLocalizedMessage());\n"); 
		sb.append("			e.printStackTrace();\n"); 
		sb.append("		}\n"); 
		sb.append("		return resultMap;\n"); 
		sb.append("	}\n"); 
		
		out.write(sb.toString().getBytes("utf-8"));
		log.info(sb.toString()) ;        
		out.close();
	}
	
	/**
	 * 添加 select 函数
	 * 
	 * @return
	 */
	public  void addSelectFunction(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("\n");
        sb.append("	@RequestMapping(value=\"/select"+lowerName+"\", produces = MediaType.APPLICATION_JSON_VALUE)\n");
        sb.append("	public Map<String, Object> select("+lowerName+" "+tableName.toLowerCase()+"){\n");
        sb.append("		Map<String, Object> resultMap= new HashMap<>();\n");
        sb.append("		try {\n"); 
        sb.append("			if("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()==null){\n"); 
        sb.append("				resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"参数不能为空！\");\n"); 
        sb.append("			}\n"); 
        sb.append("			else{\n"); 
        sb.append("				"+lowerName+" resultSelect=i"+lowerName+"Service.select"+lowerName+"By"+toUpperCaseFirstOne(pKey.toLowerCase())+"("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()+\"\");\n"); 
        sb.append("				resultMap.put(\"status\", \"0\");\n"); 
        sb.append("				resultMap.put(\"msg\", resultSelect);\n"); 
        sb.append("			}\n"); 
        sb.append("		} catch (Exception e) {\n"); 
        sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("			resultMap.put(\"msg\", \"查询失败！\");\n"); 
        sb.append("			logger.info(\"查询失败！\"+e.getLocalizedMessage());\n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n");  
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	
	/**
	 * 添加 update  函数
	 * 
	 * @return
	 */
	public  void addUpdateFunction(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("\n");
        sb.append("	@RequestMapping(value=\"/update"+lowerName+"\", produces = MediaType.APPLICATION_JSON_VALUE)\n");
        sb.append("	public Map update("+lowerName+" "+tableName.toLowerCase()+"){\n");
        sb.append("		Map<String, Object> resultMap= new HashMap<>();\n");
        sb.append("		try {\n"); 
        sb.append("			if("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()==null){\n"); 
        sb.append("				resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"参数不能为空！\");\n"); 
        sb.append("			}\n"); 
        sb.append("			else{\n"); 
        sb.append("				int resultUpdate=i"+lowerName+"Service.update"+lowerName+"("+tableName.toLowerCase()+");\n");
        sb.append("				resultMap.put(\"status\", \"0\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"更新成功！\");\n"); 
        sb.append("				logger.info(\"更新成功，主键：\"+"+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"());\n"); 
        sb.append("			}\n"); 
        sb.append("		} catch (Exception e) {\n"); 
        sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("			resultMap.put(\"msg\", \"更新失败！\");\n"); 
        sb.append("			logger.info(\"更新失败！\"+e.getLocalizedMessage());\n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n");  
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	/**
	 * 添加 delete  函数
	 * 
	 * @return
	 */
	public  void addDeleteFunction(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("\n");
        sb.append("	@RequestMapping(value=\"/delete"+lowerName+"\", produces = MediaType.APPLICATION_JSON_VALUE)\n");
        sb.append("	public Map<String, Object> delete("+lowerName+" "+tableName.toLowerCase()+"){\n");
        sb.append("		Map<String, Object> resultMap= new HashMap<>();\n");
        sb.append("		try {\n"); 
        sb.append("			if("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()==null){\n"); 
        sb.append("				resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"参数不能为空！\");\n"); 
        sb.append("			}\n"); 
        sb.append("			else{\n"); 
        sb.append("				int resultDelete=i"+lowerName+"Service.delete"+lowerName+"("+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"()"+"+\"\");\n");
        sb.append("				resultMap.put(\"status\", \"0\");\n"); 
        sb.append("				resultMap.put(\"msg\", \"删除成功！\");\n"); 
        sb.append("				logger.info(\"删除成功，主键：\"+"+tableName.toLowerCase()+".get"+toUpperCaseFirstOne(pKey.toLowerCase())+"());\n"); 
        sb.append("			}\n"); 
        sb.append("		} catch (Exception e) {\n"); 
        sb.append("			resultMap.put(\"status\", \"-1\");\n"); 
        sb.append("			resultMap.put(\"msg\", \"删除失败！\");\n"); 
        sb.append("			logger.info(\"删除失败！\"+e.getLocalizedMessage());\n"); 
        sb.append("			e.printStackTrace();\n"); 
        sb.append("		}\n"); 
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n");  
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	
	/**
	 * 添加 list  函数
	 * 
	 * @return
	 */
	public  void addListFunctionStart(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        sb.append("\n");
        sb.append("	@RequestMapping(value=\"/list"+lowerName+"\", produces = MediaType.APPLICATION_JSON_VALUE)\n");
        sb.append("	public Map<String, Object> list(@RequestParam(value = \"page\", defaultValue = \"1\") int page,\n");
        sb.append("	        @RequestParam(value = \"size\", defaultValue = \"10\") int size,\n");
        sb.append("	        @RequestParam(value = \"orderby\", defaultValue = \"ID DESC\") String orderby,\n");
        sb.append("	        HttpServletRequest request, "+lowerName+" "+tableName.toLowerCase()+"){\n");
        sb.append("		Map<String, Object> resultMap=new HashMap<>();\n");
        sb.append("		try {\n");
        sb.append("			PageHelper.startPage(page, size,orderby);\n");
        sb.append("			Map<Object, Object> paramMap=new HashMap<>();\n");




        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	/**
	 * 添加 list  函数
	 * 
	 * @return
	 */
	public  void addListFunctionEnd(String tableName,String pKey) throws IOException{
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
         
        
        sb.append("			List<"+lowerName+"> list=i"+lowerName+"Service.select"+lowerName+"ByParam(paramMap);\n");
        sb.append("			PageInfo<"+lowerName+"> pageList = new PageInfo<>(list);\n");
        sb.append("			resultMap.put(\"status\", \"0\");\n");
        sb.append("			resultMap.put(\"msg\", pageList);\n");
        sb.append("		} catch (Exception e) {\n");
        sb.append("			resultMap.put(\"status\", \"-1\");\n");
        sb.append("			resultMap.put(\"msg\", \"查询失败！\");\n");
        sb.append("			logger.info(\"查询失败！\"+e.getLocalizedMessage());\n");
        sb.append("			e.printStackTrace();\n");
        sb.append("		}\n");
        sb.append("		return resultMap;\n"); 
        sb.append("	}\n");  
        sb.append("}\n"); 
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
        out.close();
    }
	
	/**
	 * 动态  list 函数的参数
	 * 
	 * @return
	 */
	public  void addListFunctionParam(String tableName,String colName,String type) throws IOException{
		Logger log = Logger.getLogger("Creater");
		String lowerName= toUpperCaseFirstOne(tableName.toLowerCase()) ;
		ProjectBean pb=new ProjectBean();
		String url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
		+"/controller/"+tableName+"/"+lowerName+"Controller.java";
        File file=new File(url);
        if(!file.exists())
            file.createNewFile();
        FileOutputStream out=new FileOutputStream(file,true); 
        StringBuffer sb=new StringBuffer();
        if(type.toUpperCase().equals("DATE")||type.toUpperCase().equals("DATETIME")){
        	sb.append("			String "+colName.toLowerCase()+"From=request.getParameter(\""+colName.toLowerCase()+"From\");\n");
        	sb.append("			String "+colName.toLowerCase()+"To=request.getParameter(\""+colName.toLowerCase()+"To\");\n");
        	sb.append("			if("+colName.toLowerCase()+"From!=null&&!"+colName.toLowerCase()+"From.equals(\"\"))\n");
        	sb.append("			paramMap.put(\""+colName.toLowerCase()+"From\", sdf.parse("+colName.toLowerCase()+"From));\n");
        	sb.append("			if("+colName.toLowerCase()+"To!=null&&!"+colName.toLowerCase()+"To.equals(\"\"))\n");
        	sb.append("			paramMap.put(\""+colName.toLowerCase()+"To\", sdf.parse("+colName.toLowerCase()+"To));\n");
        }
        else{
        	sb.append("			paramMap.put(\""+colName.toLowerCase()+"\","+lowerName.toLowerCase()+".get"+toUpperCaseFirstOne(colName.toLowerCase())+"());\n");
        }
        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;        
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
