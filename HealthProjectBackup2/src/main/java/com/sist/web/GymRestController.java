package com.sist.web;

import java.util.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
import com.sist.vo.*;

@RestController
@RequestMapping("gym/")
/*
 *    사용자(클라이언트 => 브라우저) => 요청 (URL) .do  
 *                                         ===== 현재 사이트에서 가능 
 *                                         
 *   서버 
 *   => 라이브러리 (기본 동작을 위한 틀이 만들어져 있기 때문에 => 형식을 맞게 세팅해야 된다)
 *   
 *   ==============================================================
 *    DispatcherServlet => 요청 받기 (request,response)
 *       web.xml에 셋팅을 한다 
 *       = WebApplicatinContext => 사용자가 등록한 클래스 관리 
 *         ===============> 관리가 필요한 모든 클래스를 xml에 설정해서 전송 
 *               <init-param>
 *                 <param-name>contextConfigLocation</param-name>
 *                 <param-value>/WEB-INF/config/application-*.xml</param-value>
 *               </init-param>
 *       = HandlerMapping : 요청시에 처리하는 Controller/RestController를 찾는 역할 
 *       = ViewResolver : JSP찾아서 request를 전송 
 *   ==============================================================
 *   Model : Controller / RestController 
 *           => HandlerMapping에서 해당 메소드를 찾을 수 있게 만든다 
 *                               =========
 *                               @GetMapping,@PostMapping,@RequestMapping 
 *           => 조립기 
 *              요청을 받아서 => 응답하기 
 *                       처리 => DB
 *   ==========================================
 *   Mapper : 테이블 1개를 다루는 경우 
 *   Service : 관련된 Mapper가 여러개 있는 경우
 *   DAO
 *   ========================================= DB연동 (MyBatis는 데이터베이스 연결) 
 *   View(JSP) : 화면 출력 
 *   ========================================= 요청 (form , a , axios , ajax)
 *                                                 ==========  =============
 *                                                  | 화면 변경   | 변경없이 데이터 읽기
 *                                                  
 *   list.do  ========> DispatcherServlet (XML,어노테이션)
 *                          ===> list.do처리 메소드를 찾아라 
 *                               HandlerMapping
 *                         ------------------------------------
 *                          ===> list.do에 대한 처리 ==> 개발자 
 *                               @GetMapping("list.do")
 *                          ===> JSP룰 응답값을 전송 / 화면 변경
 *                         ------------------------------------- Model 
 *                                         @Controller, @RestController 
 *                          ===> JSP를 찾아서 request를 전송 
 *                               =======================
 *                                 ViewResolver ==> 경로명 , 확장자 확인 => 등록 
 *       => 어노테이션 / XML => 스프링 동작을 위한 메뉴얼 제작 
 *       => Model / JSP
 *           |
 *         DAO/Service ==> MyBatis 
 *         
 *       Model 
 *       =====
 *         => RestController
 *             => 다른 언어와 연동 ==> JSON
 *             => 자바스크립트와 자바 연동 
 *                자바 => VO => {} (Object)
 *                자바 => List => [] (Array)
 *                => String => string => "" , ''
 *         => Controller : 화면 이동 (변경) 
 *             => forward => request를 전송할 경우 
 *                           ======== 스프링에서 제공하는 전송 객체 : Model
 *                           return "폴더명/jsp이름";
 *             => redirect => 재호출 => .do
 *                           request를 초기화 
 *                           return "redirect:...do";
 *                           _ok
 *      
 */
public class GymRestController {
	@Autowired
	private GymService service;
	
	@Autowired
	private GymReplyService gymReplyService;
	
	@GetMapping(value="find_vue.do",produces = "text/plain;charset=UTF-8")
	public String gym_find(int page, String fd) throws Exception
	{
		int rowSize=20;
		int start=(rowSize*page)-(rowSize-1);
		int end=(rowSize*page);
		Map map=new HashMap();
		map.put("start",start);
		map.put("end", end);
		map.put("address", fd);
		List<GymVO> list=service.gymFindData(map);
		//JSON변경
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value="page_vue.do",produces = "text/plain;charset=UTF-8")
	public String gym_page(int page, String fd) throws Exception
	{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		Map map=new HashMap();
		map.put("address", fd);
		int totalpage=service.gymFindCount(map);
		if(endPage>totalpage)
			endPage=totalpage;
		   
		map=new HashMap();
		map.put("curpage",page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		   
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		return json;
	}
	
	@GetMapping(value="detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String gym_detail(int no) throws Exception
	{
		GymVO vo=service.gymDetailData(no);//{} => []
		// JSON 만드는 라이브러리 => jackson
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value="gym_cookie_vue.do",produces = "text/plain;charset=UTF-8")
	public String gym_cookie(HttpServletRequest request) throws Exception
	{
		Cookie[] cookies=request.getCookies();
		List<GymVO> list=new ArrayList<GymVO>();
		int k=0;
		if(cookies!=null)
		{
			for(int i=cookies.length-1;i>=0;i--)
			{
			    if(k<9)
			    {
			    	// new Cookie("food_"+fno, String.valueOf(fno))
			    	//            =======getName() ======= getValue()
			    	if(cookies[i].getName().startsWith("gym_"))
			    	{
			    		String no=cookies[i].getValue();
			    		GymVO vo=service.gymDetailData(Integer.parseInt(no));
			    		list.add(vo);
			    	}
			    	k++;
			    }
			}
		}
		   
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value="gym_list_vue.do",produces = "text/plain;charset=UTF-8")
	public String gym_list(int page) throws Exception
	{
		Map map=new HashMap();
		map.put("start", (20*page)-19);
		map.put("end",20*page);
		   
		List<GymVO> list=service.gymListData(map);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json; // then(response=>{}) => response.data  
	}
	
	@GetMapping(value="gym_page_vue.do",produces = "text/plain;charset=UTF-8")
	public String gym_list_page(int page) throws Exception
	{
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		int totalpage=service.gymListCount();
		if(endPage>totalpage)
			endPage=totalpage;
		Map map=new HashMap();
		map=new HashMap();
		map.put("curpage",page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		   
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);
		
		return json;
	}
	
	@GetMapping(value="gym_detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String gym_detail_vue(int no) throws Exception
	{
		GymVO vo=service.gymListDetailData(no);
		
		List<GymReplyVO> list=gymReplyService.gymReplyListData(no); // 추가
		
		Map map=new HashMap(); // 추가
		map.put("detail_data", vo); // 추가
		map.put("reply_list", list); // 추가
		
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map); // vo에서 map으로 받음
		return json; // response.data
	}
}
