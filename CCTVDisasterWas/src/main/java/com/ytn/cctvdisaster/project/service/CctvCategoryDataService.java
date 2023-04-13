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
	 * @param String cctvSearchKeyword
	 * @return String
	 */
	public String getDepth1ListDataJson(String cctvSearchKeyword);
	
	/**
	 * getDepth2ListDataJson
	 * 
	 * @author mattmk
	 * @param String categoryId, String cctvSearchKeyword
	 * @return String
	 */
	public String getDepth2ListDataJson(String categoryId, String cctvSearchKeyword);
	
	/**
	 * getDepth3ListDataJson
	 * 
	 * @author mattmk
	 * @param String categoryId, String callCategory, String cctvSearchKeyword
	 * @return String
	 */
	public String getDepth3ListDataJson(String categoryId, String callCategory, String cctvSearchKeyword);
	
}
