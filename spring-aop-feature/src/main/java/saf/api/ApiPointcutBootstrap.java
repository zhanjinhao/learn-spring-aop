package saf.api;

import saf.EchoServiceMethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.ComposablePointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import ao.DefaultEchoService;
import ao.EchoService;

/**
 * @since
 */
public class ApiPointcutBootstrap {

    public static void main(String[] args) {

        ComposablePointcut pointcut = new ComposablePointcut();
        pointcut.union(EchoServiceEchoMethodPointcut.INSTANCE);
        // 组合实现
        EchoServicePointcut echoServicePointcut = new EchoServicePointcut("echo");
        pointcut.intersection(echoServicePointcut.getMethodMatcher());

        // Base interface holding AOP advice (action to take at a joinpoint) and a filter determining the applicability of the advice (such as a pointcut). T
        // 用 Pointcut 和 PointCut 适配成 Advisor
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
