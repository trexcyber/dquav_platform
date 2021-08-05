package com.dquav.dquav_platform.Filter;

import com.sun.deploy.net.HttpUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author TrEx
 * @date 2021/7/23 - 13:39
 */
@Component
public class CorsFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //*号表示对所有请求都允许跨域访问
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.addHeader("Access-Control-Allow-Credentials", "true");
        res.addHeader("Access-Control-Allow-Origin", "http://127.0.0.1:8848");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        res.addHeader("Access-Control-Allow-Headers", "Content-Type,Access-Control-Allow-Headers,X-CAF-Authorization-Token,sessionToken,X-TOKEN,Authorization,X-Requested-With");
        res.setHeader("Set-Cookie","JSESSIONID=xxx;SameSite=None;Secure");
        if (((HttpServletRequest) servletRequest).getMethod().equals("OPTIONS")) {
            servletResponse.getWriter().println("Success");

            return;
        }

        System.out.println("设置跨域请求");
        filterChain.doFilter(servletRequest, servletResponse);
    }


    @Override
    public void destroy() {

    }
}
