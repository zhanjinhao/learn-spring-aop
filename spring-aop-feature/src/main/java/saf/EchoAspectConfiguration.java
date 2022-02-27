package saf;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.framework.AopContext;

/**
 * @Author ISJINHAO
 * @Date 2021/3/9 18:34
 */
@Aspect
public class EchoAspectConfiguration {

    // pointcut  注意，它是描述信息，用于修饰join point
    @Pointcut("execution(public * echo*(..))")
    private void echoPublicMethod() {
        // Pointcut methods should have empty body
    }

    // advice  会被执行的代码
    @Before("echoPublicMethod()")
    public void beforeAnyPublicMethod() {
        System.out.println("@Before at anyPublicMethod");
    }

}
