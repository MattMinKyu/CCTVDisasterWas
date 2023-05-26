package com.ytn.cctvdisaster.project.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.service.CctvWebSiteMenuDataService;


/**
* CctvWebSiteMenuDataInfo RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvdisaster/data/website")
public class CctvWebSiteMenuDataInfoRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvWebSiteMenuDataInfoRestController.class);
	
	private CctvWebSiteMenuDataService cctvWebSiteMenuDataService;
	
	public CctvWebSiteMenuDataInfoRestController(CctvWebSiteMenuDataService cctvWebSiteMenuDataService) {
		this.cctvWebSiteMenuDataService = cctvWebSiteMenuDataService;
	}
	

	/**
	 * 웹사이트 리스트
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@PostMapping("/list")
	public String getWebSiteInfoListData() {
		logger.info("[CctvWebSiteMenuDataInfoRestController] [getWebSiteInfoListData] START ~~!!");
		
		String jsonList="";

		jsonList = cctvWebSiteMenuDataService.getWebSiteInfoListDataJson();
		
		return jsonList;
	}
	
}
