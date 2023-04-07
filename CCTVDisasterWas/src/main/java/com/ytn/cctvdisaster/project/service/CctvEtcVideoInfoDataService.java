package com.ytn.cctvdisaster.project.service;

/**
 * CctvVideoInfoDataService Interface
 * 
 * @author mattmk
 *
 */
public interface CctvEtcVideoInfoDataService {
	
	/**
	 * getCctvVideoThumbnailDataJson.
	 * 
	 * @author mattmk
	 * @param String inputFilePath, String imageName
	 * @return String
	 */
	public String getCctvVideoThumbnailDataJson(String inputFilePath, String imageName);
}
