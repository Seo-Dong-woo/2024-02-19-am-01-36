package com.sist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class MemberDAO {
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private MemberMapper mapper;
	
	public int memberIdCount(String userid)
	{
		return mapper.memberIdCount(userid);
	}
	
	public void memberInsert(MemberVO vo)
	{
		mapper.memberInsert(vo);
	}
	public void memberauthorityInsert(String userId)
	{
		mapper.memberauthorityInsert(userId);
	}
	public MemberVO memberLogin(String userId,String userPwd)
	{
		MemberVO dbVO=new MemberVO();
		int count=mapper.memberIdCount(userId);
		if(count==0)
		{
			dbVO.setMsg("NOID");
		}
		else
		{
			dbVO=mapper.memberLogin(userId);
			if(encoder.matches(userPwd,	dbVO.getUserPwd()))//park1234
			{
			    dbVO.setMsg("OK");	
			}
			else
			{
				dbVO.setMsg("NOPWD");
			}
		}
		return dbVO;
	}
}
