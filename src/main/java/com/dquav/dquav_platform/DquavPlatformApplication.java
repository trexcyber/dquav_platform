package com.dquav.dquav_platform;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

/**
 * @author trex
 *
 */
@SpringBootApplication
@MapperScan("com.dquav.dquav_platform.mapper")
public class DquavPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(DquavPlatformApplication.class, args);
    }

    @Bean
    public MultipartConfigElement configElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        DataSize dataSize = DataSize.ofMegabytes(7);
        DataSize requestSize = DataSize.ofMegabytes(5);
        factory.setMaxFileSize(dataSize);
        factory.setMaxRequestSize(requestSize);
        return factory.createMultipartConfig();
    }

//    @Bean
//    public HttpMessageConverters fastJsonMessageConverters(){
//        FastJsonHttpMessageConverter fastJsonHttpMessageConverter= new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
//        return new HttpMessageConverters(fastJsonHttpMessageConverter);
//    }

}
