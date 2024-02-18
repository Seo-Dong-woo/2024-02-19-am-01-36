package com.sist.mapper;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sist.vo.FreeBoardVO;
public interface FreeBoardMapper {
   // 목록 출력
   @Select("SELECT no,subject,name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday,hit,num "
		  +"FROM (SELECT no,subject,name,regdate,hit,rownum as num "
		  +"FROM (SELECT /*+ INDEX_DESC(projectFreeBoard pfb_no_pk)*/no,subject,name,regdate,hit "
		  +"FROM projectFreeBoard)) "
		  +"WHERE num BETWEEN #{start} AND #{end}")
   public List<FreeBoardVO> freeboardListData(@Param("start") int start,
		                                      @Param("end") int end);
   // 총페이지 
   @Select("SELECT CEIL(COUNT(*)/10.0) FROM projectFreeBoard")
   public int freeboardTotalPage();
   // 추가 
   @Insert("INSERT INTO projectFreeBoard(no,name,subject,content,pwd) "
		  +"VALUES(pfb_no_seq.nextval,#{name},#{subject},#{content},#{pwd})")
   public void freeboardInsert(FreeBoardVO vo);
   
   // 상세보기
   @Update("UPDATE projectFreeBoard SET "
		  +"hit=hit+1 "
		  +"WHERE no=#{no}")
   public void hitIncrement(int no);
   
   @Select("SELECT no,name,subject,content,hit,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
		  +"FROM projectFreeBoard "
		  +"WHERE no=#{no}")
   public FreeBoardVO freeboardDetailData(int no);
   // 수정 
   @Update("UPDATE projectFreeBoard SET "
		  +"name=#{name},subject=#{subject},content=#{content} "
		  +"WHERE no=#{no}")
   public void freeboardUpdate(FreeBoardVO vo);
   // 삭제 
   @Select("SELECT pwd FROM projectFreeBoard "
		  +"WHERE no=#{no}")
   public String freeboardGetPassword(int no);
   
   @Delete("DELETE FROM projectFreeBoard "
		  +"WHERE no=#{no}")
   public void freeboardDelete(int no);
}