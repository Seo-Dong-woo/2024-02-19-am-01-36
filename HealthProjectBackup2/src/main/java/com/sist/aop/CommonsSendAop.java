package com.sist.aop;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.manager.NewsManager;
import com.sist.service.*;
import com.sist.vo.*;

@Aspect
@Component
public class CommonsSendAop {
	@Autowired
	private GymService service;
	
	@Autowired
	private NewsManager mgr;
	
	@After("execution(* com.sist.web.*Controller.*(..))")
	public void footerSend()
	{
		List<GymVO> gList=service.gymTop7();
		List<NoticeVO> nList=service.noticeTop7();
		List<NewsVO> newList=mgr.newsFind("건강");
		/*
		 *   @Controller, @RestController를 제외한 모든 클래승서 request가 필요한 경우에 사용
		 *   ============================
		 *       | request를 요청하면 => DispatcherServlet에 의해 매개변수로 주입
		 */
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		
		request.setAttribute("nList", nList);
		request.setAttribute("gList", gList);
		request.setAttribute("newList", newList);
	}
}