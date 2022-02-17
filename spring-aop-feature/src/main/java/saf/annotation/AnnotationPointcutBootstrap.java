package saf.annotation;

import ao.EchoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import saf.AspectConfiguration;

public class AnnotationPointcutBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 换成DefaultEchoService.class就不会生效
        context.register(AspectConfiguration.class, AnnotationEchoService.class);
        context.refresh();

        EchoService echoService = context.getBean(EchoService.class);
        System.out.println(echoService.getClass());
        System.out.println(echoService.echo("123"));

        context.close();
    }

}
