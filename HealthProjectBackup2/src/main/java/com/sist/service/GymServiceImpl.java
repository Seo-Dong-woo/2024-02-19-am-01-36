package com.sist.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.vo.*;
import com.sist.dao.*;

@Service
public class GymServiceImpl implements GymService {
	@Autowired
	private GymDAO gDao;
	
	@Autowired
	private NoticeDAO nDao;

	@Override
	public List<GymVO> gymFindData(Map map) {
		// TODO Auto-generated method stub
		return gDao.gymFindData(map);
	}

	@Override
	public int gymFindCount(Map map) {
		// TODO Auto-generated method stub
		return gDao.gymFindCount(map);
	}

	@Override
	public GymVO gymDetailData(int no) {
		// TODO Auto-generated method stub
		return gDao.gymDetailData(no);
	}

	@Override
	public List<GymVO> gymListData(Map map) {
		// TODO Auto-generated method stub
		return gDao.gymListData(map);
	}

	@Override
	public int gymListCount() {
		// TODO Auto-generated method stub
		return gDao.gymListCount();
	}

	@Override
	public GymVO gymListDetailData(int no) {
		// TODO Auto-generated method stub
		return gDao.gymListDetailData(no);
	}

	@Override
	public List<NoticeVO> noticeTop7() {
		// TODO Auto-generated method stub
		return nDao.noticeTop7();
	}

	@Override
	public List<GymVO> gymTop7() {
		// TODO Auto-generated method stub
		return gDao.gymTop7();
	}

	@Override
	public List<GymVO> gymHome12() {
		// TODO Auto-generated method stub
		return gDao.gymHome12();
	}
}
