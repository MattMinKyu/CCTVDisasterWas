package com.ytn.cctvdisaster.project.result.vo;

import java.util.List;

import lombok.Data;

/**
* NaverMapLocalResearchResultVo
* 
* @author mattmk
* 
*/

@Data
public class NaverMapLocalResearchResultVo {
	
	/**
	 * ���� �ڵ�
	 */
	private Integer status;
	
	/**
	 * lastBuildDate
	 */
	private String lastBuildDate;
	
	/**
	 * total
	 */
	private Integer total;
	
	/**
	 * start
	 */
	private Integer start;
	
	/**
	 * display
	 */
	private Integer display;
	
	/**
	 * display
	 */
	private List<NaverMapLocalResearchResultListVo> items;
}
