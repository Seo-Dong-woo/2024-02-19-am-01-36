<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
	.title{
		color: white;
	}
</style>
</head>
<body>
  <!-- Footer Section Begin -->
    <footer class="footer-section">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <div class="footer-left">
                        <h3 class="title">인기 공지사항</h3>
                        <ul>
                            <c:forEach var="vo" items="${nList }">
					      	  <li><a href="#">${vo.subject }</a></li>
					      	</c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="footer-widget">
                        <h3 class="title">실시간 인기 헬스장</h3>
                        <ul>
                            <c:forEach var="vo" items="${gList }">
					      	  <li><a href="#">${vo.title }</a></li>
					      	</c:forEach>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-4">
                    <div class="footer-widget">
                    	<h3 class="title">건강 관련 뉴스</h3>
                        <ul>
                            <c:forEach var="vo" items="${newList }" varStatus="s">
					          <c:if test="${s.index<7 }">
					            <li><a href="${vo.link }">${vo.title }</a></li>
					          </c:if>
					        </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="copyright-reserved">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="copyright-text">
                            <!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">Colorlib</a>
<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
                        </div>
                        <div class="payment-pic">
                            <img src="../img/payment-method.png" alt="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </footer>
    <!-- Footer Section End -->
</body>
</html>