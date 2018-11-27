package com.example.servicecommon.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * @author gouchao
 * @since 2018.7.19 10:05
 */
@Mapper
public interface UserDao {
    int countUser();

    Map<String, String> findByName(String userName);
}
