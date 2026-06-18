package com.office.dao;
import com.office.model.EvalLog;
import com.office.model.EvalLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface EvalLogMapper {
	long countByExample(EvalLogExample example);
	int deleteByExample(EvalLogExample example);
	int deleteByPrimaryKey(Long id);
	int insert(EvalLog record);
	int insertSelective(EvalLog record);
	List<EvalLog> selectByExample(EvalLogExample example);
	EvalLog selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") EvalLog record, @Param("example") EvalLogExample example);
	int updateByExample(@Param("record") EvalLog record, @Param("example") EvalLogExample example);
	int updateByPrimaryKeySelective(EvalLog record);
	int updateByPrimaryKey(EvalLog record);
}
