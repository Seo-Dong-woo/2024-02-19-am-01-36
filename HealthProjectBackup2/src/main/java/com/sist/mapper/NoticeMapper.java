package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.vo.*;

public interface NoticeMapper {
	@Select("SELECT no, subject, TO_CHAR(regdate, 'YYYY-MM-DD') as dbday, rownum "
			+ "FROM (SELECt no, subject, regdate "
			+ "FROM projectNotice ORDER BY hit DESC")
	public List<NoticeVO> noticeTop7();
}
