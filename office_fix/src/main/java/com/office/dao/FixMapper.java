package com.office.dao;
import com.office.model.Fix;
import com.office.model.FixExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface FixMapper {
	long countByExample(FixExample example);
	int deleteByExample(FixExample example);
	int deleteByPrimaryKey(Long id);
	int insert(Fix record);
	int insertSelective(Fix record);
	List<Fix> selectByExample(FixExample example);
	Fix selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") Fix record, @Param("example") FixExample example);
	int updateByExample(@Param("record") Fix record, @Param("example") FixExample example);
	int updateByPrimaryKeySelective(Fix record);
	int updateByPrimaryKey(Fix record);
}
