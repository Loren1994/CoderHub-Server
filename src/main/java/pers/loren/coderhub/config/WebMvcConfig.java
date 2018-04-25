package pers.loren.coderhub.config;

import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.*;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    //将默认jackson替换为fastjson
    @Bean
    public HttpMessageConverters customConverters() {
        return new HttpMessageConverters(new FastJsonHttpMessageConverter());
    }

    //配置序列化
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        //fastjson
        for (HttpMessageConverter converter : converters) {
            if (converter instanceof FastJsonHttpMessageConverter) {
                FastJsonConfig config = ((FastJsonHttpMessageConverter) converter).getFastJsonConfig();
                config.setSerializerFeatures(WriteMapNullValue, WriteNullNumberAsZero, WriteNullListAsEmpty, WriteNullStringAsEmpty, WriteNullBooleanAsFalse);
            }
        }
        //jackson
//        for (HttpMessageConverter converter : converters) {
//            if (converter instanceof MappingJackson2HttpMessageConverter) {
//                ObjectMapper mapper = ((MappingJackson2HttpMessageConverter) converter).getObjectMapper();
//                mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
//                mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
//            }
//        }

    }
}
