package com.ytn.cctvdisaster.project.result.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
* CctvPlayListDetailResultVo
* 
* @author mattmk
* 
*/

@Data
public class CctvPlayListDetailResultVo {
	
	/**
	 * plistId
	 */
	private String plistId;
	
	/**
	 * idx
	 */
	private String idx;
	
	/**
	 * plistNo
	 */
	private int plistNo;
	
	/**
	 * cctvId
	 */
	private String cctvId;
	
	/**
	 * cctvNm
	 */
	private String cctvNm;
	
	/**
	 * srcNm
	 */
	private String srcNm;
	
	
	/**
	 * streamingUrl
	 */
	private String streamingUrl;
	
	/**
	 * serviceYn
	 */
	private String serviceYn;
	
	/**
	 * captionNm
	 */
	private String captionNm;
	
	/**
	 * insYmd
	 */
	private String insYmd;
	
	/**
	 * insTime
	 */
	private String insTime;
	
	/**
	 * updYmd
	 */
	private String updYmd;
	
	/**
	 * updTime
	 */
	private String updTime;
	
	/**
	 * srcGb
	 */
	@JsonIgnore
	private String srcGb;
	
}
