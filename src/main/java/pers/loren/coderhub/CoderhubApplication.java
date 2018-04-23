package pers.loren.coderhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("pers.loren.coderhub.mapper")
//@ComponentScan("pers.loren.coderhub.mapper")
public class CoderhubApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoderhubApplication.class, args);
    }
}
