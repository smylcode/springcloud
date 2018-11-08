package com.example.servicefeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author gouchao
 * @since 2018.7.19 15:32
 */
@FeignClient(value = "service-common")
public interface UserService {
    @RequestMapping(value = "/user/count", method = RequestMethod.GET)
    String countUser();
}
