package com.ytn.cctvdisaster.project.service;

import java.util.HashMap;

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
	 * @param String inputFilePath
	 * @return HashMap<String, Object>
	 */
	public HashMap<String, Object> getCctvVideoThumbnailDataJson(String inputFilePath);
}
