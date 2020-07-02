package utils.writeFiles.configuration;

import utils.ProjectBean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Logger;

public class WriteTaskPool {
    static Logger log = Logger.getLogger("Creater");
    /**
     * 添加Bean
     *
     * @return
     */
    public static String writeTaskPoolConfig(){

        String status="success";
        String url,dirs;
        ProjectBean pb=new ProjectBean();
        try {
            url=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                    +"/"+"TaskPoolConfig.java";
            dirs=pb.getProjectUrl()+"/"+pb.getProjectName()+"/"+"src/main/java/com/"+pb.getProjectName().toLowerCase()
                    +"/";
            createTaskPoolConfigFiles(dirs,url);



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
    public static  void createTaskPoolConfigFiles(String dirs,String url) throws IOException{

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
        sb.append("package com."+pb.getProjectName().toLowerCase()+";\n\n");
        sb.append("import org.springframework.context.annotation.Bean;\n");
        sb.append("import org.springframework.context.annotation.Configuration;\n");
        sb.append("import org.springframework.scheduling.annotation.EnableAsync;\n");
        sb.append("import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;\n");
        sb.append("import java.util.concurrent.Executor;\n");
        sb.append("import java.util.concurrent.ThreadPoolExecutor;\n\n");
        sb.append("//启动异步\n");
        sb.append("@EnableAsync\n");
        sb.append("@Configuration\n");
        sb.append("class TaskPoolConfig {\n");
        sb.append("    //设置Bean的名称不设置的话没有办法在 任务中对应 配置信息\n");
        sb.append("    @Bean(\"taskExecutor\")\n");
        sb.append("    public Executor taskExecutor() {\n");
        sb.append("        //根据ThreadPoolTaskExecutor 创建建线程池\n");
        sb.append("        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();\n");
        sb.append("        //为线程设置初始的线程数量 5条线程\n");
        sb.append("        executor.setCorePoolSize(5);\n");
        sb.append("        //为线程设置最大的线程数量 10条线程\n");
        sb.append("        executor.setMaxPoolSize(10);\n");
        sb.append("        //为任务队列设置最大 任务数量\n");
        sb.append("        executor.setQueueCapacity(200);\n");
        sb.append("        //设置 超出初始化线程的 存在时间为60秒\n");
        sb.append("        //也就是 如果现有线程数超过5 则会对超出的空闲线程 设置摧毁时间 也就是60秒\n");
        sb.append("        executor.setKeepAliveSeconds(60);\n");
        sb.append("        //设置 线程前缀\n");
        sb.append("        executor.setThreadNamePrefix(\"taskExecutor-\");\n");
        sb.append("        //线程池的饱和策略 我这里设置的是 CallerRunsPolicy 也就是由用调用者所在的线程来执行任务 共有四种\n");
        sb.append("        //AbortPolicy：直接抛出异常，这是默认策略；\n");
        sb.append("        //CallerRunsPolicy：用调用者所在的线程来执行任务；\n");
        sb.append("        //DiscardOldestPolicy：丢弃阻塞队列中靠最前的任务，并执行当前任务；\n");
        sb.append("        //DiscardPolicy：直接丢弃任务；\n");
        sb.append("        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());\n");
        sb.append("        //设置在关闭线程池时是否等待任务完成\n");
        sb.append("        executor.setWaitForTasksToCompleteOnShutdown(true);\n");
        sb.append("        //设置等待终止的秒数\n");
        sb.append("        executor.setAwaitTerminationSeconds(60);\n");
        sb.append("        //返回设置完成的线程池\n");
        sb.append("        return executor;\n");
        sb.append("    }\n");

        sb.append("    /**\n");
        sb.append("     * 使用方法 @Component\n");
        sb.append("    */\n");
        sb.append("    /*@Async(\"taskExecutor\")\n");
        sb.append("    public void testTaskPool(String task) throws InterruptedException {\n");
        sb.append("        System.out.println(\"任务\"+task);\n");
        sb.append("    }*/\n");


        sb.append("}\n");

        out.write(sb.toString().getBytes("utf-8"));
        log.info(sb.toString()) ;
        log.info("创建文件"+"TaskPoolConfig.java成功！") ;
        out.close();
    }

}