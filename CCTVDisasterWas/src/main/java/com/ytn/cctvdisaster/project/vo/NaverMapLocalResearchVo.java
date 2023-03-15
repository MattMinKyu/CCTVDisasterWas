package com.ytn.cctvdisaster.project.vo;

import lombok.Data;

/**
* NaverMapLocalResearchVo
* 
* @author mattmk
* 
*/

@Data
public class NaverMapLocalResearchVo {
	
	/**
	 * query
	 */
	private String query = "ytn";
	
	/**
	 * display
	 */
	private Integer display = 10;
	
	/**
	 * start
	 */
	private Integer start = 1;
	
	/**
	 * sort
	 */
	private String sort = "random";
	
}
