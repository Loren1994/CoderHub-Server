package pers.loren.coderhub;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import pers.loren.coderhub.jwt.JWTFilter;
import pers.loren.coderhub.leetcode.LeetCode;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("pers.loren.coderhub.mapper")
//@EnableScheduling
@EnableAsync
@EnableTransactionManagement
@EnableCaching
public class CoderhubApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(CoderhubApplication.class, args);
    }

    //注册拦截器
    @Bean
    public FilterRegistrationBean<JWTFilter> jwtFilter() {
        final FilterRegistrationBean<JWTFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JWTFilter());
        //添加需要拦截的url - 验证token
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/user/getAll");
        String[] arr = new String[urlPatterns.size()];
        registrationBean.addUrlPatterns(urlPatterns.toArray(arr));
        return registrationBean;
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(CoderhubApplication.class);
    }
}

