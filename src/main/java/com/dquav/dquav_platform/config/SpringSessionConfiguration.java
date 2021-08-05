package com.dquav.dquav_platform.config;

import org.springframework.boot.autoconfigure.session.DefaultCookieSerializerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author TrEx
 * @date 2021/7/19 - 15:24
 */
@Configuration
public class SpringSessionConfiguration {

    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
        defaultCookieSerializer.setCookiePath("Set-Cookie");
        defaultCookieSerializer.setUseHttpOnlyCookie(false);
        defaultCookieSerializer.setSameSite(null);
        return defaultCookieSerializer;
    }
}

