package saf.annotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author ISJINHAO
 * @Date 2021/3/9 18:18
 */
@Aspect
@EnableAspectJAutoProxy
@Configuration
public class AnnotationSimpleBootstrap {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        context.register(AnnotationSimpleBootstrap.class);

        context.refresh();

        AnnotationSimpleBootstrap annotationSimpleBootstrap = context.getBean(AnnotationSimpleBootstrap.class);

        System.out.println(annotationSimpleBootstrap.getClass());

        context.close();

    }

    @Before("myPointCut()")
    public void sayHello() {
        System.out.println("hello");
    }

    @Pointcut("execution ( * saf.annotation.*.* (..))")
    private void myPointCut() {
    }

}
