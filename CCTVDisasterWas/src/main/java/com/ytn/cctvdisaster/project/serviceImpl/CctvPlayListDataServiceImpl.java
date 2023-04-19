package com.ytn.cctvdisaster.project.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.dao.CctvInfoDataDao;
import com.ytn.cctvdisaster.project.dao.CctvPlayListInfoDataDao;
import com.ytn.cctvdisaster.project.dto.PlistDtlDto;
import com.ytn.cctvdisaster.project.dto.PlistDtlJoinCctvInfoDto;
import com.ytn.cctvdisaster.project.dto.PlistMstDto;
import com.ytn.cctvdisaster.project.result.vo.CctvPlayListDetailResultVo;
import com.ytn.cctvdisaster.project.result.vo.KtictCctvStatusResultVo;
import com.ytn.cctvdisaster.project.result.vo.RabbitMqResponseDataVo;
import com.ytn.cctvdisaster.project.service.CctvKtIctVideoInfoDataService;
import com.ytn.cctvdisaster.project.service.CctvPlayListDataService;
import com.ytn.cctvdisaster.project.vo.CctvPlayListDataVo;
import com.ytn.cctvdisaster.project.vo.CctvPlayListDetailDataListVo;
import com.ytn.cctvdisaster.project.vo.CctvPlayListDetailDataVo;

