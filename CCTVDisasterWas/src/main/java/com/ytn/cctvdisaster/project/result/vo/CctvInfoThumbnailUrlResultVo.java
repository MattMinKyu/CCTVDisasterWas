package com.ytn.cctvdisaster.project.result.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
* CctvMapResearchResultVo
* 
* @author mattmk
* 
*/

@Data
public class CctvInfoThumbnailUrlResultVo {
	
	/**
	 * cctvId
	 */
	private String cctvId;
	
	
	/**
	 * srcGb
	 */
	private String srcGb;
	
	/**
	 * srcNm
	 */
	private String srcNm;
	
	/**
	 * sidoNm
	 */
	private String sidoNm;
	
	/**
	 * cctvNm
	 */
	private String cctvNm;
	
	/**
	 * lat
	 */
	private double lat;
	
	/**
	 * lon
	 */
	private double lon;
	
	/**
	 * thumbnailUrl
	 */
	private String thumbnailUrl;
	
	/**
	 * serviceYn
	 */
	private String serviceYn;
	
	/**
	 * streamingUrl
	 */
	@JsonIgnore
	private String streamingUrl;
}
