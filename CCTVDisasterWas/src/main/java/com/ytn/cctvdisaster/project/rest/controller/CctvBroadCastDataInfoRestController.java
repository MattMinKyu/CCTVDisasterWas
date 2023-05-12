package com.ytn.cctvdisaster.project.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ytn.cctvdisaster.project.service.CctvBroadCastDataService;


/**
* CctvBroadCastDataInfo RestController
* @author mattmk
*
*/
@RestController
@RequestMapping("/ytn/cctvdisaster/data/broadcast")
public class CctvBroadCastDataInfoRestController {
private static final Logger logger = LoggerFactory.getLogger(CctvBroadCastDataInfoRestController.class);
	
	private CctvBroadCastDataService cctvBroadCastDataService;
	
	public CctvBroadCastDataInfoRestController(CctvBroadCastDataService cctvBroadCastDataService) {
		this.cctvBroadCastDataService = cctvBroadCastDataService;
	}
	

	/**
	 * Lock Info Data
	 * 
	 * @author mattmk
	 * @param 
	 * @return String
	 */
	@PostMapping("/info/lock")
	public String getCctvBroadCastLockInfoData() {
		logger.info("[CctvBroadCastDataInfoRestController] [getCctvBroadCastLockInfoData] START ~~!!");
		
		String jsonList="";

		jsonList = cctvBroadCastDataService.getCctvBroadCastLockInfoDataJson();
		
		return jsonList;
	}
	
	/**
	 * Lock 수정
	 * 
	 * @author mattmk
	 * @param String lockYn, String plistId
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	@PutMapping("/modify/lock")
	public int modifyCctvBroadCastLockData(@RequestParam(value = "lockYn")String lockYn, @RequestParam(value = "plistId")String plistId, HttpServletRequest request) {
		logger.info("[CctvBroadCastDataInfoRestController] [modifyCctvBroadCastLockData] START ~~!!");
		
		int resultCnt = 0;
		
		if(StringUtils.isEmpty(lockYn)
				|| (!(lockYn.equals("Y") || lockYn.equals("N")))
				|| StringUtils.isEmpty(plistId)) {
			return resultCnt;
		}
		
		logger.info("[CctvBroadCastDataInfoRestController] [modifyCctvBroadCastLockData] request.getRemoteAddr() : {}", request.getRemoteAddr());
		
		resultCnt = cctvBroadCastDataService.modifyCctvBroadCastLockDataJson(lockYn, plistId, request.getRemoteAddr());
		
		return resultCnt;
	}
	
}
