package com.sist.service;

import java.util.*;
import com.sist.vo.*;
public interface FreeBoardService {
	public List<FreeBoardVO> freeboardListData(int start,int end);
	public int freeboardTotalPage();
	public void freeboardInsert(FreeBoardVO vo);
	public FreeBoardVO freeboardDetailData(int no);
	public String freeboardDelete(int no,String pwd);
	public FreeBoardVO freeboardUpdateData(int no);
	public String freeboardUpdate(FreeBoardVO vo);
}
