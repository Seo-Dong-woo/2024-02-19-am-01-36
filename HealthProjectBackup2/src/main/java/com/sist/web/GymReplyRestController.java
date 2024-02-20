package com.sist.web;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;

@RestController
public class GymReplyRestController {
	@Autowired
	private GymReplyService grService;
	
	// insert, update, delete
	public String commonsReplyData(int gno) throws Exception
	{
		ObjectMapper mapper=new ObjectMapper();
		List<GymReplyVO> list=grService.gymReplyListData(gno);
		String json=mapper.writeValueAsString(list);
		return json;
	}
		
	@PostMapping(value = "gym/reply_insert_vue.do", produces = "text/plain; charset=UTF-8")
	public String reply_insert(GymReplyVO vo, HttpSession session) throws Exception
	{
		String userId=(String)session.getAttribute("userId");
		String userName=(String)session.getAttribute("userName");
		vo.setUserId(userId);
		vo.setUserName(userName);
		// rno, msg
		grService.gymReplyInsert(vo);
			
		return commonsReplyData(vo.getGno());
	}
		
	// 수정
	@PostMapping(value = "gym/reply_update_vue.do", produces = "text/plain; charset=UTF-8")
	public String reply_update(GymReplyVO vo) throws Exception
	{
		grService.gymReplyUpdate(vo);
		return commonsReplyData(vo.getGno());
	}
		
	// 삭제
	@GetMapping(value = "gym/reply_delete_vue.do", produces = "text/plain; charset=UTF-8")
	public String reply_delete(int no, int gno) throws Exception
	{
		grService.gymReplyDelete(no);
		return commonsReplyData(gno);
	}
}
