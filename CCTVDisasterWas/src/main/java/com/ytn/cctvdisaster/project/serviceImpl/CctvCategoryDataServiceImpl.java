package com.ytn.cctvdisaster.project.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.dao.CctvCategoryInfoDataDao;
import com.ytn.cctvdisaster.project.dto.CctvCategoryDepth1DataDto;
import com.ytn.cctvdisaster.project.dto.CctvInfoDataDto;
import com.ytn.cctvdisaster.project.result.vo.CctvCategorySearchResultListVo;
import com.ytn.cctvdisaster.project.result.vo.CctvCategorySearchResultVo;
import com.ytn.cctvdisaster.project.service.CctvCategoryDataService;

@Service
public class CctvCategoryDataServiceImpl implements CctvCategoryDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvCategoryDataServiceImpl.class);
	
	private CctvCategoryInfoDataDao cctvCategoryInfoDataDao;
	
	public CctvCategoryDataServiceImpl(CctvCategoryInfoDataDao cctvCategoryInfoDataDao) {
		this.cctvCategoryInfoDataDao = cctvCategoryInfoDataDao;
	}
	
	
	@Override
	public String getDepth1ListDataJson() {
		String resultJsonData = "";
		List<CctvCategoryDepth1DataDto> cctvCategoryDepth1DataDto = new ArrayList<CctvCategoryDepth1DataDto>();
		
		cctvCategoryDepth1DataDto = cctvCategoryInfoDataDao.selectCctvCategoryDepth1List();
		
		if(cctvCategoryDepth1DataDto.size() == 0) {
			return resultJsonData;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvCategoryDepth1DataDto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvCategoryDataServiceImpl] [getDepth1ListDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	@Override
	public String getDepth2ListDataJson(String srcId) {
		String resultJsonData = "";
		List<CctvInfoDataDto> cctvInfoDataDto = new ArrayList<CctvInfoDataDto>();
		
		cctvInfoDataDto = cctvCategoryInfoDataDao.selectCctvCategoryDepth2List(srcId);
		
		if(cctvInfoDataDto.size() == 0) {
			return resultJsonData;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvInfoDataDto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvCategoryDataServiceImpl] [getDepth2ListDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	
	@Override
	public String getCctvCategorySearchListDataJson(String cctvSearchKeyword) {
		String resultJsonData = "";
		List<CctvInfoDataDto> cctvInfoDataDto = new ArrayList<CctvInfoDataDto>();
		
		cctvInfoDataDto = cctvCategoryInfoDataDao.selectCctvCategorySearchList(cctvSearchKeyword);
		
		if(cctvInfoDataDto.size() == 0) {
			return resultJsonData;
		}
		
		String cctvSrcIdTemp = "";
		String cctvSrcNmTemp = "";
		List<CctvCategorySearchResultListVo> cctvCategorySearchResultListVoArray = new ArrayList<CctvCategorySearchResultListVo>();
		List<CctvCategorySearchResultVo> cctvCategorySearchResultArray = new ArrayList<CctvCategorySearchResultVo>();
		CctvCategorySearchResultListVo cctvCategorySearchResultListVo = new CctvCategorySearchResultListVo();
		
		for(int i=0; i<cctvInfoDataDto.size(); i++) {
			CctvCategorySearchResultVo cctvCategorySearchResultVo = new CctvCategorySearchResultVo();
			
			if(i == 0) {
				cctvSrcIdTemp = cctvInfoDataDto.get(i).getSrc_id();
				cctvSrcNmTemp = cctvInfoDataDto.get(i).getSrc_nm();
				cctvCategorySearchResultListVo.setCategoryDepth1(cctvSrcIdTemp);
				cctvCategorySearchResultListVo.setSrcNm(cctvSrcNmTemp);
			}
			
			if(!cctvSrcIdTemp.equals(cctvInfoDataDto.get(i).getSrc_id())) {
				cctvCategorySearchResultListVo.setItems(cctvCategorySearchResultArray);
				cctvCategorySearchResultListVo.setCategoryDepth1(cctvSrcIdTemp);
				cctvCategorySearchResultListVo.setSrcNm(cctvSrcNmTemp);
				cctvCategorySearchResultListVoArray.add(cctvCategorySearchResultListVo);
				
				cctvCategorySearchResultArray = new ArrayList<CctvCategorySearchResultVo>();
				cctvCategorySearchResultListVo = new CctvCategorySearchResultListVo();
				
				cctvSrcIdTemp = cctvInfoDataDto.get(i).getSrc_id();
				cctvSrcNmTemp = cctvInfoDataDto.get(i).getSrc_nm();
			}
			
			cctvCategorySearchResultVo.setCctvId(cctvInfoDataDto.get(i).getCctv_id());
			cctvCategorySearchResultVo.setCctvNm(cctvInfoDataDto.get(i).getCctv_nm());
			cctvCategorySearchResultVo.setSrcNm(cctvInfoDataDto.get(i).getSrc_nm());
			cctvCategorySearchResultVo.setSrcGb(cctvInfoDataDto.get(i).getSrc_gb());
			cctvCategorySearchResultVo.setSidoNm(cctvInfoDataDto.get(i).getSido_nm());
			cctvCategorySearchResultVo.setLat(cctvInfoDataDto.get(i).getLat());
			cctvCategorySearchResultVo.setLon(cctvInfoDataDto.get(i).getLon());
			
			cctvCategorySearchResultArray.add(cctvCategorySearchResultVo);
			
			if(i == (cctvInfoDataDto.size()-1)) {
				cctvCategorySearchResultListVo.setItems(cctvCategorySearchResultArray);
				cctvCategorySearchResultListVo.setCategoryDepth1(cctvSrcIdTemp);
				cctvCategorySearchResultListVo.setSrcNm(cctvSrcNmTemp);
				cctvCategorySearchResultListVoArray.add(cctvCategorySearchResultListVo);
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvCategorySearchResultListVoArray);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvCategoryDataServiceImpl] [getCctvCategorySearchListDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
}
