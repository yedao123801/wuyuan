package com.office.dao;
import com.office.model.BgService;
import com.office.model.BgServiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface BgServiceMapper {
	long countByExample(BgServiceExample example);
	int deleteByExample(BgServiceExample example);
	int deleteByPrimaryKey(Long id);
	int insert(BgService record);
	int insertSelective(BgService record);
	List<BgService> selectByExample(BgServiceExample example);
	BgService selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") BgService record, @Param("example") BgServiceExample example);
	int updateByExample(@Param("record") BgService record, @Param("example") BgServiceExample example);
	int updateByPrimaryKeySelective(BgService record);
	int updateByPrimaryKey(BgService record);
}
