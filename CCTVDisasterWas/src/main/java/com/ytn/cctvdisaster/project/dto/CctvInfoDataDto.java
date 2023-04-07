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
	 * srcId
	 */
	@JsonProperty("srcId")
	private String src_id;
	
	/**
	 * srcNm
	 */
	@JsonProperty("srcNm")
	private String src_nm;
	
	/**
	 * sidoNm
	 */
	@JsonProperty("sidoNm")
	private String sido_nm;
	
	/**
	 * cctvNm
	 */
	@JsonProperty("cctvNm")
	private String cctv_nm;
	
	/**
	 * lat
	 */
	private double lat;
	
	/**
	 * lon
	 */
	private double lon;
	
	/**
	 * url
	 */
	@JsonIgnore
	private String url;
}
