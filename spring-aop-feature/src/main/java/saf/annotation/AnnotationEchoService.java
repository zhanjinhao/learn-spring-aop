package saf.annotation;

import ao.EchoService;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author 01395265
 * @date 2022/2/17
 */
@Configuration // Configuration class
@EnableAspectJAutoProxy // 激活 Aspect 注解自动代理
public class AnnotationEchoService implements EchoService {

    @Override
    public String echo(String message) throws NullPointerException {
        return "annotation echo, " + message;
    }

}
