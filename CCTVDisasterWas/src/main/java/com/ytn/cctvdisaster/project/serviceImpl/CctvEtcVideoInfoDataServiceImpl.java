package com.ytn.cctvdisaster.project.serviceImpl;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ytn.cctvdisaster.project.service.CctvEtcVideoInfoDataService;
import com.ytn.cctvdisaster.project.util.FFmpegUtil;

@Service
public class CctvEtcVideoInfoDataServiceImpl implements CctvEtcVideoInfoDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvEtcVideoInfoDataServiceImpl.class);
	
	private FFmpegUtil fFmpegUtil;
	
	public CctvEtcVideoInfoDataServiceImpl(FFmpegUtil fFmpegUtil) {
		this.fFmpegUtil = fFmpegUtil;
	}
	
	@Override
	public HashMap<String, Object> getCctvVideoThumbnailDataJson(String inputFilePath) {
		Boolean thumbnailCreateResult = Boolean.FALSE;
		
		
		// 썸네일이미지명 난수 생성
		String imageName = RandomStringUtils.randomAlphanumeric(10);
		
		// 썸네일 이미지 생성 함수 호출
		try {
			fFmpegUtil.exportThumbnailImg(inputFilePath, imageName, "jpg");
			thumbnailCreateResult = Boolean.TRUE;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			// CCTV커넥션 문제 (연결문제)
			logger.info("[CctvVideoInfoDataServiceImpl] [getCctvVideoThumbnailDataJson] No Connection =====> {}", e.getMessage());
		}
		
		logger.info("[CctvVideoInfoDataServiceImpl] [getCctvVideoThumbnailDataJson] thumbnailCreateResult ====> {}", thumbnailCreateResult);
		
		
		return null;
	}
}
