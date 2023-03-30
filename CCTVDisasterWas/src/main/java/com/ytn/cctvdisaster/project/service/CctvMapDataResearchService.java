package com.ytn.cctvdisaster.project.service;

import java.util.List;

import com.ytn.cctvdisaster.project.dto.CctvMapResearchDto;
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
	 * getCctvListByMapDataJson
	 * 
	 * @author mattmk
	 * @param cctvMapResearchDto
	 * @return String
	 */
	public String getCctvListByLocalMapDataJson(CctvMapResearchDto cctvMapResearchDto, List<String> testCctvId);
	
}
