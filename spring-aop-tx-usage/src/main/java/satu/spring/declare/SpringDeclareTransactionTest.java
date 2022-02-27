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
        context.register(DeclareConfiguration.class, TxTestServiceImpl.class);

        context.refresh();

        TxTestService txTestService = context.getBean(TxTestService.class);
        txTestService.insert(new TxTest("springDeclare", "aha44"));

        context.close();
    }

}
