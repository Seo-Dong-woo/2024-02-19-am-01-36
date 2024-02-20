package com.sist.mapper;
/*
 *  USERID                                    NOT NULL VARCHAR2(20)
 USERNAME                                  NOT NULL VARCHAR2(51)
 USERPWD                                   NOT NULL VARCHAR2(300)
 ENABLED                                            NUMBER(1)
 SEX                                                VARCHAR2(6)
 BIRTHDAY                                  NOT NULL VARCHAR2(20)
 EMAIL                                              VARCHAR2(100)
 POST                                      NOT NULL VARCHAR2(10)
 ADDR1                                     NOT NULL VARCHAR2(500)
 ADDR2                                              VARCHAR2(500)
 PHONE                                              VARCHAR2(20)
 CONTENT                                            CLOB
 REGDATE                                            DATE
 MODIFYDATE                                         DATE
 LASTLOGIN                                          DATE
 * 
 */

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.MemberVO;

public interface MemberMapper {
	//1.회원가입
	@Select("SELECT COUNT(*) FROM hhfinalMember "
			+"WHERE userid=#{id}")
	public int memberIdCount(String userid);
	// => ID중복체크
	@Insert("INSERT INTO hhfinalMember(userid,username,userpwd,sex,birthday,email,post,"
			+"addr1,addr2,phone,content) VALUES(#{userId},#{userName},"
			+"#{userPwd},#{sex},#{birthday},#{email},#{post},"
			+"#{addr1},#{addr2},#{phone},#{content})")
	public void memberInsert(MemberVO vo);
	
	@Insert("INSERT INTO hhfinalAuthority VALUES(#{userId},'ROLE_USER')")
	public void memberauthorityInsert(String userId);
	//2. 로그인
	//1=>ID존재여부 확인
	// memberIdCount() 재사용
	//2=> 비밀번호 검색
	@Select("SELECT hfm.userId,userName,userPwd,enabled,authority "
	     +"FROM hhfinalMember hfm,hhfinalAuthority ha "
	     +"WHERE hfm.userId=ha.userId "
	     +"AND hfm.userId=#{userId}")
	public MemberVO memberLogin(String userId);
	
}















