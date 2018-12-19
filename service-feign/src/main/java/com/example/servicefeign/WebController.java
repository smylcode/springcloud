package com.example.servicefeign;

import com.alibaba.fastjson.JSON;
import com.example.common.dto.UserDto;
import com.example.common.utils.Constant;
import com.example.common.utils.Md5util;
import com.example.common.utils.ReturnModel;
import com.example.common.utils.TokenUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
    @Autowired
    private AuthService authService;

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
     * 登录操作
     *
     * @author gouchao
     * @since 2018.11.27 15:44
     */
    @RequestMapping(value = "/doLogin", method = RequestMethod.POST)
    public ReturnModel doLogin(String loginName, String password) {
        Map<String, Object> data = new HashMap<>();
        try {
            if (StringUtils.isEmpty(loginName)) {
                return ReturnModel.fail("登录名不能为空！");
            }
            if (StringUtils.isEmpty(password)) {
                return ReturnModel.fail("登录密码不能为空！");
            }
            UserDto userDto = userService.findByName(loginName);
            if (userDto == null) {
                return ReturnModel.fail("用户名不存在！");
            }
            String tempPassword = userDto.getPassword();
            password = Md5util.encryptByMD5(password);
            if (!password.equals(tempPassword)) {
                return ReturnModel.fail("密码错误！");
            }
            data.put("user", userDto);
            data.put("token", TokenUtil.getInstance().getToken(userDto.getId() + "", Constant.token_expire));
            data.put("expiry", System.currentTimeMillis() + Constant.token_expire - 1000 * 2);
            log.info("userInfo:" + JSON.toJSONString(userDto));
            return ReturnModel.success(data);
        } catch (Exception e) {
            log.error(e);
            return ReturnModel.fail("发生未知错误！");
        }
    }

    /**
     * 获取顶部菜单
     *
     * @author gouchao
     * @since 2018.12.3 16:07
     */
    @RequestMapping(value = "/getTopMenu", method = RequestMethod.GET)
    public ReturnModel getTopMenu(HttpServletRequest request) {
        try {
            UserDto user = (UserDto) request.getAttribute("user");
            return ReturnModel.success(authService.getHeadAuthByUser(user));
        } catch (Exception e) {
            log.error(e);
            return ReturnModel.fail("发生未知错误！");
        }
    }

    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    public UserDto findUserById(String id) {
        return userService.findById(id);
    }

    /**
     * 获取左侧菜单
     *
     * @author gouchao
     * @since 2018.12.3 16:07
     */
    @RequestMapping(value = "/getLeftMenu", method = RequestMethod.GET)
    public ReturnModel getLeftMenu(HttpServletRequest request, String topId) {
        try {
            UserDto user = (UserDto) request.getAttribute("user");
            return ReturnModel.success(authService.getLeftAuthByUser(user, topId));
        } catch (Exception e) {
            log.error(e);
            return ReturnModel.fail("发生未知错误！");
        }
    }
}
