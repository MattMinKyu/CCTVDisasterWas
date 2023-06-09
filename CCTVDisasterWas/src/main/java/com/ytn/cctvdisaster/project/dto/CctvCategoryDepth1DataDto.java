package com.ytn.cctvdisaster.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
* CctvCategoryDepth1DataDto
* 
* @author mattmk
* 
*/

@Data
public class CctvCategoryDepth1DataDto {
	
	/**
	 * lvl1Id
	 */
	@JsonProperty("lvl1Id")
	private String lvl1_id;
	
	/**
	 * lvl1Nm
	 */
	@JsonProperty("lvl1Nm")
	private String lvl1_nm;
	
}
