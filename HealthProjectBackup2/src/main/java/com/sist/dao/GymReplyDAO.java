package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class GymReplyDAO {
	@Autowired
	private GymReplyMapper mapper;

	public List<GymReplyVO> gymReplyListData(int gno)
	{
		return mapper.gymReplyListData(gno);
	}

	public void gymReplyInsert(GymReplyVO vo)
	{
		mapper.gymReplyInsert(vo);
	}

	public void gymReplyUpdate(GymReplyVO vo)
	{
		mapper.gymReplyUpdate(vo);
	}

	public void gymReplyDelete(int no)
	{
		mapper.gymReplyDelete(no);
	}
}
