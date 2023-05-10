package com.ytn.cctvdisaster.project.dto;

import lombok.Data;

/**
* CctvBroadCastLockDto
* 
* @author mattmk
* 
*/

@Data
public class CctvBroadCastLockDto {
	
	/**
	 * plist_id
	 */
	private String plist_id;
	
	/**
	 * user_nm
	 */
	private String user_nm;
	
	/**
	 * plist_nm
	 */
	private String plist_nm;
	
	/**
	 * lock_yn
	 */
	private String lock_yn;
	
	/**
	 * lock_ip
	 */
	private String lock_ip;
	
	/**
	 * lock_ymd
	 */
	private String lock_ymd;
	
	/**
	 * lock_time
	 */
	private String lock_time;
	
	/**
	 * caption_yn
	 */
	private String caption_yn;
	
	/**
	 * auto_yn
	 */
	private String auto_yn;
	
	/**
	 * auto_time
	 */
	private String auto_time;
}
