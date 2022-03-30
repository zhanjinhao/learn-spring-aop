package satu.spring.declare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import satu.mybatis.TxTest;
import satu.mybatis.TxTestMapper;

/**
 * @Author ISJINHAO
 * @Date 2022/2/26 23:01
 */
@Component
public class TxTestServiceImpl2 implements TxTestService {

    @Autowired
    private TxTestMapper txTestMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(TxTest txTest) {
        return txTestMapper.insert(txTest) > 0;
    }
}
