package com.ytn.cctvdisaster.project.vo;

import lombok.Data;

/**
* CctvPlayListDataVo
* 
* @author mattmk
* 
*/

@Data
public class CctvPlayListDataVo {
	
	/**
	 * plistId
	 */
	private String plistId;
	
	/**
	 * userNm
	 */
	private String userNm;
	
	/**
	 * plistNm
	 */
	private String plistNm;
	
	/**
	 * captionYn
	 */
	private String captionYn;
	
	/**
	 * autoYn
	 */
	private String autoYn;
	
	/**
	 * autoTime
	 */
	private int autoTime;
	
}
