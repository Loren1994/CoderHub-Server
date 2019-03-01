package pers.loren.coderhub.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "es-customer", type = "customer", shards = 2, replicas = 1, refreshInterval = "-1")
public class Customer {
    @Id
    private String id;
    private String firstName;
    private String lastName;
}
