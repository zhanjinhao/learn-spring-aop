package saf.annotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration // Configuration class
@EnableAspectJAutoProxy // 激活 Aspect 注解自动代理
public class AnnotationPointcutBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(AnnotationPointcutBootstrap.class, AnnotationAspectConfiguration.class);
        context.refresh();

        AnnotationPointcutBootstrap aspectJAnnotationDemo = context.getBean(AnnotationPointcutBootstrap.class);

        aspectJAnnotationDemo.execute();

        context.close();
    }

    public void execute() {
        System.out.println("execute()...");
    }

}
