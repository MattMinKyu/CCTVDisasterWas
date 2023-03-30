package com.ytn.cctvdisaster.project.result.vo;

import lombok.Data;

/**
* CctvMapResearchResultVo
* 
* @author mattmk
* 
*/

@Data
public class CctvMapResearchResultVo {
	
	/**
	 * cctvId
	 */
	private String cctvId;
	
	/**
	 * streamingUrl
	 */
	private String streamingUrl;
	
	/**
	 * thumbnailUrl
	 */
	private String thumbnailUrl;
	
	/**
	 * serviceYn
	 */
	private String serviceYn;
	
	/**
	 * liveAvailableYn
	 */
	private String liveAvailableYn;
	
}
