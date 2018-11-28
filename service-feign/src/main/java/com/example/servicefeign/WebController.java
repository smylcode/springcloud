package com.example.servicefeign;

import com.example.utils.Md5util;
import com.example.utils.ReturnModel;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author gouchao
 * @since 2018.11.21 9:37
 */
@RestController
@Log4j2
@RequestMapping("/feign/web")
public class WebController {
    @Autowired
    private UserService userService;

    /**
     * 跳转到登录界面
     *
     * @author gouchao
     * @since 2018.11.27 14:45
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void index(HttpServletResponse response) {
        try {
            response.sendRedirect("/login.html");
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * 进入到主页
     *
     * @author gouchao
     * @since 2018.11.21 10:05
     */
    @RequestMapping(value = "goIndex", method = RequestMethod.GET)
    public void goIndex(HttpServletResponse response) {
        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * 进入测试页
     *
     * @author gouchao
     * @since 2018.11.21 10:05
     */
    @RequestMapping(value = "/goTest", method = RequestMethod.GET)
    public void goTest(HttpServletResponse response) {
        try {
            response.sendRedirect("/page/test");
        } catch (IOException e) {
            log.error(e);
        }
    }

    /**
     * 登录操作
     *
     * @author gouchao
     * @since 2018.11.27 15:44
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ReturnModel doLogin(String loginName, String password) {
        try {
            if (StringUtils.isEmpty(loginName)) {
                return ReturnModel.fail("登录名不能为空！");
            }
            if (StringUtils.isEmpty(password)) {
                return ReturnModel.fail("登录密码不能为空！");
            }
            Map<String, String> map = userService.findByName(loginName);
            if (map == null) {
                return ReturnModel.fail("用户名不存在！");
            }
            String tempPassword = map.get("password");
            password = Md5util.encryptByMD5(password);
            if (!password.equals(tempPassword)) {
                return ReturnModel.fail("密码错误！");
            }
            log.info("userInfo:" + map);
        } catch (Exception e) {
            log.error(e);
            return ReturnModel.fail("发生未知错误！");
        }
        return ReturnModel.success();
    }
}
