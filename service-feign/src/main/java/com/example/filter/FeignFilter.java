package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.common.dto.UserDto;
import com.example.common.utils.ReturnModel;
import com.example.common.utils.TokenUtil;
import com.example.servicefeign.WebController;
import com.example.utils.ApplicationContextRegister;
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
import java.util.Arrays;

/**
 * @author gouchao
 * @since 2018.11.5 14:29
 */
@Log4j2
public class FeignFilter implements Filter{
    private String[] excludeUrl = {"/feign/web/doLogin"};
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
        if (Arrays.asList(excludeUrl).contains(uri) || uri.contains("/js/")
                || uri.contains("/css/")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String token = httpRequest.getHeader("Authorization");
        log.info("访问接口url:" + uri);
        ReturnModel model = TokenUtil.getInstance().checkToken(token);
        if (model.getCode() != 0) {
            PrintWriter print = httpResponse.getWriter();
            print.write(model.getDes());
            return;
        }
        WebController webController = ApplicationContextRegister.getApplicationContext().getBean(WebController.class);
        UserDto user = webController.findUserById(model.getData().toString());
        if (StringUtils.isEmpty(token)) {
            PrintWriter print = httpResponse.getWriter();
            print.write(JSON.toJSONString(ReturnModel.fail("token不能为空！")));
            log.error("路径url:" + uri + ",token不能为空！");
            print.close();
            return;
        }
        servletRequest.setAttribute("user", user);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
