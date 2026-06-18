package com.office.dao;
import com.office.model.Depart;
import com.office.model.DepartExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface DepartMapper {
	long countByExample(DepartExample example);
	int deleteByExample(DepartExample example);
	int deleteByPrimaryKey(Long id);
	int insert(Depart record);
	int insertSelective(Depart record);
	List<Depart> selectByExample(DepartExample example);
	Depart selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") Depart record, @Param("example") DepartExample example);
	int updateByExample(@Param("record") Depart record, @Param("example") DepartExample example);
	int updateByPrimaryKeySelective(Depart record);
	int updateByPrimaryKey(Depart record);
}
