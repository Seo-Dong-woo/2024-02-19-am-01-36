package com.sist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import com.sist.dao.*;
import com.sist.vo.*;
@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	@Autowired
    private FreeBoardDAO fDao;
	
	@Override
	public List<FreeBoardVO> freeboardListData(int start, int end) {
		// TODO Auto-generated method stub
		return fDao.freeboardListData(start, end);
	}

	@Override
	public int freeboardTotalPage() {
		// TODO Auto-generated method stub
		return fDao.freeboardTotalPage();
	}

	@Override
	public void freeboardInsert(FreeBoardVO vo) {
		// TODO Auto-generated method stub
		fDao.freeboardInsert(vo);
	}

	@Override
	public FreeBoardVO freeboardDetailData(int no) {
		// TODO Auto-generated method stub
		return fDao.freeboardDetailData(no);
	}

	@Override
	public String freeboardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		return fDao.freeboardDelete(no, pwd);
	}

	@Override
	public FreeBoardVO freeboardUpdateData(int no) {
		// TODO Auto-generated method stub
		return fDao.freeboardUpdateData(no);
	}

	@Override
	public String freeboardUpdate(FreeBoardVO vo) {
		// TODO Auto-generated method stub
		return fDao.freeboardUpdate(vo);
	}
  
}