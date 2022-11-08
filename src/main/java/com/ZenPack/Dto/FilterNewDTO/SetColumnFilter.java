package com.ZenPack.Dto.FilterNewDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SetColumnFilter extends ColumnFilter{
    private List<String> values;
}
