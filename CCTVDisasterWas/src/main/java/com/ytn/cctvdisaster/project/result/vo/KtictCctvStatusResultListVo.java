package com.ytn.cctvdisaster.project.result.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
* NaverMapLocalResearchResultListVo
* 
* @author mattmk
* 
*/

@Data
public class KtictCctvStatusResultListVo {
	
	/**
	 * cctvId
	 */
	@JsonProperty("cctv_id")
	private String cctvId;
	
	/**
	 * serviceYn
	 */
	@JsonProperty("service_yn")
	private String serviceYn;
	
	/**
	 * liveAvailableYn
	 */
	@JsonProperty("live_available_yn")
	private String liveAvailableYn;
	
	/**
	 * vodAvailableYn
	 */
	@JsonProperty("vod_available_yn")
	private String vodAvailableYn;
	
}
