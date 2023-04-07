package com.ytn.cctvdisaster.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
* CctvMapResearchDto
* 
* @author mattmk
* 
*/

@Data
public class CctvCategoryDepth1DataDto {
	
	/**
	 * srcId
	 */
	@JsonProperty("srcId")
	private String src_id;
	
	/**
	 * srcNm
	 */
	@JsonProperty("srcNm")
	private String src_nm;
	
}
