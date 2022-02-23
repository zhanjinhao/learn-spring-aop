package saf.api;

import org.springframework.aop.framework.ProxyFactory;

import java.util.Random;

/**
 * @Author ISJINHAO
 * @Date 2022/2/19 18:34
 */
public class ApiThrowsAdviceBootstrap {


    public static void main(String[] args) throws Exception {

        ApiThrowsAdviceBootstrap instance = new ApiThrowsAdviceBootstrap();

        ProxyFactory proxyFactory = new ProxyFactory(instance);

        proxyFactory.addAdvice(new MyThrowsAdvice());

        ApiThrowsAdviceBootstrap proxy = (ApiThrowsAdviceBootstrap) proxyFactory.getProxy();
        proxy.execute();
        proxy.execute();

    }

    public void execute() {
        Random random = new Random();
        if (random.nextBoolean()) {
            throw new RuntimeException("For Purpose.");
        }
        System.out.println("Executing...");
    }

}
