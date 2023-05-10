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
	 * @param String remoteIp
	 * @return String
	 */
	public String getCctvBroadCastLockInfoDataJson(String remoteIp);
	
	/**
	 * modifyCctvPlayListLockDataJson
	 * 
	 * @author mattmk
	 * @param String lockYn, String plistId, String remoteIp
	 * @return String
	 */
	public int modifyCctvBroadCastLockDataJson(String lockYn, String plistId, String remoteIp);
	
}
