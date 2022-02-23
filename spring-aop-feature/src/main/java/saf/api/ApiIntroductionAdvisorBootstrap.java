package saf.api;

import ao.EchoService;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 只能用于JDK动态代理
 *
 * @since
 */
public class ApiIntroductionAdvisorBootstrap implements EchoService, Comparable<ApiIntroductionAdvisorBootstrap> {

    public static void main(String[] args) {
        // EchoService 和 Comparable
        ApiIntroductionAdvisorBootstrap target = new ApiIntroductionAdvisorBootstrap();
        // 使用该构造器会使得 IntroductionInfo 失效
        // target -> EchoService 和 Comparable
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(target);
        // 添加 IntroductionAdvisor
        proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println("BeforeAdvice : " + method);
            }
        }, new IntroductionInfo() {
            @Override
            public Class<?>[] getInterfaces() {
                return new Class[]{EchoService.class};
            }
        }));

        Object proxy = proxyFactory.getProxy();

        EchoService echoService = (EchoService) proxy;

        echoService.echo("Hello,World");

        // 代理对象没有实现 Comparable 接口
        Comparable comparable = (Comparable) proxy;
        comparable.compareTo(null);

    }

    @Override
    public int compareTo(ApiIntroductionAdvisorBootstrap o) {
        return 0;
    }

    @Override
    public String echo(String message) throws NullPointerException {
        return "IntroductionAdvisorDemo : " + message;
    }
}
