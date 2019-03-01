package pers.loren.coderhub.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CustomerRepository extends ElasticsearchRepository<Customer, String> {
    public List<Customer> findByFirstName(String firstName);

    public List<Customer> findByLastName(String lastName);

}
