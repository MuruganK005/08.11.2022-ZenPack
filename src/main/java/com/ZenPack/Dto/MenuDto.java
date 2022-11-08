package com.ZenPack.Dto;

import com.ZenPack.model.FeaturedList;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MenuDto {
    private String createdTime;
    private String createdBy;
    private Integer parentMenuId;
    private Integer id;
    private Integer parent;
    private String featureId;
    private String text;
    private String icon;
    private Boolean isSettingMenu;
    private Boolean droppable;
    private String featureUrl;
}
