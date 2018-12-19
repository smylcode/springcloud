package com.example.servicefeign;

import com.example.common.dto.AuthLeftDto;
import com.example.common.dto.AuthTopDto;
import com.example.common.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author gouchao
 * @since 2018.7.19 15:32
 */
@FeignClient(value = "service-common")
@Service
public interface AuthService {
    @RequestMapping(value = "/auth/getHeadAuthByUser", method = RequestMethod.POST)
    List<AuthTopDto> getHeadAuthByUser(@RequestBody UserDto user);

    @RequestMapping(value = "/auth/getLeftAuthByUser", method = RequestMethod.POST)
    List<AuthLeftDto> getLeftAuthByUser(
            @RequestBody UserDto user, @RequestParam("topId") String topId);
}
