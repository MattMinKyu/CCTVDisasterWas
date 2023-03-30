package com.ytn.cctvdisaster.project.serviceImpl;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.dto.CctvMapResearchDto;
import com.ytn.cctvdisaster.project.result.vo.CctvMapResearchResultVo;
import com.ytn.cctvdisaster.project.result.vo.KtictCctvStatusResultVo;
import com.ytn.cctvdisaster.project.result.vo.NaverMapLocalResearchResultVo;
import com.ytn.cctvdisaster.project.service.CctvKtIctVideoInfoDataService;
import com.ytn.cctvdisaster.project.service.CctvMapDataResearchService;
import com.ytn.cctvdisaster.project.vo.NaverMapLocalResearchVo;

@Service
public class CctvMapDataResearchServiceImpl implements CctvMapDataResearchService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvMapDataResearchServiceImpl.class);
	
	private CctvKtIctVideoInfoDataService cctvKtIctVideoInfoDataService;
	
	@Value("${naver.map.api.uri}")
    private String naverMapApiUrl;
	
	@Value("${naver.map.api.path}")
    private String naverMapApiPath;
	
	@Value("${naver.map.api.clientid}")
    private String naverMapApiClientId;
	
	@Value("${naver.map.api.secret}")
    private String naverMapApiSecret;
	
	
	public CctvMapDataResearchServiceImpl(CctvKtIctVideoInfoDataService cctvKtIctVideoInfoDataService) {
		this.cctvKtIctVideoInfoDataService = cctvKtIctVideoInfoDataService;
	}
	
	@Override
	public String getLocalListByNaverMapDataJson(NaverMapLocalResearchVo naverMapLocalResearchVo) {
		
		String resultJsonData = "";
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		URI uri = UriComponentsBuilder
	                .fromUriString(naverMapApiUrl)
	                .path(naverMapApiPath)
	                .queryParam("query", naverMapLocalResearchVo.getQuery())
	                .queryParam("display", naverMapLocalResearchVo.getDisplay())
	                .queryParam("start", naverMapLocalResearchVo.getStart())
	                .queryParam("sort", naverMapLocalResearchVo.getSort())
	                // UTF-8인코딩.
	                .encode(Charset.forName("UTF-8"))
	                .build()
	                .toUri();

	    // header �� �߰� get���� ��û�ϱ� ������ Void�� �޴´�.
	    RequestEntity<Void> req = RequestEntity
	            .get(uri)
	            .header("X-Naver-Client-Id",naverMapApiClientId)
	            .header("X-Naver-Client-Secret",naverMapApiSecret)
	            .build();

	    ResponseEntity<NaverMapLocalResearchResultVo> response = restTemplate.exchange(req, NaverMapLocalResearchResultVo.class);
	    response.getBody().setStatus(response.getStatusCode().value());
	    
		try {
			resultJsonData = mapper.writeValueAsString(response.getBody());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvMapDataResearchServiceImpl] [getAddressListByNaverMapDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
	@SuppressWarnings({ "deprecation", "unused" })
	@Override
	public String getCctvListByLocalMapDataJson(CctvMapResearchDto cctvMapResearchDto, List<String> testCctvId) {
		
		logger.info("[CctvMapDataResearchServiceImpl] [getCctvListByLocalMapDataJson] Start ~!!!! ");
		
		String resultJsonData = "";
		List<CctvMapResearchResultVo> cctvMapResearchResultVoList = new ArrayList<CctvMapResearchResultVo>();
		
		/*
		 * TODO DB 연동 해서 분기하기
		 * 
		 * KT ICT CCTV, 산림청
		 */
		String testTypeStr = "ktict";
		
		if(testTypeStr.equals("ktict")) {
			List<KtictCctvStatusResultVo> ktictCctvStatusResultVoList = new ArrayList<KtictCctvStatusResultVo>();
			StringBuilder cctvIdStrBuild = new StringBuilder();
			String cctvIdAllStr = "";
			
			for(int i=0;i<testCctvId.size();i++) {
				if(i!=0) {
					cctvIdStrBuild.append(",");
				}
				cctvIdStrBuild.append(testCctvId.get(i));
			}
			
			cctvIdAllStr = cctvIdStrBuild.toString();
			
			// CCTV STATUS Get
			ktictCctvStatusResultVoList = cctvKtIctVideoInfoDataService.getCctvStatusInfoListByKtIctDataJson(cctvIdStrBuild.toString());
			
			//0이면, return.
			if(ktictCctvStatusResultVoList.size() == 0) {
				return resultJsonData;
			}
			
			for(KtictCctvStatusResultVo ktictCctvStatusResultVo:ktictCctvStatusResultVoList) {
				logger.info("[CctvMapDataResearchServiceImpl] [getCctvListByLocalMapDataJson] [ktictCctvStatusResultVo] : {}", ktictCctvStatusResultVo);
				
				// cctv 서비스상태, 라이브상태가 정상이 아니라면, 건너뛰기.
				if(!ktictCctvStatusResultVo.getServiceYn().equals("Y")
							&&ktictCctvStatusResultVo.getLiveAvailableYn().equals("Y")) {
					continue;
				}
				
				String returnStream = "";
				CctvMapResearchResultVo cctvMapResearchResultVo = new CctvMapResearchResultVo();
				
				// Streaming Url & ThumbnailUrl Get
				returnStream = cctvKtIctVideoInfoDataService.getCctvStreamingDateByKtIctJson(ktictCctvStatusResultVo.getCctvId());
				
				if(StringUtils.isEmpty(returnStream)) {
					continue;
				}
				
				cctvMapResearchResultVo.setCctvId(ktictCctvStatusResultVo.getCctvId());
				cctvMapResearchResultVo.setStreamingUrl(returnStream);
				cctvMapResearchResultVo.setThumbnailUrl(cctvKtIctVideoInfoDataService.getCctvThumbnailMakeUrlByKtIct(ktictCctvStatusResultVo.getCctvId()));
				cctvMapResearchResultVo.setServiceYn(ktictCctvStatusResultVo.getServiceYn());
				cctvMapResearchResultVo.setLiveAvailableYn(ktictCctvStatusResultVo.getLiveAvailableYn());
				
				cctvMapResearchResultVoList.add(cctvMapResearchResultVo);
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvMapResearchResultVoList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvMapDataResearchServiceImpl] [getAddressListByNaverMapDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
}
