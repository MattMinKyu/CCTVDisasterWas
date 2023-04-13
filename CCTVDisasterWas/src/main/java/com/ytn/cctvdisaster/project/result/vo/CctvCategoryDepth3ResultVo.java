package com.ytn.cctvdisaster.project.result.vo;

import lombok.Data;

/** 
 * CctvCategoryDepth3ResultVo
 * 
* @author mattmk
* 
*/

@Data
public class CctvCategoryDepth3ResultVo {
	
	/**
	 * id
	 */
	private String id;
	
	/**
	 * name
	 */
	private String name;
	
	/**
	 * sidoNm
	 */
	private String sidoNm;
	
	/**
	 * hasItems
	 */
	private Boolean hasItems;
	
	/**
	 * groupId
	 */
	private String groupId;
	
	
	/**
	 * lat
	 */
	private double lat;
	
	/**
	 * lon
	 */
	private double lon;
	
}
