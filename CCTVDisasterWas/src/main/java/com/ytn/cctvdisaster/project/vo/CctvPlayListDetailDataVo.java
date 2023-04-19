package com.ytn.cctvdisaster.project.vo;

import java.util.List;

import lombok.Data;

/**
* CctvPlayListDetailDataVo
* 
* @author mattmk
* 
*/

@Data
public class CctvPlayListDetailDataVo {
	
	/**
	 * plistId
	 */
	private String plistId;
	
	/**
	 * cctvPlayListDetailDataListVo
	 */
	private List<CctvPlayListDetailDataListVo> items;
		
}
