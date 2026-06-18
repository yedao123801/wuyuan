package com.office.dao;
import com.office.model.Bgqy;
import com.office.model.BgqyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface BgqyMapper {
	long countByExample(BgqyExample example);
	int deleteByExample(BgqyExample example);
	int deleteByPrimaryKey(Long id);
	int insert(Bgqy record);
	int insertSelective(Bgqy record);
	List<Bgqy> selectByExample(BgqyExample example);
	Bgqy selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") Bgqy record, @Param("example") BgqyExample example);
	int updateByExample(@Param("record") Bgqy record, @Param("example") BgqyExample example);
	int updateByPrimaryKeySelective(Bgqy record);
	int updateByPrimaryKey(Bgqy record);
}
