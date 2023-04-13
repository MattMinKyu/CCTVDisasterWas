package com.ytn.cctvdisaster.project.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.dao.CctvPlayListInfoDataDao;
import com.ytn.cctvdisaster.project.dto.PlistMstDto;
import com.ytn.cctvdisaster.project.service.CctvPlayListDataService;
import com.ytn.cctvdisaster.project.vo.CctvPlayListDataVo;

@Service
public class CctvPlayListDataServiceImpl implements CctvPlayListDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvPlayListDataServiceImpl.class);
	
	private CctvPlayListInfoDataDao cctvPlayListInfoDataDao;
	
	public CctvPlayListDataServiceImpl(CctvPlayListInfoDataDao cctvPlayListInfoDataDao) {
		this.cctvPlayListInfoDataDao = cctvPlayListInfoDataDao;
	}
	
	
	@Override
	public String getCctvPlayListDataJson(String likeSearchKeyword) {
		String resultJsonData = "";
		List<PlistMstDto> plistMstDtoList = new ArrayList<PlistMstDto>();
		
		plistMstDtoList = cctvPlayListInfoDataDao.selectCctvPlayListInfoList(likeSearchKeyword);
		
		if(plistMstDtoList.size() == 0) {
			return resultJsonData;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			resultJsonData = mapper.writeValueAsString(plistMstDtoList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvPlayListDataServiceImpl] [getCctvPlayListDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	
	@SuppressWarnings("deprecation")
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int modifyCctvPlayListDataJson(CctvPlayListDataVo cctvPlayListDataVo) {
		int resultCnt = 0;
		
		// insert
		if(StringUtils.isEmpty(cctvPlayListDataVo.getPlistId())) {
			try {
				resultCnt = cctvPlayListInfoDataDao.insertCctvPlayListInfo(cctvPlayListDataVo);
			}catch (Exception e) {
				logger.error("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDataJson] [Try Catch Insert Data] [Exception] ====> {}", e);
				logger.error("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDataJson] [Try Catch Insert Data] [cctvPlayListDataVo] ====> {}", cctvPlayListDataVo);
			}
			
		// update
		}else {
			try {
				resultCnt = cctvPlayListInfoDataDao.updateCctvPlayListInfo(cctvPlayListDataVo);
			}catch (Exception e) {
				logger.error("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDataJson] [Try Catch Update Data] [Exception] ====> {}", e);
				logger.error("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDataJson] [Try Catch Update Data] [cctvPlayListDataVo] ====> {}", cctvPlayListDataVo);
			}
		}
		
		logger.info("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDataJson] resultCnt : {}", resultCnt);
		
		return resultCnt;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int deleteCctvPlayListDataJson(String plistId) {
		int resultCnt = 0;
		
		try {
			resultCnt = cctvPlayListInfoDataDao.deleteCctvPlayListInfo(plistId);
		}catch (Exception e) {
			logger.error("[CctvPlayListDataServiceImpl] [deleteCctvPlayListDataJson] [Try Catch Delete Data] [Exception] ====> {}", e);
			logger.error("[CctvPlayListDataServiceImpl] [deleteCctvPlayListDataJson] [Try Catch Delete Data] [plistId] ====> {}", plistId);
		}
		
		return resultCnt;
	}
}
