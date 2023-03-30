package com.ytn.cctvdisaster.project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.service.CctvEtcVideoInfoDataService;
import com.ytn.cctvdisaster.project.service.CctvKtIctVideoInfoDataService;


/**
* CctvMapInfoData RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvdisaster/data/video/")
public class CctvVideoDataInfoRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvVideoDataInfoRestController.class);
	
	private CctvEtcVideoInfoDataService cctvVideoInfoDataService;
	
	private CctvKtIctVideoInfoDataService cctvKtIctVideoInfoDataService;
	
	public CctvVideoDataInfoRestController(CctvEtcVideoInfoDataService cctvVideoInfoDataService, CctvKtIctVideoInfoDataService cctvKtIctVideoInfoDataService) {
		this.cctvVideoInfoDataService = cctvVideoInfoDataService;
		this.cctvKtIctVideoInfoDataService = cctvKtIctVideoInfoDataService;
	}
	

	/**
	 * CCTV 영상정보 리스트
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@PostMapping("info/list")
	public String getCctvVideoThumbnailInfoList(@RequestParam(value = "linkPath")String linkPath) {
		
		logger.info("[CctvMapDataResearchRestController] [getLocalListByNaverMap] START ~~!!");
		
		//String linkPath="http://220.70.164.229/cctv/hls/sangwonsa.m3u8";
		//String linkPath="http://220.70.164.229/cctv/hls/solak.m3u8";
		String jsonList = "";
		
		cctvVideoInfoDataService.getCctvVideoThumbnailDataJson(linkPath);
		   
		return jsonList;
	}
	
	/**
	 * CCTV 영상정보 리스트
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@PostMapping("cctv/list")
	public String VideoInfoListByKtIctDataList(@RequestParam(value = "cctvId")String cctvId) {
		
		logger.info("[CctvMapDataResearchRestController] [VideoInfoListByKtIctDataList] START ~~!!");
		
		//String linkPath="http://220.70.164.229/cctv/hls/sangwonsa.m3u8";
		//String linkPath="http://220.70.164.229/cctv/hls/solak.m3u8";
		String jsonList = "";
		
		cctvKtIctVideoInfoDataService.getCctvStatusInfoListByKtIctDataJson(cctvId);
		   
		return jsonList;
	}
	
	
	
	
}
