package runner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import task.Performance;
import task.Task;

public class Runner {
    public static void main(String[] args) {
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("context.xml");
        System.out.println("Для обозначения, что задача выполнена введите +");
        Task task1=applicationContext.getBean("1", Task.class);
        task1.getMassage();
        Performance.performance();
        Task task2=applicationContext.getBean("2", Task.class);
        task2.getMassage();
        Performance.performance();
        Task task3=applicationContext.getBean("3", Task.class);
        task3.getMassage();
    }
}
