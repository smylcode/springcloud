package com.example.utils;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 设置全局上下文，便于全局使用
 *
 * @author gouchao
 * @since 2018.12.4 10:12
 */
@Component
@Lazy(false)
@Log4j2
public class ApplicationContextRegister implements ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 设置spring上下文  *  * @param applicationContext spring上下文  * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("ApplicationContext registed-->{}", applicationContext);
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }
}
