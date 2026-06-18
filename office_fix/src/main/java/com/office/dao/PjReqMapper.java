package com.office.dao;
import com.office.model.PjReq;
import com.office.model.PjReqExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface PjReqMapper {
	long countByExample(PjReqExample example);
	int deleteByExample(PjReqExample example);
	int deleteByPrimaryKey(Long id);
	int insert(PjReq record);
	int insertSelective(PjReq record);
	List<PjReq> selectByExample(PjReqExample example);
	PjReq selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") PjReq record, @Param("example") PjReqExample example);
	int updateByExample(@Param("record") PjReq record, @Param("example") PjReqExample example);
	int updateByPrimaryKeySelective(PjReq record);
	int updateByPrimaryKey(PjReq record);
}
