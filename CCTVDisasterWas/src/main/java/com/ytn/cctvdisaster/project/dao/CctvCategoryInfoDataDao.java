package com.ytn.cctvdisaster.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.ytn.cctvdisaster.project.dto.CctvCategoryDepth1DataDto;
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
	 * @param 
	 * @return List<CctvCategoryDepth1DataDto>
	 */
	List<CctvCategoryDepth1DataDto> selectCctvCategoryDepth1List();
	
	/**
	 * selectCctvCategoryDepth2List
	 * 
	 * @author mattmk
	 * @param String srcId
	 * @return List<CctvInfoDataDto>
	 */
	List<CctvInfoDataDto> selectCctvCategoryDepth2List(@RequestParam("srcId")String srcId);
	
	/**
	 * selectCctvCategorySearchList
	 * 
	 * @author mattmk
	 * @param String cctvSearchKeyword
	 * @return List<CctvInfoDataDto>
	 */
	List<CctvInfoDataDto> selectCctvCategorySearchList(@RequestParam("cctvSearchKeyword")String cctvSearchKeyword);
}
