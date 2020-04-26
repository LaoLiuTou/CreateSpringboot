package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class CopyFiles {
	   public  String read(String filename) throws IOException{  
	        BufferedReader in = new BufferedReader(new FileReader(filename));  
	        String s;  
	        StringBuilder sb = new StringBuilder();  
	        while((s = in.readLine())!= null){  
	            sb.append(s);  
	        }  
	        in.close();  
	        return sb.toString();  
	    }  
	    public  void copyfile(String inputname,String outputname) throws IOException{  
	        InputStream in = new FileInputStream(inputname);  
	        OutputStream out = new FileOutputStream(outputname);  
	        byte[] buffer = new byte[1024];  
	        int read = 0;  
	        while((read = in.read(buffer))!= -1){  
	            out.write(buffer,0,read);  
	            System.out.println(read);  
	            System.out.println(buffer);  
	            System.out.println("/n");  
	        }  
	        in.close();  
	        out.close();  
	    }  
	    public  void copyDir(String inputname,String outputname) throws IOException{  
	        (new File(outputname)).mkdirs();  
	        File[] file=(new File(inputname)).listFiles();  
	        for(int i=0;i<file.length;i++){  
	            if(file[i].isFile()){  
	                file[i].toString();  
	                FileInputStream input=new FileInputStream(file[i]);  
	                //mkdir if destination does not exist  
	                File outtest = new File(outputname);  
	                if (!outtest.exists()){  
	                    outtest.mkdir(); 
	                }
	                 
	                FileOutputStream output=new FileOutputStream(outputname+"/"+(file[i].getName()).toString()); 
	                
	                byte[] b=new byte[1024*5];  
	                int len;  
	                while((len=input.read(b))!=-1){  
	                    output.write(b,0,len);  
	                }  
	                output.flush();  
	                output.close();  
	                input.close();  
	            }else if (file[i].isDirectory()){  
	                //System.out.print(file[i].getAbsolutePath()+ "/n" + file[i].getName());  
	                System.out.println(file[i].toString()+ "," + outputname + "//" + file[i].getName());  
	                copyDir(file[i].toString(),outputname+ "//" + file[i].getName());  
	            }  
	        }  
	    }  
	    public static void main(String[] args)throws IOException{  
	        //System.out.print(read("BOOTEX.LOG"));  
	    	//ProjectBean pb=new ProjectBean();
	        //copyDir(System.getProperty("user.dir")+"/WebRoot",pb.getProjectUrl()+"/"+pb.getProjectName()+"/WebRoot");  
	        ///Date date = new Date();  
	        //SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");  
	        //System.out.println(df.format(new Date()));  
	        //System.out.print(date.getMonth()+"/n");  
	        //System.out.print(date.getDate()+ "/n");  
	        //System.out.print(date.toGMTString()+"/n");  
	    	
	    	//System.out.println(CopyFiles.class.getResource("/").getPath());
	    	//System.out.println(pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/jdbc/jdbc.properties");
	    	//System.out.println(System.getProperty("user.dir"));
	    }  
}
