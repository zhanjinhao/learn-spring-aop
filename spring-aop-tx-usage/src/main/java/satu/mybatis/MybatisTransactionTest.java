package satu.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @Author ISJINHAO
 * @Date 2020/11/26 19:52
 */
public class MybatisTransactionTest {

    public static void main(String[] args) {

        String resource = "mybatis-config.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            // false 没有作用，因为事务已经被Spring接管了。
            // 实现在 SpringManagedTransactionFactory#newTransaction()
//            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE, false);
            SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.SIMPLE);

            try {
                TxTestMapper txTestMapper = sqlSession.getMapper(TxTestMapper.class);
                txTestMapper.insert(new TxTest("isjinhao", "aha"));
//                sqlSession.commit();
            } finally {
                sqlSession.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
