package utils.writeFiles.token;

import utils.ProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class WriteToken{
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加token
     *
     * @return
     */
    public static String writeToken(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                    +"/token/"+"AuthenticationInterceptor.java";
            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                    +"/token/";
            createTokenFiles(dirs,url);



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
    public static  void createTokenFiles(String dirs,String url) throws IOException{
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
        sb.append("package com."+pb.getProjectName().toLowerCase()+".token;\n\n");

        sb.append("import com.auth0.jwt.JWT;\n");
        sb.append("import com.auth0.jwt.JWTVerifier;\n");
        sb.append("import com.auth0.jwt.algorithms.Algorithm;\n");
        sb.append("import com.fasterxml.jackson.databind.ObjectMapper;\n");
        sb.append("import org.springframework.web.servlet.HandlerInterceptor;\n");
        sb.append("import org.springframework.web.servlet.ModelAndView;\n");

        sb.append("import javax.servlet.http.HttpServletRequest;\n");
        sb.append("import javax.servlet.http.HttpServletResponse;\n");
        sb.append("import java.io.IOException;\n");
        sb.append("import java.io.PrintWriter;\n");
        sb.append("import java.util.HashMap;\n");
        sb.append("import java.util.Map;\n");
        sb.append("import java.util.Date;\n");
        sb.append("import java.util.logging.Logger;\n\n");
        sb.append("/**\n");
        sb.append(" * @author LT\n");
        sb.append(" */\n");
        sb.append("public class AuthenticationInterceptor implements HandlerInterceptor {\n\n");
        sb.append("    private Logger logger = Logger.getLogger(\""+pb.getProjectName()+"\");\n\n");
        sb.append("    private static String jwtkey=\"@TTIVY@\";\n\n");
        sb.append("    public static String getToken(String userid) {\n");
        sb.append("        String token= JWT.create().withAudience(userid)\n");
        sb.append("                .withExpiresAt(new Date(System.currentTimeMillis()+60000))\n");
        sb.append("                .sign(Algorithm.HMAC256(jwtkey));\n");
        sb.append("        return token;\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {\n");
        sb.append("        String token = httpServletRequest.getHeader(\"token\");// 从 http 请求头中取出 token\n");
        sb.append("        try {\n");
        sb.append("            // 执行认证\n");
        sb.append("            if (token == null) {\n");
        sb.append("                logger.info(\"无token，请重新登录\");\n");
        sb.append("                responseMessage(httpServletResponse, \"无token，请重新登录\");\n");
        sb.append("                return false;\n");
        sb.append("            }\n");
        sb.append("            // 获取 token 中的 user id\n");
        sb.append("            //String userId = JWT.decode(token).getAudience().get(0);\n");
        sb.append("            // 验证 token\n");
        sb.append("            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtkey)).build();\n");
        sb.append("            jwtVerifier.verify(token);\n");
        sb.append("            return true;\n");
        sb.append("        } catch (Exception e) {\n");
        sb.append("            responseMessage(httpServletResponse,  \"token验证出错！\");\n");
        sb.append("            return false;\n");
        sb.append("        }\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public void postHandle(HttpServletRequest httpServletRequest,\n");
        sb.append("                           HttpServletResponse httpServletResponse,\n");
        sb.append("                           Object o, ModelAndView modelAndView)  {\n");
        sb.append("    }\n\n");
        sb.append("    @Override\n");
        sb.append("    public void afterCompletion(HttpServletRequest httpServletRequest,\n");
        sb.append("                                HttpServletResponse httpServletResponse,\n");
        sb.append("                                Object o, Exception e) {\n");
        sb.append("    }\n\n");
        sb.append("    /**\n");
        sb.append("     * 请求不通过，返回错误信息给客户端\n");
        sb.append("    */\n");
        sb.append("    private void responseMessage(HttpServletResponse response,  String  message) throws IOException {\n");
        sb.append("        //response.setContentType(\"text/html;charset=UTF-8\");\n");
        sb.append("        response.setContentType(\"application/json;charset=utf-8\");\n");
        sb.append("        PrintWriter out=response.getWriter();\n");
        sb.append("        Map<String, String> result= new HashMap<>();\n");
        sb.append("        result.put(\"state\",\"-1\");\n");
        sb.append("        result.put(\"msg\",message);\n");
        sb.append("        ObjectMapper objectMapper = new ObjectMapper();\n");
        sb.append("        out.print(objectMapper.writeValueAsString(result));\n");
        sb.append("        out.flush();\n");
        sb.append("        out.close();\n");
        sb.append("    }\n");

        sb.append("    /**\n");
        sb.append("     * 使用方法\n");
        sb.append("    */\n");
        sb.append("    /*@RequestMapping(value=\"/login\", produces = MediaType.APPLICATION_JSON_VALUE)\n");
        sb.append("    public String login(@RequestParam(value = \"username\") String username,@RequestParam(value = \"password\") String password) throws IOException {\n");
        sb.append("        Map<String, Object> resultMap= new HashMap<>();\n");
        sb.append("        Map<Object, Object> paramMap=new HashMap<>();\n");
        sb.append("        paramMap.put(\"username\",username);\n");
        sb.append("        paramMap.put(\"password\",password);\n");
        sb.append("        List<User> list =iUserService.selectUserByParam(paramMap);\n");
        sb.append("        ObjectMapper objectMapper = new ObjectMapper();\n");
        sb.append("        if(list.size()>0){\n");
        sb.append("            resultMap.put(\"token\", AuthenticationInterceptor.getToken(list.get(0).getId().toString()));\n");
        sb.append("            resultMap.put(\"userInfo\",list.get(0));\n");
        sb.append("        }\n");
        sb.append("        else{\n");
        sb.append("            resultMap.put(\"status\", \"-1\");\n");
        sb.append("            resultMap.put(\"msg\", \"登录失败！\");\n");
        sb.append("        }\n");
        sb.append("        return objectMapper.writeValueAsString(resultMap);\n");
        sb.append("    }*/\n");


        sb.append("}\n");

        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+"AuthenticationInterceptor.java 成功！") ;
        out.close();
    }

}

