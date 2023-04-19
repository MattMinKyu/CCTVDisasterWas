package com.ytn.cctvdisaster.project.result.vo;

import lombok.Data;

/**
* RabbitMqResponseDataVo
* 
* @author mattmk
* 
*/

@Data
public class RabbitMqResponseDataVo {
	
	/**
	 * cctvId
	 */
	private String cctvId;
	
	/**
	 * lockYn
	 */
	private String lockYn;
	
}
