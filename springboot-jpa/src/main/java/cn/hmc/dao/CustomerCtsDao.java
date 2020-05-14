package cn.hmc.dao;

import cn.hmc.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 多表查询客户dao
 */
public interface CustomerCtsDao extends JpaRepository<Customer,Long>, JpaSpecificationExecutor<Customer> {
}
