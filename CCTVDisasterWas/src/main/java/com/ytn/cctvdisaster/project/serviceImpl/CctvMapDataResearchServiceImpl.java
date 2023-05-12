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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.dao.CctvInfoDataDao;
import com.ytn.cctvdisaster.project.dto.CctvInfoDataDto;
import com.ytn.cctvdisaster.project.result.vo.NaverMapAddressResearchResultListVo;
import com.ytn.cctvdisaster.project.result.vo.NaverMapAddressResearchResultVo;
import com.ytn.cctvdisaster.project.result.vo.NaverMapLocalResearchResultListVo;
import com.ytn.cctvdisaster.project.result.vo.NaverMapLocalResearchResultVo;
import com.ytn.cctvdisaster.project.service.CctvMapDataResearchService;
import com.ytn.cctvdisaster.project.vo.CctvCoordinateMapListResearchVo;
import com.ytn.cctvdisaster.project.vo.NaverMapLocalResearchVo;

@Service
public class CctvMapDataResearchServiceImpl implements CctvMapDataResearchService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvMapDataResearchServiceImpl.class);
	
	private CctvInfoDataDao cctvInfoDataDao;
	
	@Value("${naver.map.api.uri}")
    private String naverMapApiUrl;
	
	@Value("${naver.map.api.path}")
    private String naverMapApiPath;
	
	@Value("${naver.map.api.clientid}")
    private String naverMapApiClientId;
	
	@Value("${naver.map.api.secret}")
    private String naverMapApiSecret;
	
	@Value("${navercloud.map.api.uri}")
	private String naverCloudMapApiUrl;
	
	@Value("${navercloud.map.api.path}")
	private String naverCloudMapApiPath;
	
	@Value("${navercloud.map.api.id}")
	private String naverCloudMapApiKeyId;
	
	@Value("${navercloud.map.api.key}")
	private String naverCloudMapApiKey;
	
	
	public CctvMapDataResearchServiceImpl(CctvInfoDataDao cctvInfoDataDao) {
		this.cctvInfoDataDao = cctvInfoDataDao;
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
	    
	    if(response.getBody().getItems().size() > 0) {
	    	for(NaverMapLocalResearchResultListVo naverMapLocalResearchResultListVo : response.getBody().getItems()) {
	    		naverMapLocalResearchResultListVo.setReturnType("local");
	    	}
	    	
	    	try {
				resultJsonData = mapper.writeValueAsString(response.getBody());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				logger.error("[CctvMapDataResearchServiceImpl] [getAddressListByNaverMapDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
			}
	    	
	    	return resultJsonData;
	    }
		
		URI uri2 = UriComponentsBuilder
	                .fromUriString(naverCloudMapApiUrl)
	                .path(naverCloudMapApiPath)
	                .queryParam("query", naverMapLocalResearchVo.getQuery())
	                .queryParam("count", 5)
	                // UTF-8인코딩.
	                .encode(Charset.forName("UTF-8"))
	                .build()
	                .toUri();
	
	    // header �� �߰� get���� ��û�ϱ� ������ Void�� �޴´�.
	    RequestEntity<Void> req2 = RequestEntity
	            .get(uri2)
	            .header("X-NCP-APIGW-API-KEY-ID",naverCloudMapApiKeyId)
	            .header("X-NCP-APIGW-API-KEY",naverCloudMapApiKey)
	            .build();
	
	    ResponseEntity<NaverMapAddressResearchResultVo> response2 = restTemplate.exchange(req2, NaverMapAddressResearchResultVo.class);
	    NaverMapAddressResearchResultVo naverMapAddressResearchResultVo = (NaverMapAddressResearchResultVo) response2.getBody();
	    
	    if(naverMapAddressResearchResultVo.getAddresses().size() == 0) {
	    	try {
	    		resultJsonData = mapper.writeValueAsString(naverMapAddressResearchResultVo);
	    	} catch (JsonProcessingException e) {
	    		e.printStackTrace();
	    		logger.error("[CctvMapDataResearchServiceImpl] [naverMapAddressResearchResultVo] [Try Catch resultJsonData] [Exception] ====> {}", e);
	    	}
	    		return resultJsonData;
	    }
	    
	    NaverMapLocalResearchResultVo naverMapLocalResearchResultVo = new NaverMapLocalResearchResultVo();
	    NaverMapLocalResearchResultListVo naverMapLocalResearchResultListVo = new NaverMapLocalResearchResultListVo();
	    List<NaverMapLocalResearchResultListVo> naverMapLocalResearchResultList = new ArrayList<NaverMapLocalResearchResultListVo>();
	    
	    for(NaverMapAddressResearchResultListVo naverMapAddressResearchResultListVo : naverMapAddressResearchResultVo.getAddresses()) {
	    	naverMapLocalResearchResultListVo.setTitle(naverMapAddressResearchResultListVo.getRoadAddress());
	    	naverMapLocalResearchResultListVo.setRoadAddress(naverMapAddressResearchResultListVo.getJibunAddress());
	    	naverMapLocalResearchResultListVo.setMapx(naverMapAddressResearchResultListVo.getX());
	    	naverMapLocalResearchResultListVo.setMapy(naverMapAddressResearchResultListVo.getY());
	    	naverMapLocalResearchResultListVo.setReturnType("address");
	    	
	    	naverMapLocalResearchResultList.add(naverMapLocalResearchResultListVo);
	    }
	    
	    naverMapLocalResearchResultVo.setItems(naverMapLocalResearchResultList);
	    
		try {
			resultJsonData = mapper.writeValueAsString(naverMapLocalResearchResultVo);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvMapDataResearchServiceImpl] [getAddressListByNaverMapDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
			
			return resultJsonData;
	}
	
	@Override
	public String getCctvListByCoordinateLocalMapDataJson(CctvCoordinateMapListResearchVo cctvMapListResearchVo) {
		
		logger.info("[CctvMapDataResearchServiceImpl] [getCctvListByLocalMapDataJson] Start ~!!!! ");
		
		logger.info("[CctvMapDataResearchServiceImpl] [getCctvListByLocalMapDataJson] [cctvMapListResearchVo] : {} ", cctvMapListResearchVo);
		
		String resultJsonData = "";
		ObjectMapper mapper = new ObjectMapper();
		List<CctvInfoDataDto> cctvInfoDataDto = new ArrayList<CctvInfoDataDto>();
		
		cctvInfoDataDto = cctvInfoDataDao.selectCctvCoordinateMapInfoList(cctvMapListResearchVo);
		
		try {
			resultJsonData = mapper.writeValueAsString(cctvInfoDataDto);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvMapDataResearchServiceImpl] [getAddressListByNaverMapDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return resultJsonData;
	}
	
}
