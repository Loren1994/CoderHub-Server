package pers.loren.coderhub.es;

import com.alibaba.fastjson.JSON;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void saveCustomers() {
        val list = new ArrayList<Customer>();
        list.add(new Customer("111", "Alice", "Smith"));
        list.add(new Customer("222", "Bob", "Smith"));
        customerRepository.save(list.get(0));
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
