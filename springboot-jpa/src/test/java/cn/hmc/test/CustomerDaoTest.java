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
public class CustomerDaoTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 更具id查询
     */
    @Test
    public void testFindOne(){

        Customer customer = customerDao.findById(1L).get();
        System.out.println(customer);
    }

    /**
     * save : 保存或者更新
     *      根据传递的对象是否存在主键id，
     *      如果没有id主键属性：保存
     *      存在id主键属性，根据id查询数据，更新数据
     */
    @Test
    public void testSave(){
        Customer customer = new Customer();
        customer.setCustName("测试保存");
        customer.setCustLevel("test");
        customer.setCustIndustry("测试");
        customerDao.save(customer);
    }

    /**
     * 测试更新
     */
    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setCustId(2L);
        customer.setCustName("测试更新");
        customerDao.save(customer);
    }

    /**
     * 测试删除
     */
    @Test
    public void testDelete(){
        customerDao.deleteById(2L);
    }

    /**
     * 查询所有
     */
    @Test
    public void testFindAll(){
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 测试统计查询，查询客户的总数量
     */
    @Test
    public void testCount(){
        long count = customerDao.count();
        System.out.println(count);
    }


}
