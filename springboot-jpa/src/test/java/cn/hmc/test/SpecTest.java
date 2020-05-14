package cn.hmc.test;

import cn.hmc.dao.CustomerDao;
import cn.hmc.pojo.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.*;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecTest {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件查询单个对象
     */
    @Test
    public void testSpec(){
        /**
         * 自定义查询条件
         *  1.实现Specification接口（提供泛型，查询的对象类型）
         *  2.实现toPredicate方法（构造查询条件）
         *  3.需要借助方法参数中的两个参数（
         *      root：获取需要查询的对象属性
         *      CriteriaBuilder：构造查询条件，内部封装了很多查询条件
         *      ）
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件（第一个参数：需要比较的属性（path对象），第二个参数：当前需要比较的取值）
                Predicate predicate = cb.equal(custName, "测试");//进行精准匹配
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec).get();
        System.out.println(customer);
    }


    /**
     * 多条件动态查询
     */
    @Test
    public void testSpec1(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");
                Predicate p1 = cb.equal(custName, "测试");
                Predicate p2 = cb.equal(custIndustry, "互联网");
                Predicate predicate = cb.and(p1, p2);   //以与的形式拼接查询条件
                return predicate;
            }
        };
        Customer customer = customerDao.findOne(spec).get();
        System.out.println(customer);
    }

    /**
     * 根据客户名称模糊匹配，返回客户列表
     * gt,lt,le,like:需要得到path对象，根据path指定比较的参数类型，再去进行比较
     *      指定类型：path.as(类型的字节码对象)
     */
    @Test
    public void testSpec3(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "测试%");
                return predicate;
            }
        };
        List<Customer> list = customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }


    /**
     * 排序
     */
    @Test
    public void testSpec4(){
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Object> custName = root.get("custName");
                Predicate predicate = cb.like(custName.as(String.class), "测试%");
                return predicate;
            }
        };
        /**
         * 创建排序对象
         * 第一个参数：排序的顺序（倒序，正序）
         * 第二个参数：排序的属性名称
         */
        Sort sort = new Sort(Sort.Direction.DESC,"custId");
        List<Customer> list = customerDao.findAll(spec,sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 分页查询
     *      Specification：查询条件
     *      Pageable：分页参数
     *          分页参数：查询的页码,每页查询的条数
     *      findAll(Specification,Pageable)：带有条件的分页
     *      findAll(Pageable)：没有条件的分页
     *      返回：Page（SpringDataJpa封装好的pageBean对象，数据列表，总条数...）
     */
    @Test
    public void testSpec5(){
        /**
         * 使用PageRequest.of构建Pageable，需要传入两个参数
         *      第一个参数：当前查询的页数（从0开始，即0表示查询的是第一页）
         *      第二个参数：每页查询的数量
         */
        Pageable pageable = PageRequest.of(0,2);
        Page<Customer> page = customerDao.findAll(pageable);
        System.out.println(page.getTotalElements());//得到总条数
        System.out.println(page.getContent());//得到数据集合列表
        System.out.println(page.getTotalPages());//得到总页数
    }
}
