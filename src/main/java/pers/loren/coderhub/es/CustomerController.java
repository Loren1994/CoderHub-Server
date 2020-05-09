package pers.loren.coderhub.es;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.loren.coderhub.util.Result;

import java.io.IOException;

@RestController
@RequestMapping("/es")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/save")
    public Result save() throws IOException {
//        Client transportClient = elasticsearchTemplate.getClient();
//        IndexResponse response = transportClient.prepareIndex("es-customer", "customer")
//                .setSource(jsonBuilder()
//                        .startObject()
//                        .field("firstName", "Alice")
//                        .field("latName", "Smith")
//                        .endObject()
//                )
//                .execute()
//                .actionGet();
        customerService.saveCustomers();
        return new Result("添加成功");
    }

    @GetMapping("/all")
    public Result getAll() {
        customerService.getAll();
        return new Result("获取成功");
    }

    @GetMapping("/one")
    public Result getOne() {
        customerService.getSomeone();
        return new Result("查找成功");
    }


}
