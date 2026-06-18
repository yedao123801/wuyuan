package com.office.dao;
import com.office.model.BxLog;
import com.office.model.BxLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface BxLogMapper {
	long countByExample(BxLogExample example);
	int deleteByExample(BxLogExample example);
	int deleteByPrimaryKey(Long id);
	int insert(BxLog record);
	int insertSelective(BxLog record);
	List<BxLog> selectByExample(BxLogExample example);
	BxLog selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") BxLog record, @Param("example") BxLogExample example);
	int updateByExample(@Param("record") BxLog record, @Param("example") BxLogExample example);
	int updateByPrimaryKeySelective(BxLog record);
	int updateByPrimaryKey(BxLog record);
}
