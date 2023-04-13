package com.ytn.cctvdisaster.project.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.ytn.cctvdisaster.project.dto.CctvCategoryDepth1DataDto;
import com.ytn.cctvdisaster.project.dto.CctvCategoryDepth2DataDto;
import com.ytn.cctvdisaster.project.dto.CctvCategoryDepth3DataDto;
import com.ytn.cctvdisaster.project.dto.CctvInfoDataDto;


/**
 * CctvInfoData DAO
 * 
 * @author mattmk
 */

@Mapper
public interface CctvCategoryInfoDataDao {
	
	/**
	 * selectCctvCategoryDepth1List
	 * 
	 * @author mattmk
	 * @param String cctvSearchKeyword
	 * @return List<CctvCategoryDepth1DataDto>
	 */
	List<CctvCategoryDepth1DataDto> selectCctvCategoryDepth1List(@RequestParam("cctvSearchKeyword")String cctvSearchKeyword);
	
	/**
	 * selectCctvCategoryDepth2List
	 * 
	 * @author mattmk
	 * @param Map<String, Object> daoParams
	 * @return List<CctvCategoryDepth2DataDto>
	 */
	List<CctvCategoryDepth2DataDto> selectCctvCategoryDepth2List(Map<String, Object> daoParams);
	
	/**
	 * selectCctvCategoryDepth3List
	 * 
	 * @author mattmk
	 * @param Map<String, Object> daoParams
	 * @return List<CctvCategoryDepth3DataDto>
	 */
	List<CctvCategoryDepth3DataDto> selectCctvCategoryDepth3List(Map<String, Object> daoParams);
	
	/**
	 * selectCctvCategorySearchList
	 * 
	 * @author mattmk
	 * @param String cctvSearchKeyword
	 * @return List<CctvInfoDataDto>
	 */
	List<CctvInfoDataDto> selectCctvCategorySearchList(@RequestParam("cctvSearchKeyword")String cctvSearchKeyword);
}
