package com.ytn.cctvdisaster.project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.service.CctvCategoryDataService;


/**
* CctvCategoryDataInfos RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvdisaster/data/category")
public class CctvCategoryDataInfoRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvCategoryDataInfoRestController.class);
	
	private CctvCategoryDataService cctvCategoryDataService;
	
	public CctvCategoryDataInfoRestController(CctvCategoryDataService cctvCategoryDataService) {
		this.cctvCategoryDataService = cctvCategoryDataService;
	}
	

	/**
	 * 대카테고리 리스트
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@PostMapping("/depth1/list")
	public String getDepth1ListData() {
		logger.info("[CctvCategoryDataInfoRestController] [getDepth1ListData] START ~~!!");
		
		String jsonList="";

		jsonList = cctvCategoryDataService.getDepth1ListDataJson();
		
		return jsonList;
	}
	
	
	/**
	 * 중카테고리 리스트
	 * 
	 * @author mattmk
	 * @param String srcId
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	@PostMapping("/depth2/list")
	public String getDepth2ListData(@RequestParam(value = "srcId")String srcId) {
		logger.info("[CctvCategoryDataInfoRestController] [getDepth2ListData] START ~~!!");
		
		String jsonList="";
		
		if(StringUtils.isEmpty(srcId)) {
			return jsonList;
		}
		
		jsonList = cctvCategoryDataService.getDepth2ListDataJson(srcId);
		
		return jsonList;
	}
	
	
	/**
	 * 카테고리 검색 리스트
	 * 
	 * @author mattmk
	 * @param searchLocalName
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	@PostMapping("/search/list")
	public String getCctvSearchListData(@RequestParam(value = "cctvSearchKeyword")String cctvSearchKeyword) {
		logger.info("[CctvCategoryDataInfoRestController] [getCctvSearchListData] START ~~!!");
		
		String jsonList="";
		
		if(StringUtils.isEmpty(cctvSearchKeyword)) {
			return jsonList;
		}
		
		jsonList = cctvCategoryDataService.getCctvCategorySearchListDataJson(cctvSearchKeyword);
		
		return jsonList;
	}
}
