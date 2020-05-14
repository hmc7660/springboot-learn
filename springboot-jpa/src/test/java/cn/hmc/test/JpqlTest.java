package cn.hmc.test;

import cn.hmc.dao.CustomerDao;
import cn.hmc.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpqlTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    public void testFindJPQL(){
        Customer customer = customerDao.findJpql("赵六");
        System.out.println(customer);
    }

    @Test
    public void testFindCustNameAndId(){
        Customer customer = customerDao.findCustNameAndId("赵六", 4L);
        System.out.println(customer);
    }

    /**
     * 测试Jpql更新操作
     */
    @Test
    public void testUpdateCustomer(){
        customerDao.updateCustomer(4L,"测试jpql更新操作");
    }

}
