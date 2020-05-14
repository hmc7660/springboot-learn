package cn.hmc.mapper;

import cn.hmc.pojo.Customer;
import org.apache.ibatis.annotations.*;

import java.util.List;

//这个注解表示这是一个mybatis的mapper类
@Mapper
public interface CustomerMapper {

    @Select("select * from tb_customer")
    List<Customer> queryCustomerList();

    @Select("select * from tb_customer where id = #{id}")
    Customer queryCustomerById(int id);

    @Insert("insert into tb_customer (name,telephone,address,remark) value(#{name},#{telephone},#{address},#{remark})")
    int addCustomer(Customer customer);

    @Update("update tb_customer set name=#{name},telephone=#{telephone},address=#{address},remark=#{remark} where id=#{id}")
    int updateCustomer(Customer customer);

    @Delete("delete from tb_customer where id=#{id}")
    int deleteCustomer(int id);

}
