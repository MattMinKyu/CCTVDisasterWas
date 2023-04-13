package com.ytn.cctvdisaster.project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.service.CctvVideoInfoDataService;
import com.ytn.cctvdisaster.project.vo.CctvIdDataVo;


/**
* CctvMapInfoData RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvdisaster/data/video")
public class CctvVideoDataInfoRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvVideoDataInfoRestController.class);
	
	private CctvVideoInfoDataService cctvVideoInfoDataService;
	
	public CctvVideoDataInfoRestController(CctvVideoInfoDataService cctvVideoInfoDataService) {
		this.cctvVideoInfoDataService = cctvVideoInfoDataService;
	}
	
	
	/**
	 * CCTV 영상정보 리스트
	 * 
	 * @author mattmk
	 * @param searchLocalName
	 * @return String
	 */
	@PostMapping("/streamingUrl")
	public String getCctvStreamUrlData(@RequestBody CctvIdDataVo cctvIdDataVo) {
		logger.info("[CctvVideoDataInfoRestController] [getCctvStreamUrlData] START ~~!!");
		
		String jsonList="";
		
		if(cctvIdDataVo.getCctvId().size() == 0) {
			return jsonList;
		}
		
		jsonList = cctvVideoInfoDataService.getCctvStreamingUrlMapDataJson(cctvIdDataVo.getCctvId());
		
		return jsonList;
	}
	
	/**
	 * CCTV 썸네일정보 리스트
	 * 
	 * @author mattmk
	 * @param searchLocalName
	 * @return String
	 */
	@PostMapping("/thumbnailUrl")
	public String getCctvThumbnailUrlData(@RequestBody CctvIdDataVo cctvIdDataVo) {
		logger.info("[CctvVideoDataInfoRestController] [getCctvThumbnailUrlData] START ~~!!");
		
		String jsonList="";
		
		if(cctvIdDataVo.getCctvId().size() == 0) {
			return jsonList;
		}
		
		jsonList = cctvVideoInfoDataService.getCctvThumbnailUrlMapDataJson(cctvIdDataVo.getCctvId());
		
		return jsonList;
	}
	
}
