package saf;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class EchoServiceMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println("拦截 EchoService 的方法：" + method);
        return invocation.proceed();
    }
}
