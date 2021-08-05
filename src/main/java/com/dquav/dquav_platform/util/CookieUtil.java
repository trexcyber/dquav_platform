//package com.dquav.dquav_platform.util;
//
//import org.springframework.http.HttpCookie;
//import org.springframework.http.ResponseCookie;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @author TrEx
// * @date 2021/7/19 - 15:37
// */
//public class CookieUtil {
//    private final static String DOMAIN = "localhost";
//    public static HttpCookie generateSetCookie(HttpServletRequest request, String name, String value, Duration duration){
//        ResponseCookie cookie = ResponseCookie.from(name, value) // key & value
//                .secure(true)		// 在https下传输,配合none使用
//                .domain(DOMAIN)// 域名
//                .path("*")			// path
//                .maxAge(duration)	// 过期时间
//                .sameSite("Lax")
//                .build()
//                ;
//        return cookie;
//    }
//}
