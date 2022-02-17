package saf.api;

import saf.EchoServiceMethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import ao.DefaultEchoService;
import ao.EchoService;

/**
 * @Author ISJINHAO
 * @Date 2021/4/3 12:52
 */
public class ApiProxyFactoryBootstrap {

    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象（被代理）
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
//        proxyFactory.setTargetClass(DefaultEchoService.class);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.getClass());
        System.out.println(echoService.echo("Hello,World"));
    }

}
