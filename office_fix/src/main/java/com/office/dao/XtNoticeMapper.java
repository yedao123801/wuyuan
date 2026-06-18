package com.office.dao;
import com.office.model.XtNotice;
import com.office.model.XtNoticeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
public interface XtNoticeMapper {
	long countByExample(XtNoticeExample example);
	int deleteByExample(XtNoticeExample example);
	int deleteByPrimaryKey(Long id);
	int insert(XtNotice record);
	int insertSelective(XtNotice record);
	List<XtNotice> selectByExample(XtNoticeExample example);
	XtNotice selectByPrimaryKey(Long id);
	int updateByExampleSelective(@Param("record") XtNotice record, @Param("example") XtNoticeExample example);
	int updateByExample(@Param("record") XtNotice record, @Param("example") XtNoticeExample example);
	int updateByPrimaryKeySelective(XtNotice record);
	int updateByPrimaryKey(XtNotice record);
}
