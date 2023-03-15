package com.ytn.cctvdisaster.project.serviceImpl;

import java.net.URI;
import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.result.vo.NaverMapLocalResearchResultVo;
import com.ytn.cctvdisaster.project.service.CctvMapDataResearchService;
import com.ytn.cctvdisaster.project.vo.NaverMapLocalResearchVo;

@Service
public class CctvMapDataResearchServiceImpl implements CctvMapDataResearchService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvMapDataResearchServiceImpl.class);
	
	/*
	private CaptionsDataDao captionsDataDao;
	
	public CctvMapDataResearchServiceImpl(CaptionsDataDao captionsDataDao) {
		this.captionsDataDao = captionsDataDao;
	}
	*/
	
	@Override
	public String getLocalListByNaverMapDataJson(NaverMapLocalResearchVo naverMapLocalResearchVo) {
		
		String resultJsonData = "";
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		URI uri = UriComponentsBuilder
	                .fromUriString("https://openapi.naver.com")
	                .path("/v1/search/local.json")
	                .queryParam("query", naverMapLocalResearchVo.getQuery())
	                .queryParam("display", naverMapLocalResearchVo.getDisplay())
	                .queryParam("start", naverMapLocalResearchVo.getStart())
	                .queryParam("sort", naverMapLocalResearchVo.getSort())
	                // UTF-8로 인코딩
	                .encode(Charset.forName("UTF-8"))
	                .build()
	                .toUri();

	    // header 값 추가 get으로 요청하기 때문에 Void로 받는다.
	    RequestEntity<Void> req = RequestEntity
	            .get(uri)
	            .header("X-Naver-Client-Id","Uu42wShv9vuD7g4y1GIi")
	            .header("X-Naver-Client-Secret", "a7BmKltapZ")
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
	
	
	
}
