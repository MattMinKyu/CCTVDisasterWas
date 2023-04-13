package com.ytn.cctvdisaster.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
* CctvCategoryDepth3DataDto
* 
* @author mattmk
* 
*/

@Data
public class CctvCategoryDepth3DataDto {
	
	/**
	 * cctvId
	 */
	@JsonProperty("cctvId")
	private String cctv_id;
	
	/**
	 * lvl3Nm
	 */
	@JsonProperty("lvl3Nm")
	private String lvl3_nm;
	
	/**
	 * lvl2Id
	 */
	@JsonProperty("lvl2Id")
	private String lvl2_id;
	
	/**
	 * sidoNm
	 */
	@JsonProperty("sidoNm")
	private String sido_nm;
	
	/**
	 * lat
	 */
	private double lat;
	
	/**
	 * lon
	 */
	private double lon;
	
}
