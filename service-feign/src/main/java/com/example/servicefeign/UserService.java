package com.example.servicefeign;

import com.example.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gouchao
 * @since 2018.7.19 15:32
 */
@FeignClient(value = "service-common")
@Service
public interface UserService {
    @RequestMapping(value = "/user/count", method = RequestMethod.GET)
    String countUser();

    @RequestMapping(value = "/user/findByName", method = RequestMethod.POST)
    UserDto findByName(@RequestParam("loginName") String loginName);

    @RequestMapping(value = "/user/findById", method = RequestMethod.POST)
    UserDto findById(@RequestParam("id") String id);
}
