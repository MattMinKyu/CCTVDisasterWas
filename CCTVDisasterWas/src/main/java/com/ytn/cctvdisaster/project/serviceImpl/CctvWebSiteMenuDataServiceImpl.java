package com.ytn.cctvdisaster.project.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.dao.CctvWebSiteInfoDataDao;
import com.ytn.cctvdisaster.project.dto.CctvWebSiteInfoDataDto;
import com.ytn.cctvdisaster.project.service.CctvWebSiteMenuDataService;

@Service
public class CctvWebSiteMenuDataServiceImpl implements CctvWebSiteMenuDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvWebSiteMenuDataServiceImpl.class);
	
	private CctvWebSiteInfoDataDao cctvWebSiteInfoDataDao;
	
	public CctvWebSiteMenuDataServiceImpl(CctvWebSiteInfoDataDao cctvWebSiteInfoDataDao) {
		this.cctvWebSiteInfoDataDao = cctvWebSiteInfoDataDao;
	}
	
	
	@Override
	public String getWebSiteInfoListDataJson() {
		String resultJsonData = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CctvWebSiteInfoDataDto> cctvWebSiteInfoDataDtoList = new ArrayList<CctvWebSiteInfoDataDto>();
		
		cctvWebSiteInfoDataDtoList = cctvWebSiteInfoDataDao.selectCctvWebSiteDataList();
		
		if(cctvWebSiteInfoDataDtoList.size() == 0) {
			try {
				return mapper.writeValueAsString(cctvWebSiteInfoDataDtoList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvWebSiteInfoDataDtoList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvWebSiteMenuDataServiceImpl] [getWebSiteInfoListDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
}
