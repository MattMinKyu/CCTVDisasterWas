package com.ytn.cctvdisaster.project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.service.CctvPlayListDataService;
import com.ytn.cctvdisaster.project.vo.CctvPlayListDataVo;
import com.ytn.cctvdisaster.project.vo.CctvPlayListDetailDataVo;


/**
* CctvPlayListDataInfo RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvdisaster/data/playlist")
public class CctvPlayListDataInfoRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvPlayListDataInfoRestController.class);
	
	private CctvPlayListDataService cctvPlayListDataService;
	
	public CctvPlayListDataInfoRestController(CctvPlayListDataService cctvPlayListDataService) {
		this.cctvPlayListDataService = cctvPlayListDataService;
	}
	

	/**
	 * PlayList 리스트
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@PostMapping("/list")
	public String getCctvPlayListData(@RequestParam(value = "keyword")String likeSearchKeyword) {
		logger.info("[CctvPlayListDataInfoRestController] [getCctvPlayListData] START ~~!!");
		
		String jsonList="";

		jsonList = cctvPlayListDataService.getCctvPlayListDataJson(likeSearchKeyword);
		
		return jsonList;
	}
	
	/**
	 * PlayList 추가, 수정
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@PutMapping("/modify")
	public int modifyCctvPlayListData(@RequestBody CctvPlayListDataVo cctvPlayListDataVo) {
		logger.info("[CctvPlayListDataInfoRestController] [modifyCctvPlayListData] START ~~!!");
		
		int resultCnt=0;
		
		if(cctvPlayListDataVo == null) {
			return resultCnt;
		}
		
		
		resultCnt = cctvPlayListDataService.modifyCctvPlayListDataJson(cctvPlayListDataVo);
		
		return resultCnt;
	}
	
	/**
	 * PlayList 삭제
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	@DeleteMapping("/delete")
	public int deleteCctvPlayListData(@RequestParam(value = "plistId")String plistId) {
		logger.info("[CctvPlayListDataInfoRestController] [deleteCctvPlayListData] START ~~!!");
		
		int resultCnt=0;
		
		if(StringUtils.isEmpty(plistId)) {
			return resultCnt;
		}
		
		resultCnt = cctvPlayListDataService.deleteCctvPlayListDataJson(plistId);
		
		return resultCnt;
	}
	
	/**
	 * PlayList Detail List
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	@PostMapping("/list/detail")
	public String getCctvPlayListDetailData(@RequestParam(value = "plistId")String plistId) {
		logger.info("[CctvPlayListDataInfoRestController] [getCctvPlayListDetailData] START ~~!!");
		
		String jsonList="";
		
		if(StringUtils.isEmpty(plistId)) {
			return jsonList;
		}
		
		jsonList = cctvPlayListDataService.getCctvPlayListDetailDataJson(plistId);
		
		return jsonList;
	}
	
	/**
	 * PlayList Detail 추가, 수정
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@PutMapping("/modify/detail")
	public int modifyCctvPlayListDetailData(@RequestBody CctvPlayListDetailDataVo cctvPlayListDetailDataVo) {
		logger.info("[CctvPlayListDataInfoRestController] [modifyCctvPlayListDetailData] START ~~!!");
		
		int resultCnt=0;
		
		if(cctvPlayListDetailDataVo == null) {
			return resultCnt;
		}
		
		resultCnt = cctvPlayListDataService.modifyCctvPlayListDetailDataJson(cctvPlayListDetailDataVo);
		
		return resultCnt;
	}
	
}
