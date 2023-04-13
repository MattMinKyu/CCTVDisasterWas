package com.ytn.cctvdisaster.project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.service.CctvMapDataResearchService;
import com.ytn.cctvdisaster.project.vo.CctvCoordinateMapListResearchVo;
import com.ytn.cctvdisaster.project.vo.NaverMapLocalResearchVo;


/**
* CctvMapInfoData RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvdisaster/data/mapresearch")
public class CctvMapDataResearchRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvMapDataResearchRestController.class);
	
	private CctvMapDataResearchService cctvMapDataResearchService;
	
	public CctvMapDataResearchRestController(CctvMapDataResearchService cctvMapDataResearchService) {
		this.cctvMapDataResearchService = cctvMapDataResearchService;
	}
	

	/**
	 * getLocalListByNaverMap Call Restapi
	 * 
	 * @author mattmk
	 * @param searchLocalName
	 * @return String
	 */
	@PostMapping("/local/list")
	public String getLocalListByNaverMap(@RequestParam(value = "searchLocalName")String searchLocalName) {
		
		logger.info("[CctvMapDataResearchRestController] [getLocalListByNaverMap] START ~~!!");
		
		String jsonList="";
		NaverMapLocalResearchVo naverMapLocalResearchVo = new NaverMapLocalResearchVo();
		
		naverMapLocalResearchVo.setQuery(searchLocalName);
		jsonList = cctvMapDataResearchService.getLocalListByNaverMapDataJson(naverMapLocalResearchVo);
		
		return jsonList;
	}
	
	
	/**
	 * getCctvListByCoordinateLocalMap Call Restapi
	 * 
	 * @author mattmk
	 * @param searchLocalName
	 * @return String
	 */
	@PostMapping("/cctv/list")
	public String getCctvListByCoordinateLocalMap(@RequestParam(value = "swLat")double swLat, @RequestParam(value = "neLat")double neLat
											, @RequestParam(value = "swLng")double swLng, @RequestParam(value = "neLng")double neLng) {
		logger.info("[CctvMapDataResearchRestController] [getCctvListByCoordinateLocalMap] START ~~!!");
		
		String jsonList="";

		if(!(swLat > 0 && neLat > 0 && swLng > 0 && neLng > 0)) {
			return jsonList;
		}
		
		CctvCoordinateMapListResearchVo cctvMapListResearchVo = new CctvCoordinateMapListResearchVo();
		cctvMapListResearchVo.setSwLat(swLat);
		cctvMapListResearchVo.setNeLat(neLat);
		cctvMapListResearchVo.setSwLng(swLng);
		cctvMapListResearchVo.setNeLng(neLng);
		
		
		jsonList = cctvMapDataResearchService.getCctvListByCoordinateLocalMapDataJson(cctvMapListResearchVo);
		
		return jsonList;
	}
	
}
