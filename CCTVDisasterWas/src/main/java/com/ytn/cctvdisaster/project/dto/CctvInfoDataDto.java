package com.ytn.cctvdisaster.project.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
* CctvMapResearchDto
* 
* @author mattmk
* 
*/

@Data
public class CctvInfoDataDto {
	
	/**
	 * cctvId
	 */
	@JsonProperty("cctvId")
	private String cctv_id;
	
	/**
	 * srcGb
	 */
	@JsonProperty("srcGb")
	private String src_gb;
	
	/**
	 * cctvNm
	 */
	@JsonProperty("cctvNm")
	private String lvl3_nm;
	
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
	
	/**
	 * srcNm
	 */
	@JsonProperty("srcNm")
	private String lvl1_nm;
	
	/**
	 * url
	 */
	@JsonIgnore
	private String url;
}
