package com.ytn.cctvdisaster.project.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.dao.CctvInfoDataDao;
import com.ytn.cctvdisaster.project.dto.CctvInfoDataDto;
import com.ytn.cctvdisaster.project.result.vo.CctvInfoStreamingUrlResultVo;
import com.ytn.cctvdisaster.project.result.vo.CctvInfoThumbnailUrlResultVo;
import com.ytn.cctvdisaster.project.result.vo.KtictCctvStatusResultVo;
import com.ytn.cctvdisaster.project.service.CctvEtcVideoInfoDataService;
import com.ytn.cctvdisaster.project.service.CctvKtIctVideoInfoDataService;
import com.ytn.cctvdisaster.project.service.CctvVideoInfoDataService;

@Service
public class CctvVideoInfoDataServiceImpl implements CctvVideoInfoDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvVideoInfoDataServiceImpl.class);
	
	@Value("${ytn.thumbnail.uri}")
    private String ytnThumbnailUri;
	
	@Value("${ytn.thumbnail.path}")
    private String ytnThumbnailPath;
	
	private CctvInfoDataDao cctvInfoDataDao;
	
	private CctvKtIctVideoInfoDataService cctvKtIctVideoInfoDataService;
	
	private CctvEtcVideoInfoDataService cctvEtcVideoInfoDataService;
	
	public CctvVideoInfoDataServiceImpl(CctvInfoDataDao cctvInfoDataDao, CctvKtIctVideoInfoDataService cctvKtIctVideoInfoDataService
											,CctvEtcVideoInfoDataService cctvEtcVideoInfoDataService) {
		this.cctvInfoDataDao = cctvInfoDataDao;
		this.cctvKtIctVideoInfoDataService = cctvKtIctVideoInfoDataService;
		this.cctvEtcVideoInfoDataService = cctvEtcVideoInfoDataService;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public String getCctvStreamingUrlMapDataJson(List<String> cctvIdList) {
		logger.info("[CctvVideoInfoDataServiceImpl] [getCctvStreamUrlMapDataJson] Start ~!!!");
		
		String resultJsonData = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CctvInfoDataDto> cctvInfoDataDto = new ArrayList<CctvInfoDataDto>();
		List<CctvInfoStreamingUrlResultVo> cctvInfoStreamingUrlResultVoList = new ArrayList<CctvInfoStreamingUrlResultVo>();
		
		Map<String, Object> daoParam = new HashMap<String, Object>();
		daoParam.put("cctvIdList", cctvIdList);
		
		cctvInfoDataDto = cctvInfoDataDao.selectCctvIdInfoList(daoParam);
		
		if(cctvInfoDataDto.size() == 0) {
			try {
				return mapper.writeValueAsString(cctvInfoStreamingUrlResultVoList);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		StringBuilder cctvIdStrBuild = new StringBuilder();
		
		for(CctvInfoDataDto cctvInfoDataDtoList : cctvInfoDataDto) {
			CctvInfoStreamingUrlResultVo cctvInfoStreamingUrlResultVo = new CctvInfoStreamingUrlResultVo();
			
			cctvInfoStreamingUrlResultVo.setCctvId(cctvInfoDataDtoList.getCctv_id());
			cctvInfoStreamingUrlResultVo.setCctvNm(cctvInfoDataDtoList.getLvl3_nm());
			cctvInfoStreamingUrlResultVo.setSrcGb(cctvInfoDataDtoList.getSrc_gb());
			cctvInfoStreamingUrlResultVo.setSrcNm(cctvInfoDataDtoList.getLvl1_nm());
			cctvInfoStreamingUrlResultVo.setSidoNm(cctvInfoDataDtoList.getSido_nm());
			cctvInfoStreamingUrlResultVo.setLat(cctvInfoDataDtoList.getLat());
			cctvInfoStreamingUrlResultVo.setLon(cctvInfoDataDtoList.getLon());
			
		
			if(cctvInfoDataDtoList.getSrc_gb().equals("K")) {
				cctvIdStrBuild.append(cctvInfoDataDtoList.getCctv_id()+",");
			}else {
				cctvInfoStreamingUrlResultVo.setServiceYn("Y");
				cctvInfoStreamingUrlResultVo.setStreamingUrl(cctvInfoDataDtoList.getUrl());
			}
			
			cctvInfoStreamingUrlResultVoList.add(cctvInfoStreamingUrlResultVo);
		}
		
		
		/*
		// CCTV STATUS Get
		List<KtictCctvStatusResultVo> ktictCctvStatusResultVoList = new ArrayList<KtictCctvStatusResultVo>();
		
		if(cctvIdStrBuild.length() > 0) {
			String cctvIdAllStr = cctvIdStrBuild.toString().substring(0, cctvIdStrBuild.toString().length()-1);
			
			ktictCctvStatusResultVoList = cctvKtIctVideoInfoDataService.getCctvStatusInfoListByKtIctDataJson(cctvIdAllStr);
		}
		*/
		
		/**
		 * TODO DB 연동 해서 분기하기
		 * 
		 * KT ICT CCTV ---> K
		 * YTN CCTV ---> Y
		 *
		 **/
		for(CctvInfoStreamingUrlResultVo cctvInfoStreamingUrlResultVo : cctvInfoStreamingUrlResultVoList) {
			if(!cctvInfoStreamingUrlResultVo.getSrcGb().equals("K")) {
				break;
			}
			
			/*
			if(ktictCctvStatusResultVoList.size() > 0) {
				for(KtictCctvStatusResultVo ktictCctvStatusResultVo:ktictCctvStatusResultVoList) {
					if(cctvInfoStreamingUrlResultVo.getCctvId().equals(ktictCctvStatusResultVo.getCctvId())) {
						logger.info("[CctvVideoInfoDataServiceImpl] [getCctvStreamingUrlMapDataJson] [ktictCctvStatusResultVo] [ktictCctvStatusResultVo.getCctvId()] : {}", ktictCctvStatusResultVo.getCctvId());
						
						// cctv 서비스상태, 라이브상태가 정상이 아니라면, 건너뛰기.
						if(!(ktictCctvStatusResultVo.getServiceYn().equals("Y")
									&& ktictCctvStatusResultVo.getLiveAvailableYn().equals("Y"))) {
							cctvInfoStreamingUrlResultVo.setServiceYn("N");
						}else {
							cctvInfoStreamingUrlResultVo.setServiceYn("Y");
							
							String returnStream = "";
							
							// Streaming Url
							returnStream = cctvKtIctVideoInfoDataService.getCctvStreamingDateByKtIctJson(ktictCctvStatusResultVo.getCctvId());
							
							if(!StringUtils.isEmpty(returnStream)) {
								cctvInfoStreamingUrlResultVo.setStreamingUrl(returnStream);
							}else {
								cctvInfoStreamingUrlResultVo.setServiceYn("N");
							}
						}
						
						break;
					}
				}
			}
			*/
			
			String returnStream = "";
			
			// Streaming Url
			returnStream = cctvKtIctVideoInfoDataService.getCctvStreamingDateByKtIctJson(cctvInfoStreamingUrlResultVo.getCctvId());
			
			if(!StringUtils.isEmpty(returnStream)) {
				if(returnStream.indexOf("error") != -1) {
					cctvInfoStreamingUrlResultVo.setServiceYn("N");
				}else {
					cctvInfoStreamingUrlResultVo.setServiceYn("Y");
					cctvInfoStreamingUrlResultVo.setStreamingUrl(returnStream);
				}
				
			}else {
				cctvInfoStreamingUrlResultVo.setServiceYn("N");
			}
			
		}
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvInfoStreamingUrlResultVoList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvVideoInfoDataServiceImpl] [getCctvStreamingUrlMapDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public String getCctvThumbnailUrlMapDataJson(List<String> cctvIdList) {
		logger.info("[CctvVideoInfoDataServiceImpl] [getCctvThumbnailUrlMapDataJson] Start ~!!!");
		
		String resultJsonData = "";
		String cctvIdListStrBuilderStr = "";
		List<CctvInfoDataDto> cctvInfoDataDto = new ArrayList<CctvInfoDataDto>();
		List<CctvInfoThumbnailUrlResultVo> cctvInfoThumbnailUrlResultVoList = new ArrayList<CctvInfoThumbnailUrlResultVo>();
		StringBuilder cctvIdListStrBuilder = new StringBuilder();
		Map<Integer, String> overlapCctvIdMap = new HashMap<Integer, String>();
		
		
		ObjectMapper mapper = new ObjectMapper();
		
		for(int i=0; i<cctvIdList.size(); i++) {
			
			if(cctvIdListStrBuilder.indexOf(cctvIdList.get(i)) >= 0) {
				overlapCctvIdMap.put(i, cctvIdList.get(i));
			}else {
				cctvIdListStrBuilder.append(cctvIdList.get(i)+",");
			}
		}
		
		cctvIdListStrBuilderStr = cctvIdListStrBuilder.substring(0, cctvIdListStrBuilder.length()-1).toString();
		
		logger.info("[CctvVideoInfoDataServiceImpl] [getCctvThumbnailUrlMapDataJson] cctvIdListStrBuilderStr : {}", cctvIdListStrBuilderStr);
		
		Map<String, Object> daoParam = new HashMap<String, Object>();
		daoParam.put("cctvIdList", cctvIdList);
		daoParam.put("cctvIdListStr", cctvIdListStrBuilderStr);
		
		cctvInfoDataDto = cctvInfoDataDao.selectCctvIdInfoList(daoParam);
		
		if(cctvInfoDataDto.size() == 0) {
			try {
				return mapper.writeValueAsString(cctvInfoThumbnailUrlResultVoList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		StringBuilder cctvIdStrBuild = new StringBuilder();
		
		for(CctvInfoDataDto cctvInfoDataDtoList : cctvInfoDataDto) {
			CctvInfoThumbnailUrlResultVo cctvInfoThumbnailUrlResultVo = new CctvInfoThumbnailUrlResultVo();
			
			cctvInfoThumbnailUrlResultVo.setCctvId(cctvInfoDataDtoList.getCctv_id());
			cctvInfoThumbnailUrlResultVo.setCctvNm(cctvInfoDataDtoList.getLvl3_nm());
			cctvInfoThumbnailUrlResultVo.setSrcGb(cctvInfoDataDtoList.getSrc_gb());
			cctvInfoThumbnailUrlResultVo.setSrcNm(cctvInfoDataDtoList.getLvl1_nm());
			cctvInfoThumbnailUrlResultVo.setSidoNm(cctvInfoDataDtoList.getSido_nm());
			cctvInfoThumbnailUrlResultVo.setLat(cctvInfoDataDtoList.getLat());
			cctvInfoThumbnailUrlResultVo.setLon(cctvInfoDataDtoList.getLon());
			
		
			if(cctvInfoDataDtoList.getSrc_gb().equals("K")) {
				cctvIdStrBuild.append(cctvInfoDataDtoList.getCctv_id()+",");
			}else {
				cctvInfoThumbnailUrlResultVo.setStreamingUrl(cctvInfoDataDtoList.getUrl());
			}
			
			cctvInfoThumbnailUrlResultVoList.add(cctvInfoThumbnailUrlResultVo);
		}
		
		/*
		// CCTV STATUS Get
		List<KtictCctvStatusResultVo> ktictCctvStatusResultVoList = new ArrayList<KtictCctvStatusResultVo>();
		
		if(cctvIdStrBuild.length() > 0) {
			String cctvIdAllStr = cctvIdStrBuild.toString().substring(0, cctvIdStrBuild.toString().length()-1);
			
			ktictCctvStatusResultVoList = cctvKtIctVideoInfoDataService.getCctvStatusInfoListByKtIctDataJson(cctvIdAllStr);
		}
		*/
		
		
		/**
		 * TODO DB 연동 해서 분기하기
		 * 
		 * KT ICT CCTV ---> K
		 * YTN CCTV ---> Y
		 *
		 **/
		for(CctvInfoThumbnailUrlResultVo cctvInfoThumbnailUrlResultVo : cctvInfoThumbnailUrlResultVoList) {
			if(cctvInfoThumbnailUrlResultVo.getSrcGb().equals("Y")) {
				
				if(!StringUtils.isEmpty(cctvInfoThumbnailUrlResultVo.getStreamingUrl())) {
					String thumbnailCreateResult = "";
					
					thumbnailCreateResult = cctvEtcVideoInfoDataService.getCctvVideoThumbnailDataJson(cctvInfoThumbnailUrlResultVo.getStreamingUrl(), cctvInfoThumbnailUrlResultVo.getCctvId());
					
					if(StringUtils.isEmpty(thumbnailCreateResult)) {
						cctvInfoThumbnailUrlResultVo.setServiceYn("N");
					}else {
						cctvInfoThumbnailUrlResultVo.setServiceYn("Y");
						cctvInfoThumbnailUrlResultVo.setThumbnailUrl(ytnThumbnailUri+ytnThumbnailPath+"/"+thumbnailCreateResult);
					}
				}
				continue;
			}else if(cctvInfoThumbnailUrlResultVo.getSrcGb().equals("K")) {
				/*
				if(ktictCctvStatusResultVoList.size() > 0) {
					for(KtictCctvStatusResultVo ktictCctvStatusResultVo:ktictCctvStatusResultVoList) {
						if(cctvInfoThumbnailUrlResultVo.getCctvId().equals(ktictCctvStatusResultVo.getCctvId())) {
							logger.info("[CctvVideoInfoDataServiceImpl] [getCctvThumbnailUrlMapDataJson] [ktictCctvStatusResultVo.getCctvId()] : {}", ktictCctvStatusResultVo.getCctvId());
							
							// cctv 서비스상태, 라이브상태가 정상이 아니라면, 건너뛰기.s
							if(!(ktictCctvStatusResultVo.getServiceYn().equals("Y")
										&& ktictCctvStatusResultVo.getLiveAvailableYn().equals("Y"))) {
								cctvInfoThumbnailUrlResultVo.setServiceYn("N");
							}else {
								cctvInfoThumbnailUrlResultVo.setServiceYn("Y");
								
								String returnThumbnailUrl = "";
								
								// Thumbnail Url
								returnThumbnailUrl = cctvKtIctVideoInfoDataService.getCctvThumbnailMakeUrlByKtIct(ktictCctvStatusResultVo.getCctvId());
								
								if(!StringUtils.isEmpty(returnThumbnailUrl)) {
									cctvInfoThumbnailUrlResultVo.setThumbnailUrl(returnThumbnailUrl);
								}
							}
							
							break;
						}
					}
				}
				*/
				
				String returnThumbnailUrl = "";
				
				// Thumbnail Url
				returnThumbnailUrl = cctvKtIctVideoInfoDataService.getCctvThumbnailMakeUrlByKtIct(cctvInfoThumbnailUrlResultVo.getCctvId());
				
				if(!StringUtils.isEmpty(returnThumbnailUrl)) {
					cctvInfoThumbnailUrlResultVo.setServiceYn("Y");
					cctvInfoThumbnailUrlResultVo.setThumbnailUrl(returnThumbnailUrl);
				}else {
					cctvInfoThumbnailUrlResultVo.setServiceYn("N");
				}
				
			}
		}
		
		
		/**
		 * 중복 cctvId 데이터, 제자리에 밀어넣기.
		 */
		if(!overlapCctvIdMap.isEmpty()) {
			for(Integer key : overlapCctvIdMap.keySet()){
	            for(CctvInfoThumbnailUrlResultVo cctvInfoThumbnailUrlResultVo : cctvInfoThumbnailUrlResultVoList) {
	            	if(overlapCctvIdMap.get(key).equals(cctvInfoThumbnailUrlResultVo.getCctvId())) {
	            		CctvInfoThumbnailUrlResultVo cctvInfoThumbnailUrlResultVoTemp = new CctvInfoThumbnailUrlResultVo();
	            		
	            		cctvInfoThumbnailUrlResultVoTemp = cctvInfoThumbnailUrlResultVo;
	            		cctvInfoThumbnailUrlResultVoList.add(key, cctvInfoThumbnailUrlResultVoTemp);
	            		break;
	            	}
	            }
	            
	        }
			
		}
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvInfoThumbnailUrlResultVoList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvVideoInfoDataServiceImpl] [getCctvThumbnailUrlMapDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	
}
