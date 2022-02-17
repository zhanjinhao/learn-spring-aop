package saf.api;

import saf.EchoServiceMethodInterceptor;
import saf.pointcut.EchoServiceEchoMethodPointcut;
import saf.pointcut.EchoServicePointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import ao.DefaultEchoService;
import ao.EchoService;

/**
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class ApiPointcutBootstrap {

    public static void main(String[] args) {

        EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo", EchoService.class);

        ComposablePointcut pointcut = new ComposablePointcut(EchoServiceEchoMethodPointcut.INSTANCE);
        // 组合实现
        pointcut.intersection(echoServicePointcut.getClassFilter());
        pointcut.intersection(echoServicePointcut.getMethodMatcher());


        // 将 Pointcut 适配成 Advisor

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new EchoServiceMethodInterceptor());

        DefaultEchoService defaultEchoService = new DefaultEchoService();
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        // 添加 Advisor
        proxyFactory.addAdvisor(advisor);

        // 获取代理对象
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        System.out.println(echoService.echo("Hello,World"));
    }
}
