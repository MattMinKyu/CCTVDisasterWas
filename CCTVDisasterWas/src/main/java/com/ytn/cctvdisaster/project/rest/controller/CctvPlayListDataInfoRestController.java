package com.ytn.cctvdisaster.project.rest.controller;

import org.apache.ibatis.annotations.Delete;
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


/**
* CctvPlayListDataInfo RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvdisaster/data/playlist")
public class CctvPlayListDataInfoRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvPlayListDataInfoRestController.class);
	
	private CctvPlayListDataService cctvLikeDataService;
	
	public CctvPlayListDataInfoRestController(CctvPlayListDataService cctvLikeDataService) {
		this.cctvLikeDataService = cctvLikeDataService;
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

		jsonList = cctvLikeDataService.getCctvPlayListDataJson(likeSearchKeyword);
		
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
		
		
		resultCnt = cctvLikeDataService.modifyCctvPlayListDataJson(cctvPlayListDataVo);
		
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
		
		resultCnt = cctvLikeDataService.deleteCctvPlayListDataJson(plistId);
		
		return resultCnt;
	}
	
}
