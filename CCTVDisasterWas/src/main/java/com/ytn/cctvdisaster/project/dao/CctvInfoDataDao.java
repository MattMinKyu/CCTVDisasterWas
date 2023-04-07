package com.ytn.cctvdisaster.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.ytn.cctvdisaster.project.dto.CctvInfoDataDto;
import com.ytn.cctvdisaster.project.vo.CctvCoordinateMapListResearchVo;


/**
 * CctvInfoData DAO
 * 
 * @author mattmk
 */

@Mapper
public interface CctvInfoDataDao {
	
	/**
	 * selectCctvCoordinateMapInfoList
	 * 
	 * @author mattmk
	 * @param CctvCoordinateMapListResearchVo cctvMapListResearchVo
	 * @return List<CctvInfoDataDto>
	 */
	List<CctvInfoDataDto> selectCctvCoordinateMapInfoList(@RequestParam("cctvMapListResearchVo")CctvCoordinateMapListResearchVo cctvMapListResearchVo);
	
	
	/**
	 * selectCctvCoordinateMapInfoList
	 * 
	 * @author mattmk
	 * @param Map<String, Object> cctvIdList
	 * @return List<CctvInfoDataDto>
	 */
	List<CctvInfoDataDto> selectCctvIdInfoList(@RequestParam("cctvIdList")Map<String, Object> cctvIdList);
}
