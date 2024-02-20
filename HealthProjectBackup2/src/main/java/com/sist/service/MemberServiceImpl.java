package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.MemberVO;
import java.util.*;
import com.sist.vo.*;
import com.sist.dao.*;
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberDAO mDao;
	
	@Override
	public int memberIdCount(String userid) {
		// TODO Auto-generated method stub
		return mDao.memberIdCount(userid); 
	}

	@Override
	public void memberInsert(MemberVO vo) {
		// TODO Auto-generated method stub
			mDao.memberInsert(vo);
	}

	@Override
	public void memberauthorityInsert(String userId) {
		// TODO Auto-generated method stub
			mDao.memberauthorityInsert(userId);
	}

	@Override
	public MemberVO memberLogin(String userId, String userPwd) {
		// TODO Auto-generated method stub
		return mDao.memberLogin(userId, userPwd);
	}
	
	
}
