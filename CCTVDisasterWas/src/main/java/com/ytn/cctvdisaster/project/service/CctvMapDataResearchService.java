package com.ytn.cctvdisaster.project.service;

import com.ytn.cctvdisaster.project.vo.CctvCoordinateMapListResearchVo;
import com.ytn.cctvdisaster.project.vo.NaverMapLocalResearchVo;

/**
 * CctvMapDataResearchService Interface
 * 
 * @author mattmk
 *
 */
public interface CctvMapDataResearchService {
	
	/**
	 * getLocalListByNaverMapDataJson
	 * 
	 * @author mattmk
	 * @param searchLocalName
	 * @return String
	 */
	public String getLocalListByNaverMapDataJson(NaverMapLocalResearchVo naverMapLocalResearchVo);
	
	/**
	 * getCctvListByCoordinateLocalMapDataJson
	 * 
	 * @author mattmk
	 * @param CctvCoordinateMapListResearchVo cctvMapListResearchVo
	 * @return String
	 */
	public String getCctvListByCoordinateLocalMapDataJson(CctvCoordinateMapListResearchVo cctvMapListResearchVo);
	
}
