package saf.api;

import org.springframework.aop.support.StaticMethodMatcherPointcut;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @Author ISJINHAO
 * @Date 2021/4/3 13:39
 */
public class EchoServicePointcut extends StaticMethodMatcherPointcut {

    private final String methodName;

    public EchoServicePointcut(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return Objects.equals(methodName, method.getName());
    }

}
