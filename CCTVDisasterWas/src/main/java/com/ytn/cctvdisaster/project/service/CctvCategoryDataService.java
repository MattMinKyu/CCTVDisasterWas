package com.ytn.cctvdisaster.project.service;

/**
 * CctvCategoryDataService Interface
 * 
 * @author mattmk
 *
 */
public interface CctvCategoryDataService {
	
	/**
	 * getDepth1ListDataJson
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	public String getDepth1ListDataJson();
	
	/**
	 * getDepth2ListDataJson
	 * 
	 * @author mattmk
	 * @param String srcId
	 * @return String
	 */
	public String getDepth2ListDataJson(String srcId);
	
	/**
	 * getCctvCategorySearchListDataJson
	 * 
	 * @author mattmk
	 * @param String cctvSearchKeyword
	 * @return String
	 */
	public String getCctvCategorySearchListDataJson(String cctvSearchKeyword);
}
