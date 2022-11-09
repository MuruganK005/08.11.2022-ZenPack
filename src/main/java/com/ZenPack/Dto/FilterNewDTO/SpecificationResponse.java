package com.ZenPack.Dto.FilterNewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecificationResponse {
    private List<Object> data;
    private int lastRow;
}
