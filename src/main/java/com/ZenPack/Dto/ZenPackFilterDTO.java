package com.ZenPack.Dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ZenPackFilterDTO {

    private String createdDate;
    private String updatedTime;
    private String field;
    private String sortType;
    private Integer page;
    private Integer size;
    private List<FilterDTO> filterDTOList;

}