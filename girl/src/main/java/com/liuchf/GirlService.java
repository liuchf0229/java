package com.liuchf;

import com.liuchf.entity.Girl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class GirlService {

    @Autowired
    private GirlRepository girlRepository;

    /**
     * @Transactional --事务管理
     * 操作多条数据,必须都成功才成功
     * */
    @Transactional
    public void insertTwo() {
        Girl girlA = new Girl();
        girlA.setCupSize("A");
        girlA.setAge(18);
        girlRepository.save(girlA);

        Girl girlB = new Girl();
        girlB.setCupSize("B");
        girlB.setAge(19);
        girlRepository.save(girlB);
    }
}
