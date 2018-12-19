package com.example.servicecommon.dao;

import com.example.common.dto.AuthLeftDto;
import com.example.common.dto.AuthTopDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author gouchao
 * @since 2018.7.19 10:05
 */
@Mapper
public interface AuthDao {
    List<AuthTopDto> authTopList();

    List<AuthLeftDto> authLeftist(String topId);
}
