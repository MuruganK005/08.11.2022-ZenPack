package com.ZenPack.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
@Getter
@Setter
public class FilterDTO {
    String key;
    String value;
    String operator;
}
