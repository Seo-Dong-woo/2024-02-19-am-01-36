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
<div class="wrapper row3">
  <main class="container clear"> 
  <h2 class="sectiontitle">맛집 검색</h2>
  <div class="content" id="findApp"> 
      <div id="gallery">
        <figure>
          <header class="heading inline">
            <input type=text ref="fd" size=20 class="input-sm" v-model="fd" @keyup.enter="find()">
            <input type=button value="검색" class="btn-sm btn-primary" @click="find()">
          </header>
          <ul class="nospace clear">
            <li v-for="(vo,index) in food_list" :class="index%4==0?'one_quarter first':'one_quarter'"><img class="img_click" :src="'http://www.menupan.com'+vo.poster" :title="vo.name" @click="detail(vo.fno)"></li>
          </ul>
          <figcaption>Gallery Description Goes Here</figcaption>
        </figure>
      </div>
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a class="link" @click="prev()">&laquo; Previous</a></li>
          <li v-for="i in range(startPage,endPage)" :class="i===curpage?'current':''"><a class="link" @click="pageChange(i)">{{i}}</a></li>
          <li v-if="endPage<totalpage"><a class="link" @click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
      <div id="dialog" title="맛집 상세 보기" v-show="isShow">
        <detail_dialog  v-bind:food_detail="food_detail"></detail_dialog>
      </div>
     </div>
    <div class="clear"></div>
  </main>
</div>
<%--
   class A
   {
       B b=new B() => 매개변수
                      => 태그의 속성 , 멤버변수  
   }
   class B
   {
      
   }
 --%>
<script>
  const detailComponent={
	  props:['food_detail'],
	  template:`<h3 class="text-center">맛집 상세보기</h3>
	            <table class="table">
	            <tr>
	              <td width=30% class="text-center" rowspan="9">
	                <img :src="'http://www.menupan.com'+food_detail.poster" style="width:100%">
	              </td>
	              <td colspan="2">
	                <h3>{{food_detail.name}}&nbsp;<span style="color:orange">{{food_detail.score}}</span></h3>
	              </td>
	            </tr>
	            <tr>
	              <th width="15%">주소</th>
	              <td width="55%">{{food_detail.address}}</td>
	            </tr>
	            <tr>
	              <th width="15%">전화</th>
	              <td width="55%">{{food_detail.phone}}</td>
	            </tr>
	            <tr>
	              <th width="15%">음식종류</th>
	              <td width="55%">{{food_detail.type}}</td>
	            </tr>
	            <tr>
	              <th width="15%">영업시간</th>
	              <td width="55%">{{food_detail.time}}</td>
	            </tr>
	            <tr>
	              <th width="15%">가격대</th>
	              <td width="55%">{{food_detail.price}}</td>
	            </tr>
	            <tr>
	              <th width="15%">좌석</th>
	              <td width="55%">{{food_detail.seat}}</td>
	            </tr>
	            <tr>
	              <th width="15%">테마</th>
	              <td width="55%">{{food_detail.theme}}</td>
	            </tr>
	            </table>
	           `
  }
  let findApp=Vue.createApp({
	  data(){
		return {
			food_list:[],
			fd:'마포',
			food_detail:{},
			page_list:{},
			fno:1,
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
			  axios.get('../food/find_vue.do',{
				  params:{
					  page:this.curpage,
					  fd:this.fd
				  }
			  }).then(response=>{
				  console.log(response)
				  this.food_list=response.data
			  })
			  
			  axios.get('../food/page_vue.do',{
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
			  axios.get('../food/detail_vue.do',{
				  params:{
					  fno:fno
				  }
			  }).then(response=>{
				  console.log(response.data)
				  this.food_detail=response.data
				  
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