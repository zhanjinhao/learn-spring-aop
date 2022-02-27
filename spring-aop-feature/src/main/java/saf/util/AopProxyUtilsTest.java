package saf.util;

import ao.DefaultEchoService;
import ao.EchoService;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.framework.ProxyFactory;
import saf.EchoServiceMethodInterceptor;

/**
 * @Author ISJINHAO
 * @Date 2022/2/23 20:12
 */
public class AopProxyUtilsTest {


    public static void main(String[] args) {
        getSingletonTarget();
    }

    public static void getSingletonTarget() {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.getClass());
        System.out.println(echoService.echo("Hello,World"));

        // 获取被代理的对象
        Object singletonTarget = AopProxyUtils.getSingletonTarget(echoService);
        System.out.println(singletonTarget.getClass());
    }

}
