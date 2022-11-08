package com.ZenPack.Dto.filterRequestDTO;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ColumnVO implements Serializable {
    private String id;
    private String displayName;
    private String field;
    private String aggFunc;
}
