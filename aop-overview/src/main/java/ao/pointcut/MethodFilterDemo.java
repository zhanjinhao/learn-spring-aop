package ao.pointcut;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

/**
 * @author ISJINHAO
 */
public class MethodFilterDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        String targetClassName = "sao.Echo2Service";
        // 获取当前线程 ClassLoader
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        // 获取目标类
        Class<?> targetClass = classLoader.loadClass(targetClassName);

        // 方法定义：String echo(String message);
        // Spring 反射工具类
//        Method targetMethod = ReflectionUtils.findMethod(targetClass, "echo", String.class);
//        System.out.println(targetMethod);

        // 查找方法有且仅有一个String类型参数 且 throws NullPointerException 的方法
        ReflectionUtils.doWithMethods(targetClass, new ReflectionUtils.MethodCallback() {
            @Override
            public void doWith(Method method) throws IllegalArgumentException, IllegalAccessException {
                System.out.println("拦截到符合要求的方法，方法名：" + method.getName());
            }
        }, new ReflectionUtils.MethodFilter() {
            @Override
            public boolean matches(Method method) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?>[] exceptionTypes = method.getExceptionTypes();
                return parameterTypes.length == 1
                        && String.class.equals(parameterTypes[0])
                        && exceptionTypes.length == 1
                        && NullPointerException.class.equals(exceptionTypes[0]);
            }
        });
    }
}
