package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.utils.ReturnModel;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author gouchao
 * @since 2018.11.5 14:29
 */
@Log4j2
public class FeignFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filterConfig.getServletContext(): " + filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setCharacterEncoding("UTF-8");
        String uri = httpRequest.getRequestURI();
        String token = httpRequest.getParameter("token");
        token = "1";
        if (StringUtils.isEmpty(token) && !uri.contains("/favicon.ico")) {
            PrintWriter print = httpResponse.getWriter();
            print.write(JSON.toJSONString(ReturnModel.fail("token不能为空！")));
            log.error("路径url:" + uri + ",token不能为空！");
            print.close();
            return;
        }
        servletRequest.setAttribute("name", "李四！");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
