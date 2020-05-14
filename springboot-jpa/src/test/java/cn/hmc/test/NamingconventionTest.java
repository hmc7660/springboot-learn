package cn.hmc.test;

import cn.hmc.dao.CustomerDao;
import cn.hmc.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NamingconventionTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 测试方法名称规则的查询
     */
    @Test
    public void testFindByCustName(){
        Customer customer = customerDao.findByCustName("测试员");
        System.out.println(customer);
    }

    /**
     * 测试方法名称规则的查询
     * 模糊查询
     */
    @Test
    public void testFindByCustNameLike(){
        List<Customer> list = customerDao.findByCustNameLike("测试%");
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 测试方法名称规则的查询
     * 多条件查询
     */
    @Test
    public void testFindByCustNameLikeAndCustIndustry(){
        Customer customer = customerDao.findByCustNameLikeAndCustIndustry("测试%", "互联网");
        System.out.println(customer);

    }
}
