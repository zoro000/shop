package filter;

import pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpReq = (HttpServletRequest) req;
        HttpServletResponse httpRes = (HttpServletResponse) resp;
        HttpSession httpSession = httpReq.getSession();
        String path = httpReq.getRequestURL().toString();    //当前请求相对url
        String loginUrl = httpReq.getContextPath() + "http://localhost:8080/home/login.html";    //1.登录界面url
        String initUrl = httpReq.getContextPath() + "http://localhost:8080/home/register.html";    //2.初始化界面url
        String userName = (String) httpSession.getAttribute("username");    //在session中获取当前用户名
        // session 获取用户信息
        String name = ((User) httpSession.getAttribute("loginer")).getName();

        // css js
        //获取请求界面的路径
        String a=httpReq.getRequestURI();
        if(a.contains(".css") || a.contains(".js") || a.contains(".png")|| a.contains(".jpg")){
            //如果发现是css或者js文件，直接放行
            chain.doFilter(req, resp);
        }


        // 登陆页面、初始化页面不过滤
        if (loginUrl.equals(path) || initUrl.equals(path) ) {
            chain.doFilter(req, resp);
            return;
    }
        if (name == null) {
            httpRes.sendRedirect(loginUrl);
            return;
        } else {
            chain.doFilter(req, resp);
            return;
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
