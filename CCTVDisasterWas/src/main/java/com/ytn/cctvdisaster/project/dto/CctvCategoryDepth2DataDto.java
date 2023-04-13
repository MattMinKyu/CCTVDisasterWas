package com.ytn.cctvdisaster.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
* CctvCategoryDepth2DataDto
* 
* @author mattmk
* 
*/

@Data
public class CctvCategoryDepth2DataDto {
	
	/**
	 * lvl2Id
	 */
	@JsonProperty("lvl2Id")
	private String lvl2_id;
	
	/**
	 * lvl2Nm
	 */
	@JsonProperty("lvl2Nm")
	private String lvl2_nm;
	
}
