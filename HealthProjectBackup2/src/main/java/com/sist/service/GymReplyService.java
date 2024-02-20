package com.sist.service;

import java.util.List;

import com.sist.vo.GymReplyVO;

public interface GymReplyService {
	public List<GymReplyVO> gymReplyListData(int gno);
	public void gymReplyInsert(GymReplyVO vo);
	public void gymReplyUpdate(GymReplyVO vo);
	public void gymReplyDelete(int no);

}
