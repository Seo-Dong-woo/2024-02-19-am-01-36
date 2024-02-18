<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

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
	<div class="container">
		<div class="banner-section spad" id="findApp">
	        <div class="container-fluid">
	        	<h2 class="sectiontitle">헬스장 검색</h2>
	        	<input type=text ref="fd" size=20 class="input-sm" v-model="fd" @keyup.enter="find()">
            	<input type=button value="검색" class="btn-sm btn-primary" @click="find()">
            	<div class="container">
            		<div class="row">
            			<div class="col-md-3">
						    <div class="thumbnail">
						    	<a href="#">
						        	<img src="../img/icon-1.png" alt="Lights" style="width:100%">
						        	<div class="caption">
						          		<p>Lorem ipsum...</p>
						        	</div>
						      	</a>
						    </div>
						</div>
            		</div>
            	</div>
            </div>
		</div>
		<!-- ###################################################### -->
		<ul class="pagination">
			<li v-if="startPage>1"><a class="link" @click="prev()">&laquo; Previous</a></li>
			<li v-for="i in range(startPage,endPage)" :class="i===curpage?'active':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
		  	<li v-if="endPage<totalpage"><a class="link" @click="next()">Next &raquo;</a></li>
		</ul>
		<div id="dialog" title="헬스장 상세 보기" v-show="isShow">
        	<detail_dialog  v-bind:gym_detail="gym_detail"></detail_dialog>
      	</div>
	</div>
	<script>
	  let findApp=Vue.createApp({
		  data(){
			return {
				gym_list:[],
				fd:'마포',
				gym_detail:{},
				page_list:{},
				no:1,
				curpage:1,
				totalpage:0,
				startPage:0,
				endPage:0,
				isShow:false
			}  
		  },
		  mounted(){
			  this.dataRecv()
		  },
		  updated(){
			  
		  },
		  methods:{
			  dataRecv(){
				  axios.get('../gym/gym_vue.do',{
					  params:{
						  page:this.curpage,
						  fd:this.fd
					  }
				  }).then(response=>{
					  console.log(response)
					  this.gym_list=response.data
				  })
				  
				  axios.get('../gym/page_vue.do',{
					  params:{
						  page:this.curpage,
						  fd:this.fd
					  }
				  }).then(response=>{
					  console.log(response.data)
					  this.page_list=response.data
					  
					  this.curpage=response.data.curpage
					  this.totalpage=response.data.totalpage
					  this.startPage=response.data.startPage
					  this.endPage=response.data.endPage
				  })
			  },
			  range(start,end){
				  let arr=[]
				  let leng=end-start
				  for(let i=0;i<=leng;i++)
				  {
					  arr[i]=start
					  start++;
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
				  this.curpage=page
				  this.dataRecv()
			  },
			  find(){
				  this.curpage=1
				  this.dataRecv()
			  },
			  detail(fno){
				  this.isShow=true
				  // .do?fno=1
				  /*
				      axios.get() => 요청  
				      then() => 응답(결과)
				      catch() => 처리과정에서 오류 발생시 
				  */
				  axios.get('../gym/detail_vue.do',{
					  params:{
						  no:no
					  }
				  }).then(response=>{
					  console.log(response.data)
					  this.gym_detail=response.data
					  
					  $('#dialog').dialog({
						  autoOpen:false,
						  modal:true,
						  width:700,
						  height:600
					  }).dialog("open")
				  })/* .catch(error=>{
					  console.log(error.response) 
				  }) */
				  
			  }
		  },
		  components:{
			  // 상세보기 => dialog
			  'detail_dialog':detailComponent
		  }
	  }).mount('#findApp')
	</script>
</body>
</html>