package com.ytn.cctvdisaster.project.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.ytn.cctvdisaster.project.dto.CctvBroadCastLockDto;
import com.ytn.cctvdisaster.project.vo.CctvBroadCastDataVo;


/**
 * CctvBroadCastInfoData DAO
 * 
 * @author mattmk
 */

@Mapper
public interface CctvBroadCastInfoDataDao {
	
	/**
	 * selectCctvCategorySearchList
	 * 
	 * @author mattmk
	 * @param String cctvSearchKeyword
	 * @return List<CctvInfoDataDto>
	 */
	//List<CctvInfoDataDto> selectCctvCategorySearchList(@RequestParam("cctvSearchKeyword")String cctvSearchKeyword);
	
	/**
	 * selectCctvBroadCastLockInfoData
	 * 
	 * @author mattmk
	 * @param CctvBroadCastDataVo cctvBroadCastDataVo
	 * @return int
	 */
	int selectCctvLockYnCount(@RequestParam("cctvBroadCastDataVo")CctvBroadCastDataVo cctvBroadCastDataVo);
	
	/**
	 * selectCctvBroadCastLockInfoData
	 * 
	 * @author mattmk
	 * @param 
	 * @return CctvBroadCastLockDto
	 */
	CctvBroadCastLockDto selectCctvBroadCastLockInfoData();
	
	
	/**
	 * modifyCctvBroadCastLockData
	 * 
	 * @author mattmk
	 * @param CctvBroadCastDataVo cctvBroadCastDataVo
	 * @return int
	 */
	int modifyCctvBroadCastLockData(@RequestParam("cctvBroadCastDataVo")CctvBroadCastDataVo cctvBroadCastDataVo);
	
	/**
	 * selectCctvBroadCastRabbitMqSendData
	 * 
	 * @author mattmk
	 * @param CctvBroadCastDataVo cctvBroadCastDataVo
	 * @return CctvBroadCastLockDto
	 */
	CctvBroadCastLockDto selectCctvBroadCastRabbitMqSendData(@RequestParam("cctvBroadCastDataVo")CctvBroadCastDataVo cctvBroadCastDataVo);
}
