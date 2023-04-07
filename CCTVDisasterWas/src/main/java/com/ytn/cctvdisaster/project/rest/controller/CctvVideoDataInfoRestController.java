package com.ytn.cctvdisaster.project.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.service.CctvVideoInfoDataService;


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
	public String getCctvStreamUrlData(@RequestBody List<String> cctvIdList) {
		logger.info("[CctvVideoDataInfoRestController] [getCctvStreamUrlData] START ~~!!");
		
		String jsonList="";
		
		if(cctvIdList.size() == 0) {
			return jsonList;
		}
		
		jsonList = cctvVideoInfoDataService.getCctvStreamingUrlMapDataJson(cctvIdList);
		
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
	public String getCctvThumbnailUrlData(@RequestBody List<String> cctvIdList) {
		logger.info("[CctvVideoDataInfoRestController] [getCctvThumbnailUrlData] START ~~!!");
		
		String jsonList="";

		if(cctvIdList.size() == 0) {
			return jsonList;
		}
		
		jsonList = cctvVideoInfoDataService.getCctvThumbnailUrlMapDataJson(cctvIdList);
		
		return jsonList;
	}
	
	
	/**
	 * CCTV 영상정보 리스트
	 * 
	 * @author mattmk
	 * @param searchLocalName
	 * @return String
	 */
	/*
	@PostMapping("/streamingUrl")
	public String getCctvStreamUrlData(@RequestBody List<CctvLikeDataListVo> cctvIdList) {
		logger.info("[CctvVideoDataInfoRestController] [getCctvStreamUrlData] START ~~!!");
		
		String jsonList="";
		
		for(CctvLikeDataListVo temp : cctvIdList){
			logger.info("[CctvVideoDataInfoRestController] [getCctvStreamUrlData] [temp.getKey()] : {}", temp);
	      }
		
		//mapper.writeval
		
		/*
		if(cctvIdList.size() == 0) {
			return jsonList;
		}else {
			for(String temp : cctvIdList) {
				logger.info("[CctvVideoDataInfoRestController] [getCctvStreamUrlData] [cctvIdList] : {}", temp);
			}
		}
		
		//jsonList = cctvVideoInfoDataService.getCctvStreamingUrlMapDataJson(cctvIdList);
		
		return jsonList;
		
		
		
		[
		  {
		        "cctvId": "7063",
		        "cctvNm": "가덕영업소"
		  },
		  {
		        "cctvId": "7058",
		        "cctvNm": "거가1교"
		  }
		]
	}
	*/
	
}
