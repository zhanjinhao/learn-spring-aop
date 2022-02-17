package ao.interceptor;

import java.lang.reflect.Method;

/**
 * @author ISJINHAO
 */
public interface AfterReturnInterceptor {

    /**
     * 后置执行
     *
     * @param proxy
     * @param method
     * @param args
     * @param returnResult 执行方法返回结果
     * @return
     */
    Object after(Object proxy, Method method, Object[] args, Object returnResult);
}
