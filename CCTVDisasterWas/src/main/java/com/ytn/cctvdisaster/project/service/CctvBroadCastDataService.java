package com.ytn.cctvdisaster.project.service;

/**
 * CctvBroadCastDataService Interface
 * 
 * @author mattmk
 *
 */
public interface CctvBroadCastDataService {
	
	/**
	 * getCctvBroadCastLockInfoDataJson
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	public String getCctvBroadCastLockInfoDataJson();
	
	/**
	 * modifyCctvPlayListLockDataJson
	 * 
	 * @author mattmk
	 * @param String lockYn, String plistId, String remoteIp
	 * @return String
	 */
	public int modifyCctvBroadCastLockDataJson(String lockYn, String plistId, String remoteIp);
	
}
