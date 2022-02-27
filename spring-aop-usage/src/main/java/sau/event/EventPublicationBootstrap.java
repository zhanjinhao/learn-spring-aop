package sau.event;

import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.context.event.EventPublicationInterceptor;

import java.lang.reflect.Method;

/**
 * @Author ISJINHAO
 * @Date 2022/2/24 18:33
 */
@Configuration
@EnableAspectJAutoProxy
public class EventPublicationBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext
                context = new AnnotationConfigApplicationContext();
        context.register(EventPublicationBootstrap.class, AopExecutor.class, StaticExecutor.class);
        context.refresh();

        AopExecutor aopExecutor = context.getBean(AopExecutor.class);
        // org.springframework.context.event.EventPublicationInterceptor.invoke方法里写了，Event里的source就是target
        // 可以自己copy一份EventPublicationInterceptor，这样就能满足自己需求了
        aopExecutor.execute();


        System.out.println("-------------------------------------------");
        StaticExecutor staticExecutor = context.getBean(StaticExecutor.class);
        staticExecutor.execute();

        context.close();
    }

    @Bean
    public EventPublicationInterceptor eventPublicationInterceptor() {
        EventPublicationInterceptor eventPublicationInterceptor = new EventPublicationInterceptor();
        eventPublicationInterceptor.setApplicationEventClass(ExecutedEvent.class);
        return eventPublicationInterceptor;
    }

    @Bean
    public Pointcut pointcut() {
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "execute".equals(method.getName()) && AopExecutor.class.equals(targetClass);
            }
        };
    }

    @Bean
    public PointcutAdvisor pointcutAdvisor(Pointcut pointcut, EventPublicationInterceptor eventPublicationInterceptor) {
        return new DefaultPointcutAdvisor(pointcut, eventPublicationInterceptor);
    }

    // 4. 处理事件 - ExecutedEvent
    @EventListener(ExecutedEvent.class)
    public void executed(ExecutedEvent event) {
        Object source = event.getSource();
        System.out.println(source);
        System.out.println(source.getClass());
        System.out.println("Executed : " + event);
    }

}
