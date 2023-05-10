package com.ytn.cctvdisaster.project.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.dao.CctvBroadCastInfoDataDao;
import com.ytn.cctvdisaster.project.dto.CctvBroadCastLockDto;
import com.ytn.cctvdisaster.project.result.vo.CctvBroadCastLockInfoResultVo;
import com.ytn.cctvdisaster.project.result.vo.RabbitMqBroadCastLockDataRequestVo;
import com.ytn.cctvdisaster.project.service.CctvBroadCastDataService;
import com.ytn.cctvdisaster.project.vo.CctvBroadCastDataVo;

@Service
public class CctvBroadCastDataServiceImpl implements CctvBroadCastDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvBroadCastDataServiceImpl.class);
	
	@Value("${rabbitmq.exchange.name}")
    private String exchangeName;
	
	@Value("${rabbitmq.routingkey.name}")
    private String routingKey;
	
	private CctvBroadCastInfoDataDao cctvBroadCastInfoDataDao;
	
	private RabbitTemplate rabbitTemplate;
	
	public CctvBroadCastDataServiceImpl(CctvBroadCastInfoDataDao cctvBroadCastInfoDataDao, RabbitTemplate rabbitTemplate) {
		this.cctvBroadCastInfoDataDao = cctvBroadCastInfoDataDao;
		this.rabbitTemplate = rabbitTemplate;
	}
	
	
	@Override
	public String getCctvBroadCastLockInfoDataJson(String remoteIp) {
		String resultJsonData = "";
		
		CctvBroadCastLockDto cctvBroadCastLockDto = cctvBroadCastInfoDataDao.selectCctvBroadCastLockInfoData();
		
		if(cctvBroadCastLockDto == null) {
			return null;
		}
		
		Boolean myLockResultType = Boolean.FALSE;
		CctvBroadCastLockInfoResultVo cctvBroadCastLockInfoResultVo = new CctvBroadCastLockInfoResultVo();
		
		cctvBroadCastLockInfoResultVo.setPlistId(cctvBroadCastLockDto.getPlist_id());
		cctvBroadCastLockInfoResultVo.setPlistNm(cctvBroadCastLockDto.getPlist_nm());
		cctvBroadCastLockInfoResultVo.setUserNm(cctvBroadCastLockDto.getUser_nm());
		cctvBroadCastLockInfoResultVo.setCaptionYn(cctvBroadCastLockDto.getCaption_yn());
		cctvBroadCastLockInfoResultVo.setAutoYn(cctvBroadCastLockDto.getAuto_yn());
		cctvBroadCastLockInfoResultVo.setAutoTime(cctvBroadCastLockDto.getAuto_time());
		cctvBroadCastLockInfoResultVo.setLockYn(cctvBroadCastLockDto.getLock_yn());
		cctvBroadCastLockInfoResultVo.setLockIp(cctvBroadCastLockDto.getLock_ip());
		cctvBroadCastLockInfoResultVo.setLockYmd(cctvBroadCastLockDto.getLock_ymd());
		cctvBroadCastLockInfoResultVo.setLockTime(cctvBroadCastLockDto.getLock_time().substring(0, cctvBroadCastLockDto.getLock_time().length()-3));
		
		if(remoteIp.equals(cctvBroadCastLockDto.getLock_ip())) {
			myLockResultType = Boolean.TRUE;
		}
		
		cctvBroadCastLockInfoResultVo.setMyLockYn(myLockResultType);
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvBroadCastLockInfoResultVo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvBroadCastDataServiceImpl] [getCctvBroadCastLockInfoDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	@Override
	public int modifyCctvBroadCastLockDataJson(String lockYn, String plistId, String remoteIp) {
		int resultCnt = 0;
		
		CctvBroadCastDataVo cctvBroadCastDataVo = new CctvBroadCastDataVo();
		cctvBroadCastDataVo.setLockYn(lockYn);
		cctvBroadCastDataVo.setPlistId(plistId);
		cctvBroadCastDataVo.setLockIp(remoteIp);
		
		int lockTargetCnt = cctvBroadCastInfoDataDao.selectCctvLockYnCount(cctvBroadCastDataVo);
		
		if(lockTargetCnt > 0) {
			return resultCnt;
		}
		
		logger.info("[CctvBroadCastDataServiceImpl] [modifyCctvBroadCastLockDataJson] resultCnt : {}", resultCnt);
		
		// UPDATE
		try {
			resultCnt = cctvBroadCastInfoDataDao.modifyCctvBroadCastLockData(cctvBroadCastDataVo);
		}catch (Exception e) {
			logger.error("[CctvBroadCastDataServiceImpl] [modifyCctvBroadCastLockDataJson] [Try Catch Delete Data] [Exception] ====> {}", e);
			logger.error("[CctvBroadCastDataServiceImpl] [modifyCctvBroadCastLockDataJson] [Try Catch Delete Data] [plistId] ====> {}", plistId);
		}
		
		if(resultCnt == 1) {
			/*
			RabbitMqBroadCastLockDataRequestVo rabbitMqBroadCastLockDataRequestVo = new RabbitMqBroadCastLockDataRequestVo();
			rabbitMqBroadCastLockDataRequestVo.setPlistId(plistId);
			rabbitMqBroadCastLockDataRequestVo.setLockYn(lockYn);
			
			rabbitTemplate.convertAndSend(exchangeName, routingKey, rabbitMqBroadCastLockDataRequestVo);
			*/
			
			CctvBroadCastLockDto cctvBroadCastLockDto = cctvBroadCastInfoDataDao.selectCctvBroadCastRabbitMqSendData(cctvBroadCastDataVo);
			
			if(cctvBroadCastLockDto == null) {
				return 0;
			}
			
			Boolean myLockResultType = Boolean.FALSE;
			CctvBroadCastLockInfoResultVo cctvBroadCastLockInfoResultVo = new CctvBroadCastLockInfoResultVo();
			
			cctvBroadCastLockInfoResultVo.setPlistId(cctvBroadCastLockDto.getPlist_id());
			cctvBroadCastLockInfoResultVo.setPlistNm(cctvBroadCastLockDto.getPlist_nm());
			cctvBroadCastLockInfoResultVo.setUserNm(cctvBroadCastLockDto.getUser_nm());
			cctvBroadCastLockInfoResultVo.setCaptionYn(cctvBroadCastLockDto.getCaption_yn());
			cctvBroadCastLockInfoResultVo.setAutoYn(cctvBroadCastLockDto.getAuto_yn());
			cctvBroadCastLockInfoResultVo.setAutoTime(cctvBroadCastLockDto.getAuto_time());
			cctvBroadCastLockInfoResultVo.setLockYn(cctvBroadCastLockDto.getLock_yn());
			cctvBroadCastLockInfoResultVo.setLockIp(cctvBroadCastLockDto.getLock_ip());
			cctvBroadCastLockInfoResultVo.setLockYmd(cctvBroadCastLockDto.getLock_ymd());
			cctvBroadCastLockInfoResultVo.setLockTime(cctvBroadCastLockDto.getLock_time().substring(0, cctvBroadCastLockDto.getLock_time().length()-3));
			
			if(remoteIp.equals(cctvBroadCastLockDto.getLock_ip())) {
				myLockResultType = Boolean.TRUE;
			}
			
			cctvBroadCastLockInfoResultVo.setMyLockYn(myLockResultType);
			
			rabbitTemplate.convertAndSend(exchangeName, routingKey, cctvBroadCastLockInfoResultVo);
		}
		
		return resultCnt;
	}
	
}