@Service
public class CctvPlayListDataServiceImpl implements CctvPlayListDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvPlayListDataServiceImpl.class);
	
	@Value("${rabbitmq.exchange.name}")
    private String exchange_name;
	
	@Value("${rabbitmq.routingkey.name}")
    private String routingKey;
	
	private CctvPlayListInfoDataDao cctvPlayListInfoDataDao;
	
	private CctvInfoDataDao cctvInfoDataDao;
	
	private RabbitTemplate rabbitTemplate;
	
	private CctvKtIctVideoInfoDataService cctvKtIctVideoInfoDataService;
	
	public CctvPlayListDataServiceImpl(CctvPlayListInfoDataDao cctvPlayListInfoDataDao, CctvInfoDataDao cctvInfoDataDao
										,RabbitTemplate rabbitTemplate, CctvKtIctVideoInfoDataService cctvKtIctVideoInfoDataService) {
		this.cctvPlayListInfoDataDao = cctvPlayListInfoDataDao;
		this.cctvInfoDataDao = cctvInfoDataDao;
		this.rabbitTemplate = rabbitTemplate;
		this.cctvKtIctVideoInfoDataService = cctvKtIctVideoInfoDataService;
	}
	
	
	@Override
	public String getCctvPlayListDataJson(String likeSearchKeyword) {
		String resultJsonData = "";
		List<PlistMstDto> plistMstDtoList = new ArrayList<PlistMstDto>();
		
		plistMstDtoList = cctvPlayListInfoDataDao.selectCctvPlayListInfoList(likeSearchKeyword);
		
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
		
		// DELETE
		try {
			resultCnt = cctvPlayListInfoDataDao.deleteCctvPlayListInfo(plistId);
		}catch (Exception e) {
			logger.error("[CctvPlayListDataServiceImpl] [deleteCctvPlayListDataJson] [Try Catch Delete Data] [Exception] ====> {}", e);
			logger.error("[CctvPlayListDataServiceImpl] [deleteCctvPlayListDataJson] [Try Catch Delete Data] [plistId] ====> {}", plistId);
		}
		
		int plistDtlCnt = cctvPlayListInfoDataDao.selectCctvPlayListDetailInfoCount(plistId);
		
		if(plistDtlCnt == 0) {
			return resultCnt;
		}
		
		// DETAIL DELETE
		try {
			resultCnt = cctvPlayListInfoDataDao.deleteCctvPlayListDetailInfo(plistId);
		}catch (Exception e) {
			logger.error("[CctvPlayListDataServiceImpl] [deleteCctvPlayListDataJson] [Try Catch Delete Detail Data] [Exception] ====> {}", e);
			logger.error("[CctvPlayListDataServiceImpl] [deleteCctvPlayListDataJson] [Try Catch Delete Detail Data] [plistId] ====> {}", plistId);
		}
		
		if(resultCnt > 1) {
			return 1;
		}
		
		return resultCnt;
	}
	
	
	@SuppressWarnings("deprecation")
	@Override
	public String getCctvPlayListDetailDataJson(String plistId) {
		String resultJsonData = "";
		ObjectMapper mapper = new ObjectMapper();
		List<PlistDtlJoinCctvInfoDto> PlistDtlJoinCctvInfoList = new ArrayList<PlistDtlJoinCctvInfoDto>();
		List<CctvPlayListDetailResultVo> cctvPlayListDetailResultVoList = new ArrayList<CctvPlayListDetailResultVo>();
		
		PlistDtlJoinCctvInfoList = cctvPlayListInfoDataDao.selectCctvPlayListDetailInfoList(plistId);
		
		if(PlistDtlJoinCctvInfoList.size() == 0) {
			try {
				return mapper.writeValueAsString(cctvPlayListDetailResultVoList);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		
		StringBuilder cctvIdStrBuild = new StringBuilder();
		
		for(PlistDtlJoinCctvInfoDto plistDtlJoinCctvInfoDto : PlistDtlJoinCctvInfoList) {
			CctvPlayListDetailResultVo cctvPlayListDetailResultVo = new CctvPlayListDetailResultVo();
			cctvPlayListDetailResultVo.setPlistId(plistId);
			cctvPlayListDetailResultVo.setPlistNo(plistDtlJoinCctvInfoDto.getPlist_no());
			cctvPlayListDetailResultVo.setCctvId(plistDtlJoinCctvInfoDto.getCctv_id());
			cctvPlayListDetailResultVo.setSrcNm(plistDtlJoinCctvInfoDto.getLvl1_nm());
			cctvPlayListDetailResultVo.setCctvNm(plistDtlJoinCctvInfoDto.getLvl3_nm());
			cctvPlayListDetailResultVo.setCaptionNm(plistDtlJoinCctvInfoDto.getCaption_nm());
			cctvPlayListDetailResultVo.setInsYmd(plistDtlJoinCctvInfoDto.getIns_ymd());
			cctvPlayListDetailResultVo.setInsTime(plistDtlJoinCctvInfoDto.getIns_time());
			cctvPlayListDetailResultVo.setUpdYmd(plistDtlJoinCctvInfoDto.getUpd_ymd());
			cctvPlayListDetailResultVo.setUpdTime(plistDtlJoinCctvInfoDto.getUpd_time());
			cctvPlayListDetailResultVo.setSrcGb(plistDtlJoinCctvInfoDto.getSrc_gb());
			
			if(plistDtlJoinCctvInfoDto.getSrc_gb().equals("K")) {
				cctvIdStrBuild.append(plistDtlJoinCctvInfoDto.getCctv_id()+",");
			}else {
				cctvPlayListDetailResultVo.setServiceYn("Y");
				cctvPlayListDetailResultVo.setStreamingUrl(plistDtlJoinCctvInfoDto.getUrl());
			}
			
			cctvPlayListDetailResultVoList.add(cctvPlayListDetailResultVo);
		}
		
		// CCTV STATUS Get
		List<KtictCctvStatusResultVo> ktictCctvStatusResultVoList = new ArrayList<KtictCctvStatusResultVo>();
		
		if(cctvIdStrBuild.length() > 0) {
			String cctvIdAllStr = cctvIdStrBuild.toString().substring(0, cctvIdStrBuild.toString().length()-1);
			
			ktictCctvStatusResultVoList = cctvKtIctVideoInfoDataService.getCctvStatusInfoListByKtIctDataJson(cctvIdAllStr);
		}
		
		/**
		 * TODO DB 연동 해서 분기하기
		 * 
		 * KT ICT CCTV ---> K
		 * YTN CCTV ---> Y
		 *
		 **/
		for(CctvPlayListDetailResultVo cctvPlayListDetailResultVo : cctvPlayListDetailResultVoList) {
			if(!cctvPlayListDetailResultVo.getSrcGb().equals("K")) {
				break;
			}
				
			if(ktictCctvStatusResultVoList.size() > 0) {
				for(KtictCctvStatusResultVo ktictCctvStatusResultVo:ktictCctvStatusResultVoList) {
					if(cctvPlayListDetailResultVo.getCctvId().equals(ktictCctvStatusResultVo.getCctvId())) {
						logger.info("[CctvPlayListDataServiceImpl] [getCctvPlayListDetailDataJson] [ktictCctvStatusResultVo] [ktictCctvStatusResultVo.getCctvId()] : {}", ktictCctvStatusResultVo.getCctvId());
						
						// cctv 서비스상태, 라이브상태가 정상이 아니라면, 건너뛰기.
						if(!(ktictCctvStatusResultVo.getServiceYn().equals("Y")
									&& ktictCctvStatusResultVo.getLiveAvailableYn().equals("Y"))) {
							cctvPlayListDetailResultVo.setServiceYn("N");
						}else {
							cctvPlayListDetailResultVo.setServiceYn("Y");
							
							String returnStream = "";
							
							// Streaming Url
							returnStream = cctvKtIctVideoInfoDataService.getCctvStreamingDateByKtIctJson(ktictCctvStatusResultVo.getCctvId());
							
							if(!StringUtils.isEmpty(returnStream)) {
								cctvPlayListDetailResultVo.setStreamingUrl(returnStream);
							}
						}
						
						break;
					}
				}
			}
		}
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvPlayListDetailResultVoList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvPlayListDataServiceImpl] [getCctvPlayListDetailDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	@SuppressWarnings("deprecation")
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int modifyCctvPlayListDetailDataJson(CctvPlayListDetailDataVo cctvPlayListDetailDataVo) {
		int resultCnt = 0;
		
		String plistId = cctvPlayListDetailDataVo.getPlistId();
		
		if(StringUtils.isEmpty(plistId)) {
			return resultCnt;
		}
		
		int playListCnt = cctvPlayListInfoDataDao.selectCctvPlayListInfoCount(plistId);
		
		if(playListCnt == 0) {
			return resultCnt;
		}
		
		// DELETE
		try {
			resultCnt = cctvPlayListInfoDataDao.deleteCctvPlayListDetailInfo(plistId);
		}catch (Exception e) {
			logger.error("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDetailDataJson] [Try Catch Delete Data] [Exception] ====> {}", e);
			logger.error("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDetailDataJson] [Try Catch Delete Data] [plistId] ====> {}", plistId);
		}
		
		int idx = 1;
		
		for(CctvPlayListDetailDataListVo cctvPlayListDetailDataListVo : cctvPlayListDetailDataVo.getItems()) {
			String cctvId = cctvPlayListDetailDataListVo.getCctvId();
			
			if(StringUtils.isEmpty(cctvId)) {
				continue;
			}
			
			// SELECT
			int cctvInfoCnt = cctvInfoDataDao.selectCctvIdInfoCount(cctvId);
			
			if(cctvInfoCnt == 0) {
				continue;
			}
			
			PlistDtlDto plistDtlDto = new PlistDtlDto();
			plistDtlDto.setPlist_id(plistId);
			plistDtlDto.setPlist_no(idx);
			plistDtlDto.setCctv_id(cctvId);
			plistDtlDto.setCaption_nm(cctvPlayListDetailDataListVo.getCaptionNm());
			
			// INSERT
			try {
				resultCnt = cctvPlayListInfoDataDao.insertCctvPlayListDetailInfo(plistDtlDto);
				idx++;
			}catch (Exception e) {
				logger.error("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDetailDataJson] [Try Catch INSERT Data] [Exception] ====> {}", e);
				logger.error("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDetailDataJson] [Try Catch INSERT Data] [plistId] ====> {}", plistId);
				logger.error("[CctvPlayListDataServiceImpl] [modifyCctvPlayListDetailDataJson] [Try Catch INSERT Data] [cctvId] ====> {}", cctvId);
			}
		}
		
		
		return resultCnt;
	}
	
	@Override
	public String modifyCctvPlayListLockDataJson(String lockYn) {
		RabbitMqResponseDataVo rabbitMqResponseDataVo = new RabbitMqResponseDataVo();
		rabbitMqResponseDataVo.setCctvId("1234567");
		rabbitMqResponseDataVo.setLockYn("disaster1");
		
		//logger.info("[CctvPlayListDataServiceImpl] [modifyCctvPlayListLockDataJson] rabbitMqResponseDataVo : {}", rabbitMqResponseDataVo);
		try {
			rabbitTemplate.convertAndSend(exchange_name, routingKey, rabbitMqResponseDataVo);
			
			rabbitMqResponseDataVo.setLockYn("disaster2");
			rabbitTemplate.convertAndSend(exchange_name, "ytn.cctv.disaster2", rabbitMqResponseDataVo);
			
			rabbitMqResponseDataVo.setLockYn("disaster3");
			rabbitTemplate.convertAndSend(exchange_name, "ytn.cctv.disaster3", rabbitMqResponseDataVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return null;
	}
}
