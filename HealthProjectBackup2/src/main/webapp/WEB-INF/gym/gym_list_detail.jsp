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
	        	<h2 class="sectiontitle">헬스장 상세보기</h2>
	        	<table class="table">
	            	<tr>
	              		<td colspan="2" class="text-center">
	                		<img :src="gym_detail.poster" style="height: 300px">
	              		</td>
	            	</tr>
	            	<tr>
	              		<td colspan="2">
	                		<h3><span id="name">{{gym_detail.title}}</span>&nbsp;<span style="color:orange"><%-- {{food_detail.score}} --%></span></h3>
	              		</td>
	            	</tr>
	            	<tr>
	              		<th width="15%">주소</th>
	              		<td width="55%">{{gym_detail.address}}</td>
	            	</tr>
	            	<tr>
	              		<th width="15%">영업시간</th>
	              		<td width="55%">{{gym_detail.time}}</td>
	            	</tr>
	            	<tr>
	              		<th width="15%">전화</th>
	              		<td width="55%">{{gym_detail.phone}}</td>
	            	</tr>
	            	<tr>
	              		<th width="15%">영업시간</th>
	              		<td width="55%">{{gym_detail.time}}</td>
	            	</tr>
	            	<tr>
	              		<td colspan="3" class="text-right inline">
	                		<input type="button" class="btn-xs btn-success" value="찜하기">&nbsp;
	                		<input type="button" class="btn-xs btn-info" value="예약">&nbsp;
	                		<input type="button" class="btn-xs btn-warning" value="목록" @click="goback()">
	              		</td>
	            	</tr>
	            </table>
			</div>
		</div>
		<div class="container"> 
    		<div id="map" style="width:100%;height:350px;"></div>
    	</div>
    	<div style="height: 20px"></div>
    	<table class="table">
     		<tr>
      			<td>
        			<table class="table" v-for="rvo in reply_list">
         				<tr>
          					<td class="text-left">◑{{rvo.userName}}({{rvo.dbday}})</td>
          					<td class="text-right">
           						<span class="inline" v-if="rvo.userId===sessionId"> <!-- v-if="rvo.userId===sessionId" -->
            						<input type=button class="btn-xs btn-danger" value="수정" @click="updateForm(rvo.no)" :id="'up'+rvo.no">&nbsp;
            						<input type=button class="btn-xs btn-info" value="삭제" @click="replyDelete(rvo.no)">
           						</span>
          					</td>
         				</tr>
         				<tr>
           					<td colspan="2" class="text-left" valign="top">
            					<pre style="white-space: pre-wrap;background-color: white;border:none">{{rvo.msg}}</pre>
           					</td>
         				</tr>
         				<tr style="display: none" :id="'u'+ rvo.no" class="ups">
	       					<td colspan="2">
	         					<textarea rows="4" cols="85" :id="'u_msg'+rvo.no" style="float: left">{{rvo.msg}}</textarea>
	         					<input type=button value="댓글수정" class="btn-danger" style="float: left;width: 80px;height: 86px" @click="replyUpdate(rvo.no)">
	       					</td>
	      				</tr>
        			</table>
      			</td>
     		</tr>
    	</table>
    	<table class="table" v-if="sessionId"><!-- v-if="sessionId" -->
      		<tr>
       			<td>
         			<textarea rows="4" cols="85" class="msg" style="float: left" v-model="msg"></textarea>
         			<input type=button value="댓글쓰기" class="btn-danger" style="float: left;width: 80px;height: 86px" @click="replyInsert()">
       			</td>
      		</tr>
    	</table>
    </div>
    <div style="height: 20px"></div>
    <script>
	  let gApp=Vue.createApp({
		  data(){
			  return {
				  gym_detail:{},
				  no:${no},
				  reply_list:[],
				  sessionId:'${sessionId}', // 추가
				  msg:'', // 추가
				  u:0 // 추가
			  }
		  },
		  mounted(){
			  axios.get('../gym/gym_detail_vue.do',{
				  params:{
					  no:this.no
				  }
			  }).then(response=>{
				  console.log(response.data)
				  this.gym_detail=response.data.detail_data // .detail_data를 추가함
				  this.reply_list=response.data.reply_list // 추가
				  
				  if(window.kakao && window.kakao.maps)
				  {
					  this.initMap()
				  }
				  else
				  {
					  this.addScript()
				  }
			  })
		  },
		  methods:{
			  replyUpdate(no){
				  
		      	  let msg=$('#u_msg'+no).val()
		    	  //alert(msg)
		    	  axios.post('../gym/reply_update_vue.do', null, {
		    		  params:{
		    			  no:no,
		    			  gno:this.no,
		    			  msg:msg
		    		  }
		    	  }).then(response=>{
		    		  this.reply_list=response.data
		    		  $('#u'+no).hide('slow')
		    		  $('#up'+no).val('수정')
		    	  })
		      },
		      updateForm(no){
		    	  $('.ups').hide()
		    	  $('#up' + no).val('수정')
		    	  if(this.u==0)
		    	  {
		    		  this.u=1
		    		  $('#u' + no).show()
		    		  $('#up' + no).val('취소')
		    	  }
		    	  else
		    	  {
		    		  this.u=0
		    		  $('#u' + no).hide()
		    		  $('#up' + no).val('수정')
		    	  }
		      },
		      replyDelete(no){
		    	  axios.get('../gym/reply_delete_vue.do',{
		    		  params:{
		    			  no:no,
		    			  gno:this.no
		    		  }
		    	  }).then(response=>{
		    		  this.reply_list=response.data
		     	  }) 
		      },
		      replyInsert(){
		    	  if(this.msg==="")
		    	  {
		    		  this.$refs.msg.focus()
		    		  return
		    	  }
		    		
		    	  axios.post('../gym/reply_insert_vue.do',null,{
		    		  params:{
		    			  gno:this.no,
		    			  msg:this.msg
		    		  }
		    	  }).then(response=>{
		    		  this.reply_list=response.data
		    		  this.msg=''
		    	  })
		      },
			  addScript(){
				  const script=document.createElement("script")
				  
				  /*global kakao*/
				  script.onload=()=>kakao.maps.load(this.initMap)
				  script.src="http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=75fbea090d02af5a01e3d114d2612fe5&libraries=services"
				  document.head.appendChild(script)
			  },
			  initMap(){
				  var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    		    mapOption = {
	    		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
	    		        level: 3 // 지도의 확대 레벨
	    		    };  
	
	    		// 지도를 생성합니다    
	    		var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	    		// 주소-좌표 변환 객체를 생성합니다
	    		var geocoder = new kakao.maps.services.Geocoder();
	
	    		// 주소로 좌표를 검색합니다
	    		geocoder.addressSearch(this.gym_detail.address, function(result, status) {
	
	    		    // 정상적으로 검색이 완료됐으면 
	    		     if (status === kakao.maps.services.Status.OK) {
	
	    		        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
	
	    		        // 결과값으로 받은 위치를 마커로 표시합니다
	    		        var marker = new kakao.maps.Marker({
	    		            map: map,
	    		            position: coords
	    		        });
	
	    		        // 인포윈도우로 장소에 대한 설명을 표시합니다
	    		        var infowindow = new kakao.maps.InfoWindow({
	    		            content: '<div style="width:150px;text-align:center;padding:6px 0;">'+$("#name").text()+'</div>'
	    		        });
	    		        infowindow.open(map, marker);
	
	    		        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	    		        map.setCenter(coords);
	    		    } 
	    		 });    
			  },
			  goback(){
				  // window.history.back() // 이 부분은 쿠키 저장하기 전으로 돌아감
				  location.href="../gym/gym_list.do" // 이렇게 해야 함
			  }
		  }
	    }).mount("#gymApp")
	  </script>
</body>
</html>