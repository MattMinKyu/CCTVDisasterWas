package com.ytn.cctvdisaster.project.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import com.ytn.cctvdisaster.project.vo.PageingVo;


/**
 * Captions DAO
 * 
 * @author mattmk
 */

@Mapper
public interface CaptionsDataDao {
	
	/**
	 * Captions List Cnt.
	 * 
	 * @author mattmk
	 * @param PageingVo pageingVo
	 * @return int
	 */
	int selectCaptionsInfoListCnt(@RequestParam("pageingVo")PageingVo pageingVo);
		
	
}
