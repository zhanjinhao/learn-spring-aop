package satu.spring.declare;

import org.springframework.context.annotation.*;
import satu.mybatis.TxTest;

/**
 * @Author ISJINHAO
 * @Date 2022/2/26 23:00
 */
public class SpringDeclareTransactionTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(DeclareConfiguration.class, TxTestServiceImpl.class, TxTestServiceImpl2.class);

        context.refresh();

        TxTestService txTestService = (TxTestService) context.getBean("txTestServiceImpl");
        txTestService.insert(new TxTest("springDeclare", "aha44"));
        TxTestService txTestService2 = (TxTestService) context.getBean("txTestServiceImpl2");
        txTestService2.insert(new TxTest("springDeclare2", "aha44"));

        context.close();
    }

}
