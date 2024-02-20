<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<style type="text/css">
a.link:hover,img.img_click:hover{
  cursor: pointer;
}
</style>
</head>
<body>
	<div class="container" id="gymApp">
		<div class="banner-section spad">
	        <div class="container-fluid">
	        	<h2 class="sectiontitle">헬스장 목록</h2>
	        	<div class="row">
       				<div class="col-md-3" v-for="(vo,index) in gym_list">
       					<a :href="'../gym/gym_before_list_detail.do?no='+vo.no">
		    				<div class="thumbnail">
		        				<img :src="vo.poster" style="width:100%">
		        				<div class="caption">
		          					<p style="font-size:8px">{{vo.title}}</p>
		        				</div>
		    				</div>
	   				 	</a>
	  				</div>
     			</div>
     		<div style="height: 20px"></div>
     		<div class="row">
       			<div class="text-center">
         			<ul class="pagination">
           				<li v-if="startPage>1"><a class="link" @click="prev()">&laquo;</a></li>
           				<li v-for="i in range(startPage,endPage)" :class="curpage===i?'active':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
           				<li v-if="endPage<totalpage"><a class="link" @click="next()">&raquo;</a></li>
         			</ul>
       			</div>
     		</div>
		</div>
	</div>
	</div>
	<script>
	  let gymApp=Vue.createApp({
		  // 데이터 관리 => 멤버변수 => this.
		  data(){
			  return {
				  gym_list:[],
				  curpage:1,
				  totalpage:0,
				  startPage:0,
				  endPage:0,
				  cookie_list:[]
			  }
		  },
		  mounted(){
			  // 브라우저에 화면에 HTML이 실행된 경우에 처리 => 자동 호출 함수 
			  /*
			      자동 호출 함수 => Vue 생명주기
			      beforeCreate() 
			      created()
			      ------------------ Vue 객체 생성 
			      beforeMount() => mount : 가상 메모리에  HTML을 올리는 경우 
			      ***mounted() => window.onload , $(function(){}) , componentDidMount()
			                                                     => HOOKS
			                                                     => useEffect()
			                                                     => class / function = 권장
			      beforeUpdate()
			      ***updated()
			      
			      
			  */
			  this.dataRecv()
		  },
		  updated(){
			  
		  },
		  methods:{
			  // 공통으로 사용되는 함수 => 반복제거 
			  dataRecv(){
				  axios.get('../gym/gym_list_vue.do',{
					  params:{
						  page:this.curpage
					  }
				  }).then(response=>{
					  console.log(response.data)
					  this.gym_list=response.data
				  })
				  
				  // 페이지 
				  axios.get('../gym/gym_page_vue.do',{
					  params:{
						  page:this.curpage
					  }
				  }).then(response=>{
					  console.log(response.data)
					  this.curpage=response.data.curpage
					  this.totalpage=response.data.totalpage
					  this.startPage=response.data.startPage
					  this.endPage=response.data.endPage
				  })
				  
				  axios.get('../gym/gym_cookie_vue.do').then(response=>{
					  console.log(response.data)
					  this.cookie_list=response.data
				  })
			  },
			  range(start,end){
				  let arr=[]
				  let lang=end-start
				  for(let i=0;i<=lang;i++)
				  {
					  arr[i]=start
					  start++
				  }
				  return arr
			  },
			  prev(){
				  this.curpage=this.startPage-1
				  this.dataRecv()
			  },
			  next(){
				  this.curpage=this.endPage+1
				  this.dataRecv()
			  },
			  pageChange(page){
				  this.curpage=page;
				  this.dataRecv()
			  }
		  }
	  }).mount("#gymApp")
	</script>
</body>
</html>