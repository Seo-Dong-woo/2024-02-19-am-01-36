package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.*;

public interface GymReplyMapper {
	// 목록
	@Select("SELECT no, gno, userId, userName, msg, TO_CHAR(regdate, 'YYYY-MM-DD HH24:MI:SS') as dbday "
			+ "FROM projectGymReply "
			+ "WHERE gno=#{gno} "
			+ "ORDER BY no DESC")
	public List<GymReplyVO> gymReplyListData(int gno);
		
	// 추가
	@Insert("INSERT INTO projectGymReply(no, gno, userId, userName, msg) "
			+ "VALUES(pgr_no_seq.nextval, #{gno}, #{userId}, #{userName}, #{msg})")
	public void gymReplyInsert(GymReplyVO vo);
		
	// 수정
	@Update("UPDATE projectGymReply SET "
			+ "msg=#{msg} "
			+ "WHERE no=#{no}")
	public void gymReplyUpdate(GymReplyVO vo);
		
	// 삭제
	@Delete("DELETE FROM projectGymReply "
			+ "WHERE no=#{no}")
	public void gymReplyDelete(int no);
}
