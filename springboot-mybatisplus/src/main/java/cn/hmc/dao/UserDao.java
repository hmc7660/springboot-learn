package cn.hmc.dao;

import cn.hmc.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

//使用mybatis-plus增强接口(提供了各种CRUD方法)
public interface UserDao extends BaseMapper<User> {
}
