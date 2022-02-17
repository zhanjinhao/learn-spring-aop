package saf.api;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.AopContext;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author ISJINHAO
 * @Date 2021/3/9 18:32
 */
public class ApiSimpleAdviceBootstrap {

    public static void main(String[] args) {

        // 通过创建一个 HashMap 缓存，作为被代理对象
        Map<String, Object> cache = new HashMap<>();
        // 创建 Proxy 工厂(AspectJ)
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        // 设置暴露代理对象到 AopContext
        proxyFactory.setExposeProxy(true);
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                if ("put".equals(method.getName()) && args.length == 2) {
                    Object proxy = AopContext.currentProxy();
                    System.out.printf("[MethodBeforeAdvice] 当前存放是 Key: %s , Value : %s ，" +
                            "代理对象存的数据：%s\n", args[0], args[1], proxy);
                }
            }
        });

        // 添加 AfterReturningAdvice
        proxyFactory.addAdvice(new AfterReturningAdvice() {
            @Override
            public void afterReturning(Object returnValue, Method method, Object[] args, Object target)
                    throws Throwable {
                // returnValue 是执行 put 方法得到的返回值
                if ("put".equals(method.getName()) && args.length == 2) {
                    System.out.printf("[AfterReturningAdvice] 当前存放是 Key: %s , 新存放的 Value : %s , 之前关联的 Value : %s\n ",
                            args[0],    // key
                            args[1],    // new value
                            returnValue // old value
                    );
                }
            }
        });

        // 通过代理对象存储数据
        Map<String, Object> proxy = proxyFactory.getProxy();
        System.out.println(proxy.getClass());

        proxy.put("1", "A");
        System.out.println("-----------------------");
        proxy.put("1", "B");
        System.out.println("-----------------------");
        System.out.println(cache.get("1"));

    }

}
