package cn.hmc.test;

import cn.hmc.dao.CustomerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SqlTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 测试SQL查询
     */
    @Test
    public void testFindSql(){
        List<Object[]> list = customerDao.findSql();
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }

    /**
     * 测试SQL模糊查询
     */
    @Test
    public void testFindSqlLike(){
        List<Object[]> list = customerDao.findSqlLike("测试%");
        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
        }
    }
}
