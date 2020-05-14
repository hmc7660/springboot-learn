package cn.hmc;

import cn.hmc.mapper.CustomerMapper;
import cn.hmc.pojo.Customer;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class CustomerMapperTest {

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void testQueryCustomerList(){
        List<Customer> customerList = customerMapper.queryCustomerList();
        customerList.forEach(customer -> {
            log.info("customer={}",customer);
        });
    }

    @Test
    public void testQueryCustomerById(){
        Customer customer = customerMapper.queryCustomerById(1);
        log.info("customer={}",customer);
    }

    @Test
    public void testAddCustomer(){
        Customer customer = new Customer();
        customer.setName("测试新增");
        customer.setTelephone("135xxxx6542");
        customer.setAddress("长沙");
        customer.setRemark("备注");
        customerMapper.addCustomer(customer);
        testQueryCustomerList();
    }

    @Test
    public void testUpdateCustomer(){
        Customer customer = customerMapper.queryCustomerById(1); //先查询再修改
        customer.setName("测试修改");
        customerMapper.updateCustomer(customer);
        testQueryCustomerList();
    }

    @Test
    public void testDeleteCustomer(){
        customerMapper.deleteCustomer(4);
        testQueryCustomerList();
    }
}
