package com.example.servicecommon.controller;

import com.example.common.dto.AuthLeftDto;
import com.example.common.dto.AuthTopDto;
import com.example.common.dto.UserDto;
import com.example.servicecommon.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author gouchao
 * @since 2018.12.3 9:51
 */
@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @RequestMapping(value = "getHeadAuthByUser", method = RequestMethod.POST)
    public List<AuthTopDto> getHeadAuthByUser(@RequestBody UserDto user) {
        return authService.authTopList(user);
    }

    @RequestMapping(value = "getLeftAuthByUser", method = RequestMethod.POST)
    public List<AuthLeftDto> getLeftAuthByUser(
            @RequestBody UserDto user, @RequestParam String topId) {
        return authService.authLeftList(user, topId);
    }
}
