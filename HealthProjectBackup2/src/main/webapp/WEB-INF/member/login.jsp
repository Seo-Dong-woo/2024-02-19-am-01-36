<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>

<body>

    <!-- Breadcrumb Section Begin -->
    <div class="breacrumb-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="breadcrumb-text">
                        <a href="#"><i class="fa fa-home"></i> Home</a>
                        <span>Login</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Breadcrumb Form Section Begin -->

    <!-- Register Section Begin -->
    <div class="register-login-section spad" id="loginApp">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 offset-lg-3">
                    <div class="login-form">
                        <h2>Login</h2>
                        <form action="#">
                            <div class="group-input">
                                <label for="username">ID</label>
                                <input type=text name="id" class="input-sm" v-model="id">
                            </div>
                            <div class="group-input">
                                <label for="pass">Password</label>
                                <input type=password name="pwd" class="input-sm" v-model="pwd">
                            </div>
                            <div class="group-input gi-check">
                                <div class="gi-more">
                                    <label for="save-pass">
                                        아이디 저장
                                        <input type="checkbox" id="save-pass" name="check" v-model="ck">
                                        <span class="checkmark"></span>
                                    </label>
                                    <a href="#" class="forget-pass">비밀번호 찾기</a>
                                </div>
                            </div>
                            <button type="submit" class="site-btn login-btn">몰라</button>
                        </form>
                        <div class="switch-login">
                            <table class="table">
                              <td colspan="2" class="text-center inline">
				               <input type=button value="로그인" class="btn-danger btn-sm" @click="login()">
				               <input type=button value="취소" class="btn-info btn-sm" onclick="javascript:history.back()">
				              </td>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Register Form Section End -->

    <!-- Partner Logo Section Begin -->
    <div class="partner-logo">
        <div class="container">
            <div class="logo-carousel owl-carousel">
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="../img/logo-carousel/logo-1.png" alt="">
                    </div>
                </div>
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="../img/logo-carousel/logo-2.png" alt="">
                    </div>
                </div>
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="../img/logo-carousel/logo-3.png" alt="">
                    </div>
                </div>
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="../img/logo-carousel/logo-4.png" alt="">
                    </div>
                </div>
                <div class="logo-item">
                    <div class="tablecell-inner">
                        <img src="../img/logo-carousel/logo-5.png" alt="">
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Partner Logo Section End -->
    <script>
   let loginApp=Vue.createApp({
	   data(){
		   return{
			   id:'${userId}',
			   pwd:'',
			   ck:true
		   }
	   },
	   methods:{
		   login(){
			   if(this.id==='')
			   {
				   alert("ID를 입력하세요!!")
			   	   this.$refs.id.focus()
			   	   return
			   }
			   if(this.pwd==='')
			   {
				   alert("비밀번호를 입력하세요!!")
				   this.$refs.pwd.focus()
				   return
			   }
			   axios.get('../member/login_ok_vue.do',{
				   params:{
					   userId:this.id,
				 	   userPwd:this.pwd,
				 	   ck:this.ck
				   }
			   }).then(response=>{
				   // NOID , NOPWD , OK
				   if(response.data==='NOID')
				   {
					   alert("ID가 존재하지 않습니다")
					   this.id=''
					   this.pwd=''
					   this.ck=false
					   this.$refs.id.focus()
					   
				   }
				   else if(response.data==='NOPWD')
				   {
					   alert("비밀번호가 틀립니다!!")
					   this.pwd=''
					   this.$refs.pwd.focus()
				   }
				   else
				   {
						location.href='../main/main.do'   
				   }
			   })
			   
		   }
	   }
   }).mount('#loginApp')
  </script>
</body>
</html>