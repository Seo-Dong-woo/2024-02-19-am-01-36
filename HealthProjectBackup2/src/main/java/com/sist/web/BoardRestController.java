package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 자바스크립트 연동 => ajax , axios
import java.util.*;
import com.sist.vo.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
@RestController
@RequestMapping("freeboard/")
public class BoardRestController {
   @Autowired
   private FreeBoardService service;
   /*
    *   axios.get('../freeboard/list_vue.do',{
    				params:{
    					page:this.curpage
    				}
    			})
    */
   @GetMapping(value="list_vue.do",produces = "text/plain;charset=UTF-8")
   public String freeboard_list(int page) throws Exception
   {
	   int rowSize=10;
	   int start=(rowSize*page)-(rowSize-1);
	   int end=rowSize*page;
	   List<FreeBoardVO> list=service.freeboardListData(start, end);
	   //JSON변경 
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(list);
	   return json;
   }
   /*
    *  axios.get("../freeboard/page_vue.do",{
    				params:{
    					page:this.curpage
    				}
    			}).then()
    */
   @GetMapping(value="page_vue.do",produces = "text/plain;charset=UTF-8")
   public String freeboard_page(int page) throws Exception
   {
	   int totalpage=service.freeboardTotalPage();
	   Map map=new HashedMap();
	   map.put("curpage", page);
	   map.put("totalpage", totalpage);
	   
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(map);
	   return json;
   }
   
   @PostMapping(value="insert_vue.do",produces = "text/plain;charset=UTF-8")
   public String freeboard_insert(FreeBoardVO vo)
   {
	   String result="";
	   try
	   {
		   service.freeboardInsert(vo);
		   result="yes";
	   }catch(Exception ex)
	   {
		   result=ex.getMessage();
	   }
	   return result;
   }
   @GetMapping(value="detail_vue.do",produces = "text/plain;charset=UTF-8")
   public String freeboard_detail(int no) throws Exception
   {
	   FreeBoardVO vo=service.freeboardDetailData(no);
	   // VO => JSON => {}  => List => JSON => [{},{},{}...] vue , react , ajax => JSON
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(vo);
	   // {no:1,name:'',subject:''...} => 키는 멤버변수
	   return json;
   }
   @GetMapping(value="delete_vue.do",produces = "text/plain;charset=UTF-8")
   public String freeboard_delete(int no,String pwd)
   {
	   String result=service.freeboardDelete(no, pwd);
	   return result;
   }
   @GetMapping(value="update_vue.do",produces = "text/plain;charset=UTF-8")
   public String freeboard_update(int no) throws Exception
   {
	   FreeBoardVO vo=service.freeboardUpdateData(no);
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(vo);
	   return json;
   }
   @PostMapping(value="update_ok_vue.do",produces = "text/plain;charset=UTF-8")
   public String freeboard_update_ok(FreeBoardVO vo)
   {
	   String result=service.freeboardUpdate(vo);
	   return result;
   }
}