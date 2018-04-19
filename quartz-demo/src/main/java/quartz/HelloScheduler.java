package quartz;

import org.quartz.*;
import org.quartz.impl.StdScheduler;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HelloScheduler {

    public static void main(String[] args) throws SchedulerException {
        //创建JobDetai实例,将该实例与Job class绑定
        JobDetail jobDetailJoke = JobBuilder.newJob(HelloJob.class)
                .withIdentity("JokeJob","group1")
                .usingJobData("name","Joke")
                .build();
        JobDetail jobDetailMark = JobBuilder.newJob(HelloJob.class)
                .withIdentity("MarkJob","group1")
                .usingJobData("name", "Mark")
                .build();

        //创建Trigger实例，定义该Job立即执行，并每隔2秒执行一次
        Trigger triggerJoke = TriggerBuilder.newTrigger()
                .withIdentity("myTrigger","group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();
        Trigger triggerMark = TriggerBuilder.newTrigger()
                .withIdentity("MarkTrigger","group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();
        Scheduler schedulerJoke = StdSchedulerFactory.getDefaultScheduler() ;
        Scheduler schedulerMark = StdSchedulerFactory.getDefaultScheduler() ;
        schedulerJoke.start();
        schedulerMark.start();
        //打印当前执行时间， format：yyyy-MM-dd HH:mm:ss
        Date date = new Date();
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
        System.out.println(String.format("Current Time: %s", currentTime));
        schedulerJoke.scheduleJob(jobDetailJoke, triggerJoke);
        schedulerJoke.scheduleJob(jobDetailMark, triggerMark);
    }
}
