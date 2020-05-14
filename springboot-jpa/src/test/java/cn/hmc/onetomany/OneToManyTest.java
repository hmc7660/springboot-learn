package cn.hmc.onetomany;

import cn.hmc.dao.CustomerCtsDao;
import cn.hmc.dao.LinkManDao;
import cn.hmc.domain.Customer;
import cn.hmc.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OneToManyTest {

    @Autowired
    private CustomerCtsDao customerCtsDao;

    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个客户，保存一个联系人
     */
    @Test
    @Transactional //配置事务
    @Rollback(value = false) //不自动回滚
    public void testAdd(){
        //创建一个客户，创建一个联系人
        Customer customer = new Customer();
        customer.setCustName("ABC");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小华");

        /**
         * 配置客户和联系人的关联关系
         *      从客户的角度：发送两条insert语句，发送一条更新语句更新数据库（更新外键）
         *      由于配置了客户到联系人的关系（一对多）：客户可以对外键进行维护
         *
         *      不需要发送更新语句：只需要一的一方放弃维护全即可
         *      方法：@OneToMany(mappedBy = "customer") （在一的一方使用此注解）
         */
        customer.getLinkMans().add(linkMan);

        /**
         * 配置联系人到客户的关系（多对一）
         *  只发送了两条insert语句
         *  由于配置了联系人到客户的映射关系（多对一）
         */
        linkMan.setCustomer(customer);

        customerCtsDao.save(customer);
        linkManDao.save(linkMan);
    }


    /**
     * 测试级联添加:保存一个客户的同时，保存客户的所有联系人
     *      需要在操作主体的实体类上，配置casacde属性
     */
    @Test
    @Transactional //配置事务
    @Rollback(value = false) //不自动回滚
    public void testCascaAdd(){
        Customer customer = new Customer();
        customer.setCustName("SDK");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("小李");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        customerCtsDao.save(customer);
    }


    /**
     * 测试级联删除:删除一号客户的同时，删除一号客户的所有联系人
     *      需要在操作主体的实体类上，配置casacde属性
     */
    @Test
    @Transactional //配置事务
    @Rollback(value = false) //不自动回滚
    public void testCascaDelete(){
        //查询一号客户
        Customer customer = customerCtsDao.findById(1L).get();
        //删除一号客户
        customerCtsDao.delete(customer);
    }
}
