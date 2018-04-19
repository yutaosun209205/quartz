package quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloJob implements Job {

    String name;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //打印当前执行时间， format：yyyy-MM-dd HH:mm:ss
        Date date = new Date();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(String.format("Execute Time: %s, Name: %s", currentTime, name));
        //编写具体的业务逻辑

        System.out.println("Hello World");
    }


    /**
     * If you add setter methods to your job class that correspond to the names of keys in the JobDataMap
     * (such as a setJobSays(String val) method for the data in the example above),
     * then Quartz’s default JobFactory implementation will automatically call those setters when the job is instantiated
     *
     * 如果在Job类中增加与{@link org.quartz.JobDataMap}中键值名称相同的set方法，在job实例化的时候
     * Quartz 中 {@link org.quartz.spi.JobFactory} 的默认实现会自动调用set方法
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
