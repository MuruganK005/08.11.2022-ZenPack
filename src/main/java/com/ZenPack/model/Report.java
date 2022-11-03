package com.ZenPack.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ZenPack.Dto.FeatureDto;
import com.ZenPack.Dto.MenuDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "report")
public class Report{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "report_id")
	private long id;
	@Column(name = "feature")
	private String feature;
	@Column(name = "category")
	private String category;
	@Column(name = "os_type")
	private String osType;
	@Column(name = "discovery_type")
	private String discoveryType;
	@Column(name = "analytics_by")
	private String analyticsBy;

}
