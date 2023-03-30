package com.ytn.cctvdisaster.project.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.dto.CctvMapResearchDto;
import com.ytn.cctvdisaster.project.service.CctvMapDataResearchService;
import com.ytn.cctvdisaster.project.vo.NaverMapLocalResearchVo;


/**
* CctvMapInfoData RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvdisaster/data/mapresearch/")
public class CctvMapDataResearchRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvMapDataResearchRestController.class);
	
	private CctvMapDataResearchService cctvMapDataResearchService;
	
	public CctvMapDataResearchRestController(CctvMapDataResearchService cctvMapDataResearchService) {
		this.cctvMapDataResearchService = cctvMapDataResearchService;
	}
	

	/**
	 * naver map Call Restapi
	 * 
	 * @author mattmk
	 * @param searchLocalName
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
	
	
	/**
	 * naver map Call Restapi
	 * 
	 * @author mattmk
	 * @param searchLocalName
	 * @return String
	 */
	@PostMapping("cctv/list")
	public String getCctvListByLocalMap(@RequestParam(value = "cctvIdParam")List<String> cctvIdParam) {
		
		logger.info("[CctvMapDataResearchRestController] [getCctvListByLocalMap] START ~~!!");
		
		logger.info("[CctvMapDataResearchRestController] [getCctvListByLocalMap] [cctvIdParam] : {}", cctvIdParam);
		
		String jsonList="";
		CctvMapResearchDto cctvMapResearchDto = new CctvMapResearchDto();
		cctvMapResearchDto.setXTopCoordinate("");
		cctvMapResearchDto.setXDownCoordinate("");
		cctvMapResearchDto.setYTopCoordinate("");
		cctvMapResearchDto.setYDownCoordinate("");
		
		jsonList = cctvMapDataResearchService.getCctvListByLocalMapDataJson(cctvMapResearchDto, cctvIdParam);
		
		return jsonList;
	}
}
