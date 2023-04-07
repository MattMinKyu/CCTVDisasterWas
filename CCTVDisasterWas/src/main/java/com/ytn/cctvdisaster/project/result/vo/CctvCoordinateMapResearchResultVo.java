package com.ytn.cctvdisaster.project.result.vo;

import lombok.Data;

/**
* CctvMapResearchResultVo
* 
* @author mattmk
* 
*/

@Data
public class CctvCoordinateMapResearchResultVo {
	
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
	
}
