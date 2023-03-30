package com.ytn.cctvdisaster.project.service;

import java.util.List;

import com.ytn.cctvdisaster.project.result.vo.KtictCctvStatusResultVo;

/**
 * CctvKtIctVideoInfoDataService Interface
 * 
 * @author mattmk
 *
 */
public interface CctvKtIctVideoInfoDataService {
	
	/**
	 * getVideoStatusInfoListByKtIctDataJson.
	 * 
	 * @author mattmk
	 * @param String cctvIdListStr
	 * @return List<KtictCctvStatusResultVo>
	 */
	public List<KtictCctvStatusResultVo> getCctvStatusInfoListByKtIctDataJson(String cctvIdListStr);
	
	/**
	 * getCctvStreamingDateByKtIctJson.
	 * 
	 * @author mattmk
	 * @param String cctvIdParam
	 * @return String
	 */
	public String getCctvStreamingDateByKtIctJson(String cctvIdParam);
	
	/**
	 * getCctvStreamingAndJpgDataListByKtIctDataJson.
	 * 
	 * @author mattmk
	 * @param String cctvIdParam
	 * @return String
	 */
	public String getCctvThumbnailMakeUrlByKtIct(String testParam);
}
