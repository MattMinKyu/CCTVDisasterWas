package com.ytn.cctvdisaster.project.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.dao.CctvCategoryInfoDataDao;
import com.ytn.cctvdisaster.project.dto.CctvCategoryDepth1DataDto;
import com.ytn.cctvdisaster.project.dto.CctvCategoryDepth2DataDto;
import com.ytn.cctvdisaster.project.dto.CctvCategoryDepth3DataDto;
import com.ytn.cctvdisaster.project.result.vo.CctvCategoryDepth1ResultVo;
import com.ytn.cctvdisaster.project.result.vo.CctvCategoryDepth2ResultVo;
import com.ytn.cctvdisaster.project.result.vo.CctvCategoryDepth3ResultListVo;
import com.ytn.cctvdisaster.project.result.vo.CctvCategoryDepth3ResultVo;
import com.ytn.cctvdisaster.project.service.CctvCategoryDataService;

@Service
public class CctvCategoryDataServiceImpl implements CctvCategoryDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvCategoryDataServiceImpl.class);
	
	private CctvCategoryInfoDataDao cctvCategoryInfoDataDao;
	
	public CctvCategoryDataServiceImpl(CctvCategoryInfoDataDao cctvCategoryInfoDataDao) {
		this.cctvCategoryInfoDataDao = cctvCategoryInfoDataDao;
	}
	
	
	@Override
	public String getDepth1ListDataJson(String cctvSearchKeyword) {
		String resultJsonData = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CctvCategoryDepth1DataDto> cctvCategoryDepth1DataDtoList = new ArrayList<CctvCategoryDepth1DataDto>();
		List<CctvCategoryDepth1ResultVo> cctvCategoryDepth1ResultVoList = new ArrayList<CctvCategoryDepth1ResultVo>();
		
		cctvCategoryDepth1DataDtoList = cctvCategoryInfoDataDao.selectCctvCategoryDepth1List(cctvSearchKeyword);
		
		if(cctvCategoryDepth1DataDtoList.size() == 0) {
			try {
				return mapper.writeValueAsString(cctvCategoryDepth1ResultVoList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		
		for(CctvCategoryDepth1DataDto cctvCategoryDepth1DataDto : cctvCategoryDepth1DataDtoList) {
			CctvCategoryDepth1ResultVo cctvCategoryDepth1ResultVo = new CctvCategoryDepth1ResultVo();
			cctvCategoryDepth1ResultVo.setId(cctvCategoryDepth1DataDto.getLvl1_id());
			cctvCategoryDepth1ResultVo.setName(cctvCategoryDepth1DataDto.getLvl1_nm());
			cctvCategoryDepth1ResultVo.setHasItems(Boolean.TRUE);
			cctvCategoryDepth1ResultVo.setGroupId("0");
			
			cctvCategoryDepth1ResultVoList.add(cctvCategoryDepth1ResultVo);
		}
		
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvCategoryDepth1ResultVoList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvCategoryDataServiceImpl] [getDepth1ListDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String getDepth2ListDataJson(String categoryId, String cctvSearchKeyword) {
		String resultJsonData = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CctvCategoryDepth2DataDto> cctvCategoryDepth2DataDtoList = new ArrayList<CctvCategoryDepth2DataDto>();
		List<CctvCategoryDepth2ResultVo> cctvCategoryDepth2ResultVoList = new ArrayList<CctvCategoryDepth2ResultVo>();
		
		Map<String, Object> daoParams = new HashMap<String, Object>();
		daoParams.put("categoryId", categoryId);
		daoParams.put("cctvSearchKeyword", cctvSearchKeyword);
		cctvCategoryDepth2DataDtoList = cctvCategoryInfoDataDao.selectCctvCategoryDepth2List(daoParams);
		
		if(cctvCategoryDepth2DataDtoList.size() == 0) {
			try {
				return mapper.writeValueAsString(cctvCategoryDepth2ResultVoList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		for(CctvCategoryDepth2DataDto cctvCategoryDepth2DataDto : cctvCategoryDepth2DataDtoList) {
			CctvCategoryDepth2ResultVo cctvCategoryDepth2ResultVo = new CctvCategoryDepth2ResultVo();
			cctvCategoryDepth2ResultVo.setName(cctvCategoryDepth2DataDto.getLvl2_nm());
			cctvCategoryDepth2ResultVo.setGroupId(categoryId);
			
			if(StringUtils.isEmpty(cctvCategoryDepth2DataDto.getLvl2_nm())) {
				resultJsonData = this.getDepth3ListDataJson(cctvCategoryDepth2DataDto.getLvl2_id(), categoryId, cctvSearchKeyword);
				return resultJsonData;
			}else {
				cctvCategoryDepth2ResultVo.setId(cctvCategoryDepth2DataDto.getLvl2_id());
				cctvCategoryDepth2ResultVo.setHasItems(Boolean.TRUE);
			}
			
			cctvCategoryDepth2ResultVoList.add(cctvCategoryDepth2ResultVo);
		}
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvCategoryDepth2ResultVoList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvCategoryDataServiceImpl] [getDepth2ListDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String getDepth3ListDataJson(String categoryId, String callCategory, String cctvSearchKeyword) {
		String resultJsonData = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CctvCategoryDepth3DataDto> cctvCategoryDepth3DataDtoList = new ArrayList<CctvCategoryDepth3DataDto>();
		CctvCategoryDepth3ResultListVo cctvCategoryDepth3ResultListVo = new CctvCategoryDepth3ResultListVo();
		
		Map<String, Object> daoParams = new HashMap<String, Object>();
		daoParams.put("categoryId", categoryId);
		daoParams.put("cctvSearchKeyword", cctvSearchKeyword);
		cctvCategoryDepth3DataDtoList = cctvCategoryInfoDataDao.selectCctvCategoryDepth3List(daoParams);
		
		if(cctvCategoryDepth3DataDtoList.size() == 0) {
			try {
				return mapper.writeValueAsString(cctvCategoryDepth3ResultListVo);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		List<CctvCategoryDepth3ResultVo> cctvCategoryDepth3ResultVoList = new ArrayList<CctvCategoryDepth3ResultVo>();
		
		for(CctvCategoryDepth3DataDto cctvCategoryDepth3DataDto : cctvCategoryDepth3DataDtoList) {
			CctvCategoryDepth3ResultVo cctvCategoryDepth3ResultVo = new CctvCategoryDepth3ResultVo();
			cctvCategoryDepth3ResultVo.setId(cctvCategoryDepth3DataDto.getCctv_id());
			cctvCategoryDepth3ResultVo.setName(cctvCategoryDepth3DataDto.getLvl3_nm());
			cctvCategoryDepth3ResultVo.setSidoNm(cctvCategoryDepth3DataDto.getSido_nm());
			cctvCategoryDepth3ResultVo.setHasItems(Boolean.FALSE);
			cctvCategoryDepth3ResultVo.setLat(cctvCategoryDepth3DataDto.getLat());
			cctvCategoryDepth3ResultVo.setLon(cctvCategoryDepth3DataDto.getLon());
			
			if(!StringUtils.isEmpty(callCategory)) {
				cctvCategoryDepth3ResultVo.setGroupId(callCategory);
			}else {
				cctvCategoryDepth3ResultVo.setGroupId(cctvCategoryDepth3DataDto.getLvl2_id());
			}
			
			
			cctvCategoryDepth3ResultVoList.add(cctvCategoryDepth3ResultVo);
		}
		
		
		if(cctvCategoryDepth3ResultVoList.size() > 0) {
			cctvCategoryDepth3ResultListVo.setItems(cctvCategoryDepth3ResultVoList);
		}
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvCategoryDepth3ResultListVo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvCategoryDataServiceImpl] [getDepth3ListDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
}
