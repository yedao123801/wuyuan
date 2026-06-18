package com.office.dao;
import com.office.model.Zsk;
import com.office.model.ZskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface ZskMapper {
	long countByExample(ZskExample example);
	int deleteByExample(ZskExample example);
	int deleteByPrimaryKey(Long id);
	int insert(Zsk record);
	int insertSelective(Zsk record);
	List<Zsk> selectByExample(ZskExample example);
	Zsk selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") Zsk record, @Param("example") ZskExample example);
	int updateByExample(@Param("record") Zsk record, @Param("example") ZskExample example);
	int updateByPrimaryKeySelective(Zsk record);
	int updateByPrimaryKey(Zsk record);
}
