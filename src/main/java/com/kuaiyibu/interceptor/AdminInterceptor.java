package com.kuaiyibu.interceptor;

import com.kuaiyibu.pojo.Admin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin==null){
            //空就是没有登陆或者登陆失败
            response.sendRedirect(request.getContextPath()+"/adLogin.html");
            return false;
        }
        String uri = request.getRequestURI();
        String[] str = uri.split("/");
        try{
            //放行可以访问其他页面
            if(str[3].indexOf(".") == -1){
                if(str[3].equals(admin.getAuthority()+"s")){
                    return true;
                }else if((admin.getAuthority()+"s").equals("seats") && str[3].equals("tickets") && "GET".equals(request.getMethod())){
                    //允许座位管理所需获取机票信息
                    return true;
                }else if("supper".equals(admin.getAuthority())){
                    return true;
                }
            }else {
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            response.sendRedirect(request.getContextPath()+"/adLogin.html");
            return false;
        }

    }

    /*public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }*/
}
