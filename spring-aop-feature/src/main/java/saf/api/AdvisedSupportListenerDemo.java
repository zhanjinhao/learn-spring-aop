package saf.api;

import ao.DefaultEchoService;
import ao.EchoService;
import org.aopalliance.aop.Advice;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AdvisedSupportListener;
import org.springframework.aop.framework.ProxyFactory;
import saf.EchoServiceMethodInterceptor;

public class AdvisedSupportListenerDemo {

    public static void main(String[] args) {
        DefaultEchoService defaultEchoService = new DefaultEchoService();
        // 注入目标对象（被代理）
        ProxyFactory proxyFactory = new ProxyFactory(defaultEchoService);
        proxyFactory.setTargetClass(DefaultEchoService.class);
        // 添加 Advice 实现 MethodInterceptor < Interceptor < Advice
        proxyFactory.addAdvice(new EchoServiceMethodInterceptor());
        proxyFactory.addListener(new AdvisedSupportListener() {
            @Override
            public void activated(AdvisedSupport advised) {
                System.out.println("AOP 配置对象：" + advised + " 已激活");
                System.out.println(advised.getAdvisors().length);
            }

            @Override
            public void adviceChanged(AdvisedSupport advised) {
                System.out.println("AOP 配置对象：" + advised + " 已变化");
                System.out.println(advised.getAdvisors().length);
            }
        });
        // 获取代理对象
        // 激活事件触发 createAopProxy() <- getProxy()
        EchoService echoService = (EchoService) proxyFactory.getProxy();
        proxyFactory.addAdvice(new Advice() {
            @Override
            public String toString() {
                return "MyAdvice";
            }
        });
    }
}
