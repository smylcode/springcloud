package com.example.servicecommon.controller;

import com.example.servicecommon.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author gouchao
 * @since 2018.7.19 10:49
 */
@RestController
@RequestMapping("/user")
public class RegisterController {
    @Autowired
    private CommonUserService userService;
    @RequestMapping(value = "count", method = RequestMethod.GET)
    public String getCount(){
        return "用户总数为：" + userService.getCount();
    }

    @RequestMapping(value = "findByName", method = RequestMethod.POST)
    public Map<String, String> findByName(@RequestParam String loginName){
        return userService.findByName(loginName);
    }
}
