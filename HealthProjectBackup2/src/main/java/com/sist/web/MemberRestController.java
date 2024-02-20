package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sist.vo.*;
import com.sist.service.*;

@RestController
public class MemberRestController {
	@Autowired 
	private MemberService mService;
	
	@GetMapping(value = "member/idcheck_vue.do",produces = "text/plain;charset=UTF-8")
	public String member_idcheck(String userId)
	{
		int count=mService.memberIdCount(userId);  //아이디가 잇는지없는지
		return String.valueOf(count); //문자열로줘야되서
	}
	
	@GetMapping(value = "member/login_ok_vue.do",produces = "text/plain;charset=UTF-8")
	public String member_login_ok(String userId,String userPwd,boolean ck,
			HttpSession session,HttpServletResponse response)
	{
		MemberVO vo=mService.memberLogin(userId, userPwd);
		if(vo.getMsg().equals("OK"))
		{
			session.setAttribute("userId", vo.getUserId());
			session.setAttribute("enabled", vo.getEnabled());
			session.setAttribute("authority", vo.getAuthority());
			session.setAttribute("userName", vo.getUserName());
			if(ck==true)
			{
				Cookie cookie=new Cookie("userId", vo.getUserId());
				cookie.setPath("/");
				cookie.setMaxAge(60*60*24);
				response.addCookie(cookie);
			}
		}
		return vo.getMsg();
	}
}












