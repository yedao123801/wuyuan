package com.office.dao;
import com.office.model.FixGc;
import com.office.model.FixGcExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface FixGcMapper {
	long countByExample(FixGcExample example);
	int deleteByExample(FixGcExample example);
	int deleteByPrimaryKey(Long id);
	int insert(FixGc record);
	int insertSelective(FixGc record);
	List<FixGc> selectByExample(FixGcExample example);
	FixGc selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") FixGc record, @Param("example") FixGcExample example);
	int updateByExample(@Param("record") FixGc record, @Param("example") FixGcExample example);
	int updateByPrimaryKeySelective(FixGc record);
	int updateByPrimaryKey(FixGc record);
}
