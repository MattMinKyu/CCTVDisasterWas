package com.ytn.cctvdisaster.project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.service.CctvMapDataResearchService;
import com.ytn.cctvdisaster.project.vo.NaverMapLocalResearchVo;


/**
* CctvMapInfoData RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvmap/data/research/")
public class CctvMapDataResearchRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvMapDataResearchRestController.class);
	
	private CctvMapDataResearchService cctvMapDataResearchService;
	
	public CctvMapDataResearchRestController(CctvMapDataResearchService cctvMapDataResearchService) {
		this.cctvMapDataResearchService = cctvMapDataResearchService;
	}
	

	/**
	 * naver map 연동 rest api
	 * 지명으로 검색
	 * 
	 * @author mattmk
	 * @param Integer, Integer, String, String, String, Integer
	 * @return String
	 */
	@PostMapping("local/list")
	public String getLocalListByNaverMap(@RequestParam(value = "searchLocalName")String searchLocalName) {
		
		logger.info("[CctvMapDataResearchRestController] [getLocalListByNaverMap] START ~~!!");
		
		String jsonList="";
		NaverMapLocalResearchVo naverMapLocalResearchVo = new NaverMapLocalResearchVo();
		
		naverMapLocalResearchVo.setQuery(searchLocalName);
		jsonList = cctvMapDataResearchService.getLocalListByNaverMapDataJson(naverMapLocalResearchVo);
		   
		return jsonList;
	}
}
