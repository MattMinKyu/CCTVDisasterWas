package com.ytn.cctvdisaster.project.service;

import com.ytn.cctvdisaster.project.vo.CctvPlayListDataVo;
import com.ytn.cctvdisaster.project.vo.CctvPlayListDetailDataVo;

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
	
	/**
	 * getCctvPlayListDetailDataJson
	 * 
	 * @author mattmk
	 * @param String plistId
	 * @return String
	 */
	public String getCctvPlayListDetailDataJson(String plistId);
	
	/**
	 * modifyCctvPlayListDetailDataJson
	 * 
	 * @author mattmk
	 * @param CctvPlayListDetailDataVo cctvPlayListDetailDataVo
	 * @return int
	 */
	public int modifyCctvPlayListDetailDataJson(CctvPlayListDetailDataVo cctvPlayListDetailDataVo);
	
}
