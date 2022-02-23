package saf.api;

import org.springframework.aop.ThrowsAdvice;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since
 */
public class MyThrowsAdvice implements ThrowsAdvice {

//    public void afterThrowing(RuntimeException e) {
//        System.out.printf("Exception : %s\n", e);
//    }

    // 参数可以被注入
    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException e) {
        System.out.printf("Method : %s , args : %s , target : %s, exception : %s\n",
                method,
                Arrays.asList(args),
                target,
                e
        );
    }

    // 拦截IOException
    public void afterThrowing(IOException e) {
        System.out.println(e);
    }

}
