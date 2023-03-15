package com.ytn.cctvdisaster.project.service;

import com.ytn.cctvdisaster.project.vo.NaverMapLocalResearchVo;

/**
 * CctvMapDataResearchService Interface
 * 
 * @author mattmk
 *
 */
public interface CctvMapDataResearchService {
	
	/**
	 * Naver Map Data By Local Research.
	 * 
	 * @author mattmk
	 * @param NaverMapLocalResearchResultVo naverMapLocalResearchVo
	 * @return String
	 */
	public String getLocalListByNaverMapDataJson(NaverMapLocalResearchVo naverMapLocalResearchVo);
	
}
