package com.ZenPack.Dto;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationDto {
    private String key;
    private String operation;
    private String value;
    private List<?> vaLues;
}
