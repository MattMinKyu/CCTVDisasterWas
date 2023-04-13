package com.ytn.cctvdisaster.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
* PlistMstDto
* 
* @author mattmk
* 
*/

@Data
public class PlistMstDto {
	
	/**
	 * plistId
	 */
	@JsonProperty("plistId")
	private String plist_id;
	
	/**
	 * userNm
	 */
	@JsonProperty("userNm")
	private String user_nm;
	
	/**
	 * plistNm
	 */
	@JsonProperty("plistNm")
	private String plist_nm;
	
	/**
	 * captionYn
	 */
	@JsonProperty("captionYn")
	private String caption_yn;
	
	/**
	 * autoYn
	 */
	@JsonProperty("autoYn")
	private String auto_yn;
	
	/**
	 * autoTime
	 */
	@JsonProperty("autoTime")
	private int auto_time;
	
	
	/**
	 * lockYn
	 */
	@JsonProperty("lockYn")
	private String lock_yn;
	
	
	/**
	 * lockIp
	 */
	@JsonProperty("lockIp")
	private String lock_ip;
	
}
