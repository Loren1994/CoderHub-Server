package pers.loren.coderhub.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class ESController {

//    @Autowired
//    private TransportClient esConfig;

//    @GetMapping("/get")
//    private Result esTest(@PathVariable String id) {
//        if (id.isEmpty()) {
//            return new Result(HttpStatus.NOT_FOUND);
//        }
//        // 通过索引、类型、id向es进行查询数据
//        GetResponse response = esConfig.prepareGet("book", "novel", id).get();
//
//        if (!response.isExists()) {
//            return new Result(HttpStatus.NOT_FOUND);
//        }
//        // 返回查询到的数据
//        return new Result(HttpStatus.OK.value(), "查询成功", response.getSource());
//    }
}
