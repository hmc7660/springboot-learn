package cn.hmc.dao;

import cn.hmc.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *      JpaRepository<操作的实体类类型,实体类中主键属性的类型>
 *          *   封装了基本的CRUD操作
 *      JpaSpecificationExecutor<操作的实体类类型>
 *          *   封装了复杂查询（分页）
 */
public interface CustomerDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
    /**
     * 根据客户名称查询客户
     * jpql: from Customer where custName = ?1
     * 配置jpql语句，使用的@Query注解
     */
    @Query(value = "from Customer where custName = ?1")
    public Customer findJpql(String custName);

    /**
     * 根据客户名称和客户id查询客户
     * jpql：from Customer where custName = ?1 and custId = ?2
     */
    @Query(value = "from Customer where custName = ?1 and custId = ?2")
    public Customer findCustNameAndId(String custName, Long custId);

    /**
     * 根据id更新客户的名称
     * jpql : update Customer set custName = ? where custId = ?
     */
    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying  //增删改操作需要加上该注解
    @Transactional    //事务的支持
    public void updateCustomer(Long custId,String custName);


    /*************************SQL语句的查询******************************/

    /**
     * 使用SQL的形式查询
     * 查询全部的客户
     * select * from tb_customer;
     */
    @Query(value = "select * from tb_customer",nativeQuery = true)
    public List<Object []> findSql();

    /**
     * 模糊查询
     * @param custName
     * @return
     */
    @Query(value = "select * from tb_customer where cust_name like ?1",nativeQuery = true)
    public List<Object []> findSqlLike(String custName);


    /*************************方法名称规则查询******************************/

    /**
     * findBy查询
     * 命名规则：findBy + 对象中属性名称（首字母大写）
     */
    public Customer findByCustName(String custName);

    /**
     * 模糊查询
     */
    public List<Customer> findByCustNameLike(String custName);

    /**
     * 多条件查询
     * 使用客户名称模糊查询和客户所属行业精准匹配查询
     */
    public Customer findByCustNameLikeAndCustIndustry(String custName,String custIndustry);

}
