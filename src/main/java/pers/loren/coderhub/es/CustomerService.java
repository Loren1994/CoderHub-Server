package pers.loren.coderhub.es;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomers() {
        customerRepository.save(new Customer("111","Alice", "Smith"));
        customerRepository.save(new Customer("222","Bob", "Smith"));
    }

    public void getAll() {
        customerRepository.findAll().forEach(item -> System.out.println(item.getFirstName()));
    }

    public void getSomeone() {
        List<Customer> list1 = customerRepository.findByFirstName("Alice");
        List<Customer> list2 = customerRepository.findByLastName("Smith");
        System.out.println("list1.size=" + list1.size() + " list2.size=" + list2.size());
        System.out.println(JSON.toJSON(list1));
        System.out.println(JSON.toJSON(list2));
    }

}
