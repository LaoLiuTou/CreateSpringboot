package utils.writeFiles.utils;

import utils.ProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Logger;


public class WriteFileUpload {
	
	/**
	 * 添加Bean
	 * 
	 * @return
	 */
	public static String writeFileUpload(){
		
		String status="success";
		String url,dirs;
		ProjectBean pb=new ProjectBean();
        url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                +"/controller/"+"FileUploadController.java";
        dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                +"/controller/";
		try {
			createFiles(dirs,url);
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
	public static  void createFiles(String dirs,String url) throws IOException{
		Logger log = Logger.getLogger("ZeroLog");   
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
            
            sb.append("package com."+pb.getProjectName().toLowerCase()+".controller;\n\n"); 
            sb.append("import java.io.File;\n");
            sb.append("import java.text.SimpleDateFormat;\n");
            sb.append("import java.util.ArrayList;\n");
            sb.append("import java.util.Date;\n");
            sb.append("import java.util.HashMap;\n");
            sb.append("import java.util.List;\n");
            sb.append("import java.util.Map;\n");
            sb.append("import java.util.UUID;\n");
            sb.append("import javax.servlet.http.HttpServletRequest;\n");
            sb.append("import org.apache.log4j.Logger;\n");
            sb.append("import org.springframework.stereotype.Controller;\n");
            sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
            sb.append("import org.springframework.web.bind.annotation.RequestParam;\n");
            sb.append("import org.springframework.web.bind.annotation.ResponseBody;\n");
            sb.append("import org.springframework.web.multipart.MultipartFile;\n\n");

            sb.append("@Controller\n");
            sb.append("public class FileUploadController {\n");
            sb.append("	SimpleDateFormat sdf = new SimpleDateFormat(\"yyyyMMdd\");\n");
            sb.append("	Logger logger = Logger.getLogger(\""+pb.getProjectName()+"Logger\");\n");
            sb.append("	/*** \n");
            sb.append("	 * 保存文件 \n");
            sb.append("	 * @param file \n");
            sb.append("	 * @return \n");
            sb.append("	 */  \n");
            sb.append("	private boolean saveFile(MultipartFile file, String path,String filename) {\n");  
            sb.append("		if (!file.isEmpty()) { \n"); 
            sb.append("			try {  \n");
            sb.append("			File filepath = new File(path);\n");
            sb.append("			if (!filepath.exists()) \n");
            sb.append("			filepath.mkdirs();\n");
            sb.append("			// 转存文件  \n");
            sb.append("			file.transferTo(new File(path+filename));  \n");
            sb.append("			return true;  \n");
            sb.append("		} catch (Exception e) {  \n");
            sb.append("			e.printStackTrace();  \n");
            sb.append("		}  \n");
            sb.append("	}  \n");
            sb.append("	return false;  \n");
            sb.append("	}  \n\n");

            sb.append("	@SuppressWarnings({ \"rawtypes\", \"unchecked\" })\n");
            sb.append("	@RequestMapping(\"/filesUpload\") \n"); 
            sb.append("	@ResponseBody\n");
            sb.append("	public Map filesUpload(@RequestParam(\"files\") MultipartFile[] files,HttpServletRequest request) {\n"); 
            sb.append("		Map resultMap=new HashMap();\n");
            sb.append("		try {\n");
            sb.append("			List<String> fileList= new ArrayList<String>();\n");
            sb.append("			String projectName=request.getParameter(\"project\");\n");
            sb.append("			if(projectName==null||projectName.equals(\"\")){\n");
            sb.append("				projectName=\"default\";\n");
            sb.append("		}\n");
            sb.append("		String middleStr=projectName+\"/\"+sdf.format(new Date())+\"/\";\n");
            sb.append("		String path=request.getSession().getServletContext().getRealPath(\"/\")+\"upload/\"+middleStr;\n");
            sb.append("		//判断file数组不能为空并且长度大于0 \n"); 
            sb.append("		if(files!=null&&files.length>0){  \n");
            sb.append("			//循环获取file数组中得文件  \n");
            sb.append("			for(int i = 0;i<files.length;i++){  \n");
            sb.append("				MultipartFile file = files[i];  \n");
            sb.append("				String filename=UUID.randomUUID()+\".\"+file.getOriginalFilename().split(\"\\\\.\")[1];\n");
            sb.append("				//保存文件  \n");
            sb.append("				if(saveFile(file, path,filename)){\n");
            sb.append("				fileList.add(request.getScheme()+\"://\"+ request.getServerName()+\n");
            sb.append("						\":\"+request.getServerPort()+\n");
            sb.append("						request.getContextPath()+\"/upload/\"+middleStr+filename);\n");
            sb.append("				} \n");
            sb.append("			}  \n");
            sb.append("		}\n");
            sb.append("		resultMap.put(\"status\", \"0\");\n");
            sb.append("		resultMap.put(\"data\", fileList);\n");
            sb.append("	}\n");
            sb.append("	catch (Exception e)\n");
            sb.append("	{\n");
            sb.append("		resultMap.put(\"status\", \"-1\");\n");
            sb.append("		resultMap.put(\"data\", \"上传失败!\");\n");
            sb.append("		e.printStackTrace();\n");
            sb.append("	}  \n");
            sb.append("	return resultMap;  \n");
            sb.append("	} \n"); 
            sb.append("}\n");

			
            out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ; 
        log.info("创建文件"+"FileUpload.java 成功！") ; 
        out.close();
    }


}
