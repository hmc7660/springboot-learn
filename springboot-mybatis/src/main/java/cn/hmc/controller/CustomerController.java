package cn.hmc.controller;

import cn.hmc.mapper.CustomerMapper;
import cn.hmc.pojo.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/queryCustomerList")
    public List<Customer> queryCustomerList(){
        List<Customer> list = customerMapper.queryCustomerList();
        return list;
    }

    @GetMapping("/queryCustomerById/{id}")
    public Customer queryCustomerById(@PathVariable("id") int id){
        Customer customer = customerMapper.queryCustomerById(id);
        return customer;
    }

    @GetMapping("/addCustomer")
    public String addCustomer(){
        Customer customer = new Customer();
        customer.setName("哦啊打开");
        customer.setTelephone("125xxxx4567");
        customer.setAddress("柳州");
        customer.setRemark("备注1");
        customerMapper.addCustomer(customer);
        return "ADD OK!";
    }

    @GetMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable("id") int id){
        Customer customer = customerMapper.queryCustomerById(id);
        customer.setRemark("ZZZZZZ");
        customerMapper.updateCustomer(customer);
        return "UPDATE OK!";
    }

    @GetMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") int id){
        customerMapper.deleteCustomer(id);
        return "DELETE OK!";
    }
}
