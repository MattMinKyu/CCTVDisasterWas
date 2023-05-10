package com.ytn.cctvdisaster.project.result.vo;

import lombok.Data;

/**
* RabbitMqBroadCastLockDataRequestVo
* 
* @author mattmk
* 
*/

@Data
public class RabbitMqBroadCastLockDataRequestVo {
	
	/**
	 * plistId
	 */
	private String plistId;
	
	/**
	 * lockYn
	 */
	private String lockYn;
	
}
