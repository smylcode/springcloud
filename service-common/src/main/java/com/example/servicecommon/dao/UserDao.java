package com.example.servicecommon.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author gouchao
 * @since 2018.7.19 10:05
 */
@Mapper
public interface UserDao {
    int countUser();
}
