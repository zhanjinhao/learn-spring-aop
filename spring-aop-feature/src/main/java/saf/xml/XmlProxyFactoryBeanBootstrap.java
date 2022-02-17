package saf.xml;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ao.EchoService;

/**
 * @Author ISJINHAO
 * @Date 2021/4/3 12:36
 */
public class XmlProxyFactoryBeanBootstrap {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:/META-INF/proxyfactorybean-aop-context.xml");

        EchoService echoService = context.getBean("echoServiceProxyFactoryBean", EchoService.class);

        System.out.println(echoService.getClass());

        System.out.println(echoService.echo("aha"));

        context.close();
    }

}
