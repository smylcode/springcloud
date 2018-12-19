package com.example.servicecommon.service;

import com.example.common.dto.UserDto;
import com.example.servicecommon.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gouchao
 * @since 2018.7.19 10:36
 */
@Service
public class CommonUserService {
    @Autowired
    private UserDao userDao;

    public Integer getCount(){
        return userDao.countUser();
    }

    public UserDto findByName(String loginName){
        UserDto userInfo = userDao.findByName(loginName);
        return userInfo;
    }

    public UserDto findById(String id){
        UserDto userInfo = userDao.findById(id);
        return userInfo;
    }
}
