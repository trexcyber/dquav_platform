package com.dquav.dquav_platform.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author TrEx
 * @date 2021/7/15 - 11:21
 * <p>
 * 使用此方法（addCorsMappings）配置之后再使用自定义拦截器时跨域相关配置就会失效 。
 * <p>
 * 原因是请求经过的先后顺序问题，当请求到来时会先进入拦截器中，
 * 而不是进入Mapping映射中，所以返回的头信息中并没有配置的跨域信息。
 * <p>
 * 跨域
 * 拦截器（全局配置）
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //        请求的方法地址 请求的ip地址 请求的头文件 请求的方法 设置请求发送Cookie信息 请求的连接时长
        registry.addMapping("/**")
//                SpringBoot升级2.4.0 将.allowedOrigins替换成.allowedOriginPatterns
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .exposedHeaders("Set-Cookie")
                .allowCredentials(true)
                .maxAge(1728000);
//      info打印跨域请求
        System.out.println("设置跨域请求");

        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}

/**
 * 有其他自定义拦截器时
 */
//    private CorsConfiguration corsConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
////    请求常用的三种配置，*代表允许所有，当时你也可以自定义属性（比如header只能带什么，只能是post方式等等）
////    corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.setAllowCredentials(true);
//        corsConfiguration.setMaxAge(3600L);
//        return corsConfiguration;
//    }
//
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig());
//        return new CorsFilter(source);
//    }


//过滤器（全局配置）
//@Configuration
//public class GlobalCorsConfig {
//    @Bean
//    public CorsFilter corsFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("*");
//        config.setAllowCredentials(true);
//        config.addAllowedMethod("*");
//        config.addAllowedHeader("*");
//        config.addExposedHeader("*");
//
//        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
//        configSource.registerCorsConfiguration("/**", config);
//
//        return new CorsFilter(configSource);
//    }
//}