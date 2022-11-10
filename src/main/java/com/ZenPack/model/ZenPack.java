package com.ZenPack.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.ZenPack.Dto.FeatureDto;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.ZenPack.Dto.MenuDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "zen_pack")
@JsonInclude(JsonInclude.Include.NON_NULL)
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class) 
public class ZenPack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "zen_pack_id")
    private Long zenPackId;

    @Column(name = "zen_pack_name")
    private String name;
    
    @Type(type = "jsonb") // See (2)
    @Column(name = "json_data", columnDefinition = "jsonb")
    private List<MenuDto> menus;

    @Type(type = "jsonb") // See (2)
    @Column(name = "json_data1", columnDefinition = "jsonb")
    private List<FeatureDto> features;

    @Column(name = "created_date")
    private String createdDate;

    @Column(name = "updated_time")
    private String updatedTime;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_by")
    private String updatedBy;

    @Column(name = "in_active")
    private Boolean inActive=false;

}
