package satu.spring.manual;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import satu.mybatis.TxTest;
import satu.mybatis.TxTestMapper;

/**
 * @Author ISJINHAO
 * @Date 2022/2/26 22:22
 */
public class SpringManualTransactionTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring-tx-manual-context.xml");

        PlatformTransactionManager transactionManager = (PlatformTransactionManager) context.getBean("dataSourceTransactionManager");

        SqlSessionFactory bean = context.getBean(SqlSessionFactory.class);

        SqlSession sqlSession = bean.openSession(ExecutorType.SIMPLE, false);
        TxTestMapper mapper = sqlSession.getMapper(TxTestMapper.class);

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        mapper.insert(new TxTest("manualSpring", "aha2"));

        transactionManager.commit(status);

    }

}
