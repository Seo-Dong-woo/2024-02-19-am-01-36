<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <!-- Header Section Begin -->
    <header class="header-section">
        <!-- <div class="header-top">
            <div class="container">
                <div class="ht-left">
                    <div class="mail-service">
                        <i class=" fa fa-envelope"></i>
                        hello.colorlib@gmail.com
                    </div>
                    <div class="phone-service">
                        <i class=" fa fa-phone"></i>
                        +65 11.188.888
                    </div>
                </div>
                <div class="ht-right">
                    <a href="#" class="login-panel"><i class="fa fa-user"></i>Login</a>
                    <div class="lan-selector">
                        <select class="language_drop" name="countries" id="countries" style="width:300px;">
                            <option value='yt' data-image="../img/flag-1.jpg" data-imagecss="flag yt"
                                data-title="English">English</option>
                            <option value='yu' data-image="../img/flag-2.jpg" data-imagecss="flag yu"
                                data-title="Bangladesh">German </option>
                        </select>
                    </div>
                    <div class="top-social">
                        <a href="#"><i class="ti-facebook"></i></a>
                        <a href="#"><i class="ti-twitter-alt"></i></a>
                        <a href="#"><i class="ti-linkedin"></i></a>
                        <a href="#"><i class="ti-pinterest"></i></a>
                    </div>
                </div>
            </div>
        </div> -->
        <div class="container">
            <div class="inner-header">
                <div class="row">
                    <div class="col-lg-2 col-md-2">
                        <div class="logo">
                            <a href="../main/main.do">
                                <img src="../img/logogam.png" alt="">
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <!-- <div class="advanced-search">
                            <button type="button" class="category-btn">All Categories</button>
                            <div class="input-group">
                                <input type="text" placeholder="What do you need?">
                                <button type="button"><i class="ti-search"></i></button>
                            </div>
                        </div> -->
                    </div>
                    <div class="col-lg-4 text-right col-md-4">
                       <c:if test="${sessionScope.userId!=null }">
                        <ul class="inline">
				          <li><i class="fa fa-phone"></i>${sessionScope.userName }(${sessionScope.authority=='ROLE_ADMIN'?'관리자':'일반사용자' })</li>
				          <li>님 로그인되었습니다</li>
				        </ul>
				       </c:if>
                        <ul class="nav-right">
                            <li class="heart-icon text-center">
                              <c:if test="${sessionScope.userId==null }">
                                <a href="../member/login.do">
                                    <i class="icon_heart_alt"></i>
                                    <span>히</span>
                                <h6>로그인</h6>
                                </a>
                              </c:if>
                              <c:if test="${sessionScope.userId!=null }">
                                <a href="../member/logout.do">
                                    <i class="icon_heart_alt"></i>
                                    <span>히</span>
                                <h6>로그아웃</h6>
                                </a>
                              </c:if>
                            </li>   	
                            <li class="cart-icon text-center">
                                <a href="../member/join.do">
                                    <i class="icon_bag_alt"></i>
                                    <span>히</span>
                                <h6>회원가입</h6>
                                </a>
                            </li>
                            
                            <li class="cart-icon text-center">
                                <a href="#">
                                    <i class="icon_bag_alt"></i>
                                    <span>히</span>
                                <h6>장바구니</h6>
                                </a>
                                <div class="cart-hover">
                                    <div class="select-items">
                                        <table>
                                            <tbody>
                                                <tr>
                                                    <td class="si-pic"><img src="../img/select-product-1.jpg" alt=""></td>
                                                    <td class="si-text">
                                                        <div class="product-selected">
                                                            <p>$60.00 x 1</p>
                                                            <h6>Kabino Bedside Table</h6>
                                                        </div>
                                                    </td>
                                                    <td class="si-close">
                                                        <i class="ti-close"></i>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="si-pic"><img src="../img/select-product-2.jpg" alt=""></td>
                                                    <td class="si-text">
                                                        <div class="product-selected">
                                                            <p>$60.00 x 1</p>
                                                            <h6>Kabino Bedside Table</h6>
                                                        </div>
                                                    </td>
                                                    <td class="si-close">
                                                        <i class="ti-close"></i>
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div class="select-total">
                                        <span>total:</span>
                                        <h5>$120.00</h5>
                                    </div>
                                    <div class="select-button">
                                        <a href="#" class="primary-btn view-card">VIEW CARD</a>
                                        <a href="#" class="primary-btn checkout-btn">CHECK OUT</a>
                                    </div>
                                </div>
                            </li>
							<li class="cart-price text-center">
                            	<i class="fa fa-user"></i>
                            	<h6>고객센터</h6>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="nav-item">
            <div class="container">
                <div class="nav-depart">
                    <div class="depart-btn">
                        
                        <span>스토어</span>
                        <ul class="depart-hover">
                            <li class="active"><a href="#">운동복</a></li>
                            <li><a href="#">영양제</a></li>

                        </ul>
                    </div>
                </div>
                <nav class="nav-menu mobile-menu">
                    <ul>
                        <li class="active"><a href="./index.html">운동/건강</a>
                        	<ul class="dropdown">
                                <li><a href="#">운동별 소모 칼로리</a></li>
                                <li><a href="#">음식 칼로리</a></li>
                                <li><a href="#">칼로리 처방 받기</a></li>
                            </ul>
                        </li>
                        <li><a href="./shop.html">예약</a>
                        	<ul class="dropdown">
                                <li><a href="#">회원권</a></li>
                                <li><a href="#">스포츠센터 예약</a></li>
                                <li><a href="../gym/gym_list.do">헬스장 목록</a></li>
                                <li><a href="../gym/gym_find.do">헬스장 찾기</a></li>
                                <li><a href="../freeboard/list.do">자유게시판</a></li>
                            </ul>
                        </li>
                        <li><a href="./blog.html">대회출전권</a></li>
                        <li><a href="#">소모임</a>
                            <ul class="dropdown">
                                <li><a href="#">모임 참가</a></li>
                                <li><a href="#">모임 톡방</a></li>
                                <li><a href="#">커뮤니티</a></li>
                            </ul>
                        </li>
                        <li><a href="./contact.html">커뮤니티</a>
                        	<ul class="dropdown">
                                <li><a href="#">공지사항</a></li>
                                <li><a href="#">자유게시판</a></li>
                                <li><a href="#">인바디보건소 찾기</a></li>
                            </ul>
                        </li>
                        <li><a href="#">실시간채팅</a>
                            <ul class="dropdown">
                                <li><a href="./blog-details.html">Blog Details</a></li>
                                <li><a href="./shopping-cart.html">Shopping Cart</a></li>
                                <li><a href="./check-out.html">Checkout</a></li>
                                <li><a href="./faq.html">Faq</a></li>
                                <li><a href="./register.html">Register</a></li>
                                <li><a href="./login.html">Login</a></li>
                            </ul>
                        </li>
                    </ul>
                </nav>
                <div id="mobile-menu-wrap"></div>
            </div>
        </div>
    </header>
    <!-- Header End -->
</body>
</html>