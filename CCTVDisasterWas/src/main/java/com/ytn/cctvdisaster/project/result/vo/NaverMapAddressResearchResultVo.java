package com.ytn.cctvdisaster.project.result.vo;

import java.util.List;

import lombok.Data;

/**
* NaverMapAddressResearchResultVo
* 
* @author mattmk
* 
*/

@Data
public class NaverMapAddressResearchResultVo {
	
	/**
	 * status
	 */
	private String status;
	
	/**
	 * errorMessage
	 */
	private String errorMessage;
	
	/**
	 * addresses
	 */
	private List<NaverMapAddressResearchResultListVo> addresses;
}
