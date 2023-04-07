package com.ytn.cctvdisaster.project.result.vo;

import lombok.Data;

/**
* CctvCategorySearchResultVo
* 
* @author mattmk
* 
*/

@Data
public class CctvCategorySearchResultVo {
	
	/**
	 * cctvId
	 */
	private String cctvId;
	
	/**
	 * cctvNm
	 */
	private String cctvNm;
	
	/**
	 * srcNm
	 */
	private String srcNm;
	
	/**
	 * srcGb
	 */
	private String srcGb;
	
	/**
	 * sidoNm
	 */
	private String sidoNm;
	
	
	/**
	 * lat
	 */
	private double lat;
	
	/**
	 * lon
	 */
	private double lon;
	
}
