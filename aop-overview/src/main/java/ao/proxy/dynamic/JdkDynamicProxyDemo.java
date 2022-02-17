package ao.proxy.dynamic;

import ao.DefaultEchoService;
import ao.EchoService;
import ao.proxy.astatic.ProxyEchoService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ISJINHAO
 */
public class JdkDynamicProxyDemo {

    // 字节码反编译后类似于：
    // class $Proxy0 extends java.lang.reflect.Proxy implements EchoService {
    //   $Proxy0(InvocationHandler handler){
    //      super(handler);
    //   }
    // }

    public static void main(String[] args) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // $Proxy0
        Object proxy = Proxy.newProxyInstance(classLoader, new Class[]{EchoService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (EchoService.class.isAssignableFrom(method.getDeclaringClass())) {
                    ProxyEchoService echoService = new ProxyEchoService(new DefaultEchoService());
                    return echoService.echo((String) args[0]);
                }
                return null;
            }
        });

        EchoService echoService = (EchoService) proxy;
        echoService.echo("Hello,World");

        // $Proxy1
        Object proxy2 = Proxy.newProxyInstance(classLoader,
                new Class[]{Comparable.class},
                (proxy1, method, args1) -> {
                    return null;
                });

        System.out.println(proxy2);
    }
}
