package com.example.servicecommon.service;

import com.example.servicecommon.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    public Map<String, String> findByName(String loginName){
        Map<String, String> userInfo = userDao.findByName(loginName);
        return userInfo;
    }
}
