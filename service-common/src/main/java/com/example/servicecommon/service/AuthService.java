package com.example.servicecommon.service;

import com.alibaba.fastjson.JSON;
import com.example.common.dto.AuthLeftDto;
import com.example.common.dto.AuthTopDto;
import com.example.common.dto.UserDto;
import com.example.servicecommon.dao.AuthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gouchao
 * @since 2018.12.3 11:11
 */
@Service("comonAuthService")
public class AuthService {
    @Autowired
    AuthDao authDao;

    public List<AuthTopDto> authTopList(UserDto user) {
        if ("admin".equals(user.getName())) {
            return authDao.authTopList();
        }
        return new ArrayList<>();
    }

    public List<AuthLeftDto> authLeftList(UserDto user, String topId) {
        List<AuthLeftDto> list = new ArrayList<>();
        if ("admin".equals(user.getName())) {
            list = authDao.authLeftist(topId);
        }
        if (!CollectionUtils.isEmpty(list)) {
            List<AuthLeftDto> oneList = new ArrayList<>();
            list.forEach(e -> {
                if (null == e.getParentId()) {
                    oneList.add(e);
                }
            });
            list.removeIf(e -> null == e.getParentId());
            getLeftMenuTree(list, oneList);
            System.out.println("得到的树形目录：" + JSON.toJSONString(oneList));
            return oneList;
        }
        return list;
    }

    public List<AuthLeftDto> getLeftMenuTree(
            List<AuthLeftDto> allList, List<AuthLeftDto> parentList){
        for (AuthLeftDto parent : parentList) {
            List<AuthLeftDto> childList = new ArrayList<>();
            for (AuthLeftDto child : allList) {
                if (parent.getId() == child.getParentId()) {
                    childList.add(child);
                }
            }
            if (childList.size() > 0) {
                parent.setHasChild(true);
                parent.setChildList(childList);
                allList.removeIf(e -> childList.contains(e));
                getLeftMenuTree(allList, childList);
            }
        }
        return parentList;
    }
}
