package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class GymReplyServiceImpl implements GymReplyService {
	@Autowired
	private GymReplyDAO grDao;

	@Override
	public List<GymReplyVO> gymReplyListData(int gno) {
		// TODO Auto-generated method stub
		return grDao.gymReplyListData(gno);
	}

	@Override
	public void gymReplyInsert(GymReplyVO vo) {
		// TODO Auto-generated method stub
		grDao.gymReplyInsert(vo);
	}

	@Override
	public void gymReplyUpdate(GymReplyVO vo) {
		// TODO Auto-generated method stub
		grDao.gymReplyUpdate(vo);
	}

	@Override
	public void gymReplyDelete(int no) {
		// TODO Auto-generated method stub
		grDao.gymReplyDelete(no);
	}
}
