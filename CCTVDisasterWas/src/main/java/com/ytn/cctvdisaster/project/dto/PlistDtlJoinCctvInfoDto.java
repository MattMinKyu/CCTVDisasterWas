package com.ytn.cctvdisaster.project.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

/**
* PlistDtlJoinCctvInfoDto
* 
* @author mattmk
* 
*/

@Data
public class PlistDtlJoinCctvInfoDto {
	
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
	 * srcNm
	 */
	@JsonProperty("srcNm")
	private String lvl1_nm;
	
	/**
	 * cctvNm
	 */
	@JsonProperty("cctvNm")
	private String lvl3_nm;
	
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
	
	/**
	 * url
	 */
	private String url;
	
	/**
	 * srcGb
	 */
	@JsonProperty("srcGb")
	private String src_gb;
}
