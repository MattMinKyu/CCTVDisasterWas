package com.ytn.cctvdisaster.project.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.ytn.cctvdisaster.project.dto.PlistDtlDto;
import com.ytn.cctvdisaster.project.dto.PlistDtlJoinCctvInfoDto;
import com.ytn.cctvdisaster.project.dto.PlistMstDto;
import com.ytn.cctvdisaster.project.vo.CctvPlayListDataVo;


/**
 * CctvPlayListInfoData DAO
 * 
 * @author mattmk
 */

@Mapper
public interface CctvPlayListInfoDataDao {
	
	/**
	 * selectCctvPlayListInfoList
	 * 
	 * @author mattmk
	 * @param String likeSearchKeyword
	 * @return List<PlistMstDto>
	 */
	List<PlistMstDto> selectCctvPlayListInfoList(@RequestParam("likeSearchKeyword")String likeSearchKeyword);
	
	/**
	 * selectCctvPlayListInfoCount
	 * 
	 * @author mattmk
	 * @param String plistId
	 * @return String
	 */
	int selectCctvPlayListInfoCount(@RequestParam("plistId")String plistId);
	
	/**
	 * insertCctvPlayListInfo
	 * 
	 * @author mattmk
	 * @param CctvPlayListDataVo cctvPlayListDataVo
	 * @return int
	 */
	int insertCctvPlayListInfo(@RequestParam("cctvPlayListDataVo")CctvPlayListDataVo cctvPlayListDataVo);
	
	/**
	 * updateCctvPlayListInfo
	 * 
	 * @author mattmk
	 * @param CctvPlayListDataVo cctvPlayListDataVo
	 * @return int
	 */
	int updateCctvPlayListInfo(@RequestParam("cctvPlayListDataVo")CctvPlayListDataVo cctvPlayListDataVo);
	
	/**
	 * deleteCctvPlayListInfo
	 * 
	 * @author mattmk
	 * @param String plistId
	 * @return int
	 */
	int deleteCctvPlayListInfo(@RequestParam("plistId")String plistId);
	
	/**
	 * deleteCctvPlayListDetailInfo
	 * 
	 * @author mattmk
	 * @param String plistId
	 * @return int
	 */
	int deleteCctvPlayListDetailInfo(@RequestParam("plistId")String plistId);
	
	/**
	 * selectCctvPlayListInfoList
	 * 
	 * @author mattmk
	 * @param String plistId
	 * @return List<PlistDtlJoinCctvInfoDto>
	 */
	List<PlistDtlJoinCctvInfoDto> selectCctvPlayListDetailInfoList(@RequestParam("plistId")String plistId);
	
	/**
	 * selectCctvPlayListDetailInfoCount
	 * 
	 * @author mattmk
	 * @param String plistId
	 * @return int
	 */
	int selectCctvPlayListDetailInfoCount(@RequestParam("plistId")String plistId);
	
	/**
	 * insertCctvPlayListDetailInfo
	 * 
	 * @author mattmk
	 * @param PlistDtlDto plistDtlDto
	 * @return int
	 */
	int insertCctvPlayListDetailInfo(@RequestParam("plistDtlDto")PlistDtlDto plistDtlDto);
}
