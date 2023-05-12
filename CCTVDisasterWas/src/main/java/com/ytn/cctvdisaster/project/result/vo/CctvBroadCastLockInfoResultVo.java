package com.ytn.cctvdisaster.project.result.vo;

import lombok.Data;

/**
* CctvBroadCastLockDto
* 
* @author mattmk
* 
*/

@Data
public class CctvBroadCastLockInfoResultVo {
	
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
	 * lockYn
	 */
	private String lockYn;
	
	
	/**
	 * lockIp
	 */
	private String lockIp;
	
	/**
	 * lockYmd
	 */
	private String lockYmd;
	
	/**
	 * lockTime
	 */
	private String lockTime;
	
	/**
	 * caption_yn
	 */
	private String captionYn;
	
	/**
	 * auto_yn
	 */
	private String autoYn;
	
	/**
	 * auto_time
	 */
	private String autoTime;
}
