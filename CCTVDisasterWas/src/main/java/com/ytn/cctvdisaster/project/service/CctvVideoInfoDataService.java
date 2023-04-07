package com.ytn.cctvdisaster.project.service;

import java.util.List;

/**
 * CctvVideoInfoDataService Interface
 * 
 * @author mattmk
 *
 */
public interface CctvVideoInfoDataService {
	
	/**
	 * getCctvStreamingUrlMapDataJson
	 * 
	 * @author mattmk
	 * @param List<String> cctvIdList
	 * @return String
	 */
	public String getCctvStreamingUrlMapDataJson(List<String> cctvIdList);
	
	/**
	 * getCctvThumbnailUrlMapDataJson
	 * 
	 * @author mattmk
	 * @param List<String> cctvIdList
	 * @return String
	 */
	public String getCctvThumbnailUrlMapDataJson(List<String> cctvIdList);
	
}
