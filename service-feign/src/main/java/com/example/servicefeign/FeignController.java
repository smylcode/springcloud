package com.example.servicefeign;

import com.example.utils.ReturnModel;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author gouchao
 * @since 2018.7.17 15:05
 */
@RestController
@RequestMapping("/feign")
@Log4j2
public class FeignController {
    @Autowired
    FeignService feignService;
    @Autowired
    UserService userService;
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return feignService.sayHiFromClientOne(name);
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public ReturnModel count(HttpServletRequest request){
        System.out.println("name:" + request.getAttribute("name"));
        String countInfo = userService.countUser();log.info(new Date().toString() + request.getAttribute("name"));
        return ReturnModel.success("获取统计信息成功！", countInfo);
    }

}
