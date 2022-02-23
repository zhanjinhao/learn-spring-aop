package saf.api;

import ao.DefaultEchoService;
import ao.EchoService;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.framework.DefaultAopProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import saf.EchoServiceMethodInterceptor;

/**
 * @Author ISJINHAO
 * @Date 2021/4/3 12:52
 */
public class ApiAopProxyFactoryBootstrap {

    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象（被代理）
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
//        proxyFactory.setTargetClass(DefaultEchoService.class);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        // 获取代理对象
        DefaultAopProxyFactory defaultAopProxyFactory = new DefaultAopProxyFactory();
        AopProxy aopProxy = defaultAopProxyFactory.createAopProxy(proxyFactory);
        EchoService proxy = (EchoService) aopProxy.getProxy();
        proxy.echo("hello");
    }

}
