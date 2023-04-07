package com.ytn.cctvdisaster.project.serviceImpl;

import java.net.URI;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ytn.cctvdisaster.project.result.vo.KtictCctvStatusResultVo;
import com.ytn.cctvdisaster.project.service.CctvKtIctVideoInfoDataService;

@Service
public class CctvKtIctVideoInfoDataServiceImpl implements CctvKtIctVideoInfoDataService{
	
	private static final Logger logger = LoggerFactory.getLogger(CctvKtIctVideoInfoDataServiceImpl.class);
	
	
	@Value("${ktict.api.coname}")
    private String ktictCoName;
	
	@Value("${ktict.api.svcname}")
    private String ktictSvcName;
	
	@Value("${ktict.api.accesscode}")
    private String ktictAccesscode;
	
	@Value("${ktict.api.authkey}")
    private String ktictAuthkey;
	
	@Value("${ktict.api.status.uri}")
    private String ktictStatusApiUri;
	
	@Value("${ktict.api.status.path}")
    private String ktictStatusApiPath;
	
	@Value("${ktict.api.videoinfo.uri}")
    private String ktictVideoInfoApiUri;
	
	@Value("${ktict.api.videoinfo.path}")
    private String ktictVideoInfoApiPath;
	
	/*
	public CctvKtIctVideoInfoDataServiceImpl(FFmpegUtil fFmpegUtil) {
		this.fFmpegUtil = fFmpegUtil;
	}
	*/
	
	@SuppressWarnings("deprecation")
	@Override
	public List<KtictCctvStatusResultVo> getCctvStatusInfoListByKtIctDataJson(String cctvIdListStr) {
		List<KtictCctvStatusResultVo> returnObjectList = null;
		
		RestTemplate restTemplate = new RestTemplate();
		ObjectMapper mapper = new ObjectMapper();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date nowDate = new Date();
		String nowDateStr = simpleDateFormat.format(nowDate);
		
		String authParam = ktictCoName + "," + ktictSvcName + "," + ktictAccesscode + ","+nowDateStr;
		
		logger.info("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStatusInfoListByKtIctDataJson] [response] [authParam] ===> {}", authParam);
		
		byte[] keyData = ktictAuthkey.getBytes();
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		logger.info("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStatusInfoListByKtIctDataJson] [secureKey] ===> {}", secureKey);
		
		String authorizationKey = "";
		
		try {
			Cipher c = Cipher.getInstance("AES/ECB/PKCS5Padding");
			logger.info("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStatusInfoListByKtIctDataJson] [c] ===> {}", c);
			
			c.init(Cipher.ENCRYPT_MODE, secureKey);
			byte[] encrypted;
			
			encrypted = c.doFinal(authParam.getBytes("UTF-8"));
			logger.info("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStatusInfoListByKtIctDataJson] [encrypted] ===> {}", encrypted);
			
			authorizationKey = new String(Base64.encodeBase64(encrypted), "UTF-8");
			
			logger.info("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStatusInfoListByKtIctDataJson] [authorizationKey] ===> {}", authorizationKey);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStatusInfoListByKtIctDataJson] [Try Catch selectKeyMake] [Exception] ====> {}", e);
			
			return returnObjectList;
		}
					
		
		MultiValueMap<String, String> parameterBody = new LinkedMultiValueMap<>();
		parameterBody.add("data", cctvIdListStr);
		
		MultiValueMap<String, String> parametersQueryString = new LinkedMultiValueMap<>();
		parametersQueryString.add("param", "list");
		parametersQueryString.add("auth", URLEncoder.encode(authorizationKey));
		
		URI uri = UriComponentsBuilder
	            .fromUriString(ktictStatusApiUri)
	            .path(ktictStatusApiPath)
	            .queryParams(parametersQueryString)
	            .build(true)
	            .toUri();
		
		logger.info("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStatusInfoListByKtIctDataJson] [response] [uri] ===> {}", uri);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(parameterBody, headers);
		
		String resultStr;
		
		try {
			resultStr = restTemplate.postForObject(uri, request, String.class);
		}catch (Exception e) {
			e.printStackTrace();
			logger.error("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStatusInfoListByKtIctDataJson] [Try Catch ktictApi] [Exception] ====> {}", e);
			
			return returnObjectList;
		}
	    
		try {
			returnObjectList = Arrays.asList(mapper.readValue(resultStr, KtictCctvStatusResultVo[].class));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			logger.error("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStatusInfoListByKtIctDataJson] [Try Catch resultJsonData] [Exception] ====> {}", e);
		}
		
		return returnObjectList;
	}
	
	
	@Override
	public String getCctvStreamingDateByKtIctJson(String cctvIdParam) {
		String resultJsonData = "";
		RestTemplate restTemplate = new RestTemplate();
		
		// 스트리밍 정보 받아오기.
		URI uri = UriComponentsBuilder
	                .fromUriString(ktictVideoInfoApiUri)
	                .path(ktictVideoInfoApiPath)
	                .path("/"+cctvIdParam+"!hls")
	                
	                //.encode(Charset.forName("UTF-8"))
	                .build()
	                .toUri();
		
		logger.info("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStreamingDataListByKtIctDataJson] uri : {}", uri);
		
	    // header �� �߰� get���� ��û�ϱ� ������ Void�� �޴´�.
	    RequestEntity<Void> req = RequestEntity
	            .get(uri)
	            .build();
	    
	    try {
	    	ResponseEntity<String> response = restTemplate.exchange(req, String.class);
		    resultJsonData = response.getBody();
	    }catch (Exception e) {
	    	//e.printStackTrace();
			logger.error("[CctvKtIctVideoInfoDataServiceImpl] [getCctvStreamingDateByKtIctJson] [Try Catch ktict api error] [cctvIdParam] ====> {}", cctvIdParam);
		}
	    
		
		return resultJsonData;
	}
	
	@Override
	public String getCctvThumbnailMakeUrlByKtIct(String cctvId) {
		
		return ktictVideoInfoApiUri+ktictVideoInfoApiPath+"/"+cctvId+"!jpg";
	}
	
}
