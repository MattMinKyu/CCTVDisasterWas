package com.ytn.cctvdisaster.project.result.vo;

import java.util.List;

import lombok.Data;

/**
* CctvCategorySearchResultListVo
* 
* @author mattmk
* 
*/

@Data
public class CctvCategorySearchResultListVo {
	
	/**
	 * categoryDepth1
	 */
	private String categoryDepth1;
	
	/**
	 * srcNm
	 */
	private String srcNm;
	
	/**
	 * CctvCategorySearchResultVo
	 */
	private List<CctvCategorySearchResultVo> items;
	
}
