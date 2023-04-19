package com.ytn.cctvdisaster.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
* PlistDtlDto
* 
* @author mattmk
* 
*/

@Data
public class PlistDtlDto {
	
	/**
	 * plistId
	 */
	@JsonProperty("plistId")
	private String plist_id;
	
	/**
	 * plistNo
	 */
	@JsonProperty("plistNo")
	private int plist_no;
	
	/**
	 * cctvId
	 */
	@JsonProperty("cctvId")
	private String cctv_id;
	
	/**
	 * captionNm
	 */
	@JsonProperty("captionNm")
	private String caption_nm;
	
	/**
	 * insYmd
	 */
	@JsonProperty("insYmd")
	private String ins_ymd;
	
	
	/**
	 * insTime
	 */
	@JsonProperty("insTime")
	private String ins_time;
	
	/**
	 * updYmd
	 */
	@JsonProperty("updYmd")
	private String upd_ymd;
	
	
	/**
	 * updTime
	 */
	@JsonProperty("updTime")
	private String upd_time;
}
