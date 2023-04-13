package com.ytn.cctvdisaster.project.service;

import com.ytn.cctvdisaster.project.vo.CctvPlayListDataVo;

/**
 * CctvPlayListDataService Interface
 * 
 * @author mattmk
 *
 */
public interface CctvPlayListDataService {
	
	/**
	 * getCctvPlayListDataJson
	 * 
	 * @author mattmk
	 * @param String likeSearchKeyword
	 * @return String
	 */
	public String getCctvPlayListDataJson(String likeSearchKeyword);
	
	/**
	 * modifyCctvPlayListDataJson
	 * 
	 * @author mattmk
	 * @param CctvPlayListDataVo cctvLikeDataVo
	 * @return int
	 */
	public int modifyCctvPlayListDataJson(CctvPlayListDataVo cctvLikeDataVo);
	
	
	/**
	 * deleteCctvPlayListDataJson
	 * 
	 * @author mattmk
	 * @param String plistId
	 * @return int
	 */
	public int deleteCctvPlayListDataJson(String plistId);
	
}
