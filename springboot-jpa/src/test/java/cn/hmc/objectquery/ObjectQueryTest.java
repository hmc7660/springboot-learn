package cn.hmc.objectquery;

import cn.hmc.dao.CustomerCtsDao;
import cn.hmc.dao.LinkManDao;
import cn.hmc.domain.Customer;
import cn.hmc.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectQueryTest {

    @Autowired
    private CustomerCtsDao customerCtsDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 测试对象导航查询（查询一个对象的时候，通过此对象查询所有的关联对象）
     *
     */
    @Test
    @Transactional  //解决no session问题
    public void testQuery(){
        //查询id为1的客户
        Customer customer = customerCtsDao.getOne(1L);
        //对象导航查询，此客户下的所有联系人
        Set<LinkMan> linkMans = customer.getLinkMans();

        for (LinkMan linkMan : linkMans) {
            System.out.println(linkMan);
        }
    }


    /**
     * 从联系人对象导航查询他的所属客户
     */
    @Test
    @Transactional
    public void testQuery2(){

        LinkMan linkMan1 = linkManDao.getOne(1L);
        Customer customer = linkMan1.getCustomer();
        System.out.println(customer.getCustName());
    }

}
