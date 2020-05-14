package cn.hmc;

import cn.hmc.dao.UserDao;
import cn.hmc.pojo.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUserDao {

    @Autowired
    private UserDao userDao;

    //查询所有
    @Test
    public void testFindAll(){
        List<User> users = userDao.selectList(null);
        users.forEach(user -> System.out.println(user));
    }

    //根据主键查询一个
    @Test
    public void testFindById(){
        User user = userDao.selectById(2);
        System.out.println("user => " + user);
    }

    //条件查询
    @Test
    public void tsetFind(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("age",26);//设置等值查询
        //queryWrapper.lt("age",26);//设置小于查询
        queryWrapper.le("age",26);//设置小于等于
        List<User> users = userDao.selectList(queryWrapper);
        users.forEach(user -> System.out.println(user));
    }

    //模糊查询
    @Test
    public void testFindLike(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /**
         * like     %?%
         * likeleft     %?
         * likeright    ?%
         */
        queryWrapper.likeRight("name","张");
        List<User> users = userDao.selectList(queryWrapper);
        users.forEach(user -> System.out.println(user));
    }

    //保存
    @Test
    public void testSave(){
        User user = new User();
        user.setName("小明").setAge(27).setBir(new Date());
        userDao.insert(user);
    }

    //更新方法
    //基于主键id进行数据的修改
    @Test
    public void testUpdateById(){
        User user = userDao.selectById(3);
        user.setName("小李");
        userDao.updateById(user);
    }

    //批量修改
    @Test
    public void testUpdate(){
        User user = userDao.selectById(3);
        user.setName("osdoa");
        QueryWrapper<User> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("age",23);
        userDao.update(user,updateWrapper);
    }

    //基于id删除一个
    @Test
    public void testDeleteById(){
        userDao.deleteById(3);
    }

    //基于条件进行删除
    @Test
    public void testDelete(){
        QueryWrapper<User> deleteWrapper = new QueryWrapper<>();
        deleteWrapper.gt("age",23);
        userDao.delete(deleteWrapper);
    }

    //分页查询
    @Test
    public void testFindPage(){
        //参数1：当前页 默认值1  参数2：每页显示记录数 默认值10
        IPage<User> page = new Page<>(1,2);
        IPage<User> userIPage = userDao.selectPage(page, null);
        long total = userIPage.getTotal();//获取总条数
        System.out.println("总记录数：" + total);
        userIPage.getRecords().forEach(user -> System.out.println(user));

    }

}
