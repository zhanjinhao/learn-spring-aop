package saf.util;

import ao.EchoService;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AopContext;
import saf.EchoAspectConfiguration;

/**
 * @Author ISJINHAO
 * @Date 2022/2/23 19:35
 */
public class AopContextTest {

    public static void main(String[] args) {

        MyEchoService defaultEchoService = new MyEchoService();
        // 注入目标对象（被代理）
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(defaultEchoService);
        proxyFactory.setExposeProxy(true);
        proxyFactory.addAspect(EchoAspectConfiguration.class);
        // 获取代理对象
        EchoService proxy = proxyFactory.getProxy();
        System.out.println(proxy.echo("hello"));

    }


    private static class MyEchoService implements EchoService {

        @Override
        public String echo(String message) {
            System.out.println(AopContext.currentProxy().getClass());
            return "[ECHO] " + message;
        }

    }

}

