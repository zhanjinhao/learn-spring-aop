package saf.api;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @Author ISJINHAO
 * @Date 2021/3/9 18:34
 */
@Aspect
public class ApiAspectConfiguration {

    // pointcut  注意，它是描述信息，用于修饰join point
    @Pointcut("execution(public * *(..))")
    private void anyPublicMethod() {
        // Pointcut methods should have empty body
    }

    // advice  会被执行的代码
    @Before("anyPublicMethod()")
    public void beforeAnyPublicMethod() {
        System.out.println("@Before at anyPublicMethod");
    }

}
