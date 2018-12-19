package com.example.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author gouchao
 * @since 2018.12.3 16:11
 */
@Getter
@Setter
public class AuthLeftDto {
    private int id;
    private String name;
    private String topId;
    private String icon;
    private String url;
    private Integer parentId;
    private boolean hasChild;
    private List<AuthLeftDto> childList;
}
