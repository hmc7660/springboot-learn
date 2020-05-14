package cn.hmc.manytomany;

import cn.hmc.bean.Role;
import cn.hmc.bean.User;
import cn.hmc.dao.RoleDao;
import cn.hmc.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 保存一个角色，保存一个用户
     * 多对多放弃维护权：被动的一方放弃[角色]（谁被选择谁放弃）
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testAdd(){
        User user = new User();
        user.setUserName("小明");

        Role role = new Role();
        role.setRoleName("管理员");

        //两方都维护中间表数据，会出现主键冲突的错误，必须有一方放弃维护权
        //配置用户到角色的关系，可以对中间表中的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表中的数据进行维护
        role.getUsers().add(user);

        userDao.save(user);
        roleDao.save(role);
    }


    /**
     * 测试级联添加（保存一个用户的同时保存用户的关联角色）
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeAdd(){
        User user = new User();
        user.setUserName("小明");

        Role role = new Role();
        role.setRoleName("管理员");

        //两方都维护中间表数据，会出现主键冲突的错误，必须有一方放弃维护权
        //配置用户到角色的关系，可以对中间表中的数据进行维护
        user.getRoles().add(role);
        //配置角色到用户的关系，可以对中间表中的数据进行维护
        role.getUsers().add(user);

        userDao.save(user);
    }

    /**
     * 测试级联删除（删除一个用户的同时删除用户的关联角色）
     */
    @Test
    @Transactional
    @Rollback(false)
    public void testCascadeDelete(){
        //查询一号用户
        User user = userDao.findById(1L).get();
        //删除一号用户
        userDao.delete(user);
    }
}
