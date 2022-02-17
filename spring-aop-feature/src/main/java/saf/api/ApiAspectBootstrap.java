package saf.api;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ISJINHAO
 * @Date 2021/3/9 18:32
 */
public class ApiAspectBootstrap {

    public static void main(String[] args) {

        // 通过创建一个 HashMap 缓存，作为被代理对象
        Map<String, Object> cache = new HashMap<>();
        // 创建 Proxy 工厂(AspectJ)
        AspectJProxyFactory proxyFactory = new AspectJProxyFactory(cache);
        // 增加 Aspect 配置类
        proxyFactory.addAspect(ApiAspectConfiguration.class);
        // 设置暴露代理对象到 AopContext，设置为true，后续可以通过 AopContext.currentProxy() 获取
        proxyFactory.setExposeProxy(true);

        // 通过代理对象存储数据
        Map<String, Object> proxy = proxyFactory.getProxy();
        System.out.println(proxy.getClass());

        proxy.put("1", "A");
        System.out.println("-----------------------");
        proxy.put("1", "B");
        System.out.println("-----------------------");
        System.out.println(proxy.get("1"));

    }

}
