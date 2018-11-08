package com.example.servicecommon.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gouchao
 * @since 2018.7.20 10:36
 */
@Configuration
@ConditionalOnProperty(name="druid.stat.enabled", havingValue="true", matchIfMissing=true)
public class DruidStatConfig {

    /**
     * 注册DruidFilter拦截
     */
    @Bean
    public FilterRegistrationBean duridFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        bean.addInitParameter("exclusions", "*.css,*.js,*.png,"
                + "*.jpg,*.gif,*.jpeg,*.bmp,*.ico,*.swf,*.psd,*.htc,*.htm,*.html,"
                + "*.crx,*.xpi,*.exe,*.ipa,*.apk,*.otf,*.eot,*.svg,*.ttf,*.woff,"
                + "/druid/*");
        bean.addUrlPatterns("/*");
        return bean;
    }

    /**
     * 注册DruidServlet
     */
    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean();
        bean.setServlet(new StatViewServlet());
        bean.addUrlMappings("/druid/*");
        return bean;
    }
}
