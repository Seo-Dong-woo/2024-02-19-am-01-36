package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface GymMapper {
	@Select("SELECT no, poster, title, num "
			+ "FROM (SELECT no, poster, title, rownum as num "
			+ "FROM (SELECT no, poster, title "
			+ "FROM gym "
			+ "WHERE address LIKE '%'||#{address}||'%' "
			+ "ORDER BY no ASC)) "
			+ "WHERE num  BETWEEN #{start} AND #{end}")
	public List<GymVO> gymFindData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM gym "
			+"WHERE address LIKE '%'||#{address}||'%'")
	public int gymFindCount(Map map);
	
	@Select("SELECT no, title, poster, address, time, phone "
			+ "FROM gym "
			+ "WHERE no=#{no}")
	public GymVO gymDetailData(int no);
	
	@Update("UPDATE gym SET "
			+ "hit=hit+1 "
			+ "WHERE no=#{no}")
    public void gymHitIncrement(int no);
	
	@Select("SELECT no, poster, title, num "
			+"FROM (SELECT no, poster, title, rownum as num "
			+"FROM (SELECT no, poster, title "
			+"FROM gym "
			+"ORDER BY no ASC)) "
			+"WHERE num BETWEEN #{start} AND #{end}")
	public List<GymVO> gymListData(Map map);
	
	@Select("SELECT CEIL(COUNT(*)/20.0) FROM gym")
	public int gymListCount();
	
	@Select("SELECT no, title , rownum "
			+"FROM (SELECT no, title "
			+"FROM gym ORDER BY hit DESC) "
			+"WHERE rownum<=7")
	public List<GymVO> gymTop7();
	  
	@Select("SELECT no, title, poster, rownum "
			+ "FROM (SELECT no, title, poster "
			+ "FROM gym ORDER BY hit DESC) "
			+ "WHERE rownum<=12")
	public List<GymVO> gymHome12();
}
