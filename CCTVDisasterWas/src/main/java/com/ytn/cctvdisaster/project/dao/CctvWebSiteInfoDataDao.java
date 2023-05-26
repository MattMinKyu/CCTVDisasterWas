package com.ytn.cctvdisaster.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ytn.cctvdisaster.project.dto.CctvWebSiteInfoDataDto;


/**
 * CctvWebSiteInfoData DAO
 * 
 * @author mattmk
 */

@Mapper
public interface CctvWebSiteInfoDataDao {
	
	/**
	 * selectCctvCategoryDepth1List
	 * 
	 * @author mattmk
	 * @param 
	 * @return List<CctvWebSiteInfoDataDto>
	 */
	List<CctvWebSiteInfoDataDto> selectCctvWebSiteDataList();
	
}
