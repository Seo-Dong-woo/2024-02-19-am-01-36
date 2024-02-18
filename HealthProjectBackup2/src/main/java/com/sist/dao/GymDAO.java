package com.sist.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;

@Repository
public class GymDAO {
	@Autowired
	private GymMapper mapper;
	
	public List<GymVO> gymFindData(Map map)
	{
		return mapper.gymFindData(map);
	}
	
	public int gymFindCount(Map map)
	{
		return mapper.gymFindCount(map);
	}
	
	public GymVO gymDetailData(int no)
	{
		return mapper.gymDetailData(no);
	}
	
	public List<GymVO> gymListData(Map map)
	{
		return mapper.gymListData(map);
	}
	
	public int gymListCount()
	{
		return mapper.gymListCount();
	}
	
	public GymVO gymListDetailData(int no)
	{
		mapper.gymHitIncrement(no);
		return mapper.gymDetailData(no);
	}
	
	public List<GymVO> gymTop7()
	{
		return mapper.gymTop7();
	}
	
	public List<GymVO> gymHome12()
	{
		return mapper.gymHome12();
	}
}
