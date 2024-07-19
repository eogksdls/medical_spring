<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html>
<html>
<head>
<title> JARDIN SHOP </title>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="JARDIN SHOP" />
<meta name="keywords" content="JARDIN SHOP" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scaleable=no" />
<link rel="stylesheet" type="text/css" href="../css/reset.css?v=Y" />
<link rel="stylesheet" type="text/css" href="../css/layout.css?v=Y" />
<link rel="stylesheet" type="text/css" href="../css/content.css?v=Y" />
<script src="https://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="../js/top_navi.js"></script>
<script type="text/javascript" src="../js/left_navi.js"></script>
<script type="text/javascript" src="../js/main.js"></script>
<script type="text/javascript" src="../js/common.js"></script>
<script type="text/javascript" src="../js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="../js/idangerous.swiper-2.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.anchor.js"></script>
<style>
	.replyBtn{cursor : pointer;}
</style>
<script type="text/javascript">
$(document).ready(function() {
	


});
</script>
</head>
<body>


<!--IE 6,7,8 사용자에게 브라우저 업데이터 설명 Div 관련 스크립트-->
 <script type="text/javascript">

     var settimediv = 200000; //지속시간(1000= 1초)
     var msietimer;

     $(document).ready(function () {
         msiecheck();
     });

     var msiecheck = function () {
         var browser = navigator.userAgent.toLowerCase();
         if (browser.indexOf('msie 6') != -1 ||
                browser.indexOf('msie 7') != -1 ||
				 browser.indexOf('msie 8') != -1) {
             msieshow();			 
         }
         else {
             msiehide();
         }
     }

     var msieshow = function () {
        $("#ieUser").show();
        msietimer = setTimeout("msiehide()", settimediv);
     }

     var msiehide = function () {
		$("#ieUser").hide();
        clearTimeout(msietimer);
     }
</script>

<div id="allwrap">
<div id="wrap">

	<%@ include file="../top/top.jsp" %>
	<!-- container -->
	<div id="container">

		<div id="location">
			<ol>
				<li><a href="#">HOME</a></li>
				<li><a href="#">CUSTOMER</a></li>
				<li class="last">NOTICE</li>
			</ol>
		</div>
		
		<div id="outbox">		
			<div id="left">
				<div id="title2">CUSTOMER<span>고객센터</span></div>
				<ul>	
					<li><a href="#" id="leftNavi1">NOTICE</a></li>
					<li><a href="#" id="leftNavi2">1:1문의</a></li>
					<li><a href="#" id="leftNavi3">FAQ</span></a></li>
					<li class="last"><a href="#" id="leftNavi4">이용안내</a></li>
				</ul>			
			</div><script type="text/javascript">initSubmenu(1,0);</script>


			<!-- contents -->
			<div id="contents">
				<div id="customer">
					<h2><strong>NOTICE</strong><span>쟈뎅샵 소식을 전해드립니다.</span></h2>

					<div class="viewDivMt">
						<div class="viewHead">
							<div class="subject">
								<ul>
									<li>${nDto.btitle }</li>
								</ul>
							</div>
							<div class="day">
								<p class="txt">작성일<span>${nDto.bdate }</span></p>
							</div>
						</div>

						<div class="viewContents">
							${nDto.bcontent }
						</div>
					</div>


					<!-- 이전다음글 -->
					<div class="pnDiv web">
						<table summary="이전다음글을 선택하여 보실 수 있습니다." class="preNext" border="1" cellspacing="0">
							<caption>이전다음글</caption>
							<colgroup>
							<col width="100px" />
							<col width="*" />
							</colgroup>
							<tbody>
								<tr>
									<th class="pre">PREV</th>
									<td><a href="#">상품 재입고는 언제 되나요?</a></td>
								</tr>

								<tr>
									<th class="next">NEXT</th>
									<td>다음 글이 없습니다.</td>
								</tr>
							</tbody>
						</table>
					</div>
					<!-- //이전다음글 -->
					<script>
					$(function(){

						let count = '${list.size()}';
						console.log("댓글 개수 : "+count);

						let bno,id,cpw,ccontent,cdate; // 전역변수 선언
						
						//등록버튼 클릭
						$(".replyBtn").click(function(){
							//alert("등록버튼 클릭")
							if(${sessionId == null} ){
								alert("로그인을 하셔야 댓글작성이 가능합니다.")
								//location.href="/member/login";
								$(".replyType").val("");
								return false;
							}
							
							
							let bno = "${nDto.bno}";
							let id = "${sessionId}";
							let cpw = $(".replynum").val();
							let ccontent = $(".replyType").val();

							console.log("bno:"+bno);
							console.log("id:"+id);
							console.log("cpw:"+cpw);
							console.log("ccontent:"+ccontent);


							$.ajax({
								url:"/customer/commentBwrite",
								type:"post",
								data:{"bno":bno,"id":id,"cpw":cpw,"ccontent":ccontent},
								dataType:"json", // 기본이 text
								success:function(data){
									alert("댓글이 저장되었습니다.");
									let htmlData="";
									htmlData += '<ul id='+data.cno+' >';
									htmlData += '<li class="name">'+data.id+' <span>['+moment(data.cdata).format("YYYY-MM-DD HH:mm:ss")+']</span></li>';
									htmlData += '<li class="txt">'+data.ccontent+'</li>';
									htmlData += '<li class="btn">';
									htmlData += '<a class="rebtn uBtn">수정</a>&nbsp';
									htmlData += '<a class="rebtn dBtn">삭제</a>';
									htmlData += '</li>';
									htmlData += '</ul>';

									if(count>0) $(".replyBox").prepend(htmlData);
									else $(".replyBox").html(htmlData);
									// 총개수 +1 증가
									$("#comment_number").text(Number($("#comment_number").text())+1)
									// 글쓰기 초기화
									$(".replynum").val("");
									$(".replyType").val("");


								},
								error:function(){
									alert("실패");
								}
							});
						}); // replyBtn
						// 삭제버튼 클릭
						$(document).on("click",".dBtn",function(){
							console.log("ui cno : "+ $(this).closest('ul').attr('id'));
							if(!confirm("댓글을 삭제하시겠습니까?")){
								return false;
							}
							let cno= $(this).closest('ul').attr('id');

							$.ajax({
								url:"/customer/commentBDelete",
								type:"post",
								data:{"cno":cno},
								dataType:"text", // 기본이 text
								success:function(data){
									alert("댓글이 삭제되었습니다.");
									$("#"+cno).remove();
									// 총개수 -1 감소
									$("#comment_number").text(Number($("#comment_number").text())-1)
								},
								error:function(){
									alert("실패");
								}
							}); // ajax

						}); // dBtn

						// 수정버튼 클릭
						$(document).on("click",".uBtn",function(){
							alert("수정버튼 클릭");
							console.log("ui cno : "+ $(this).closest('ul').attr('id'));

							cno = $(this).closest('ul').attr('id');
							id = '${sessionId}';
							cdate = $(this).closest('ul').children('.name').children('span').text();
							ccontent = $(this).closest('ul').children('.txt').text();

							console.log("cno : "+ cno);
							console.log("id : "+ id);
							console.log("cdate : "+ cdate);
							console.log("ccontent : "+ ccontent);

							let htmlData="";
							htmlData += '<li class="name">'+id+' <span>'+cdate+'</span></li>';
							htmlData += '<li class="txt"><textarea class="replyType">'+ccontent+'</textarea></li>';
							htmlData += '<li class="btn">';
							htmlData += '<a class="rebtn updateBtn">완료</a> ';
							htmlData += '<a class="rebtn cancelBtn">취소</a>';
							htmlData += '</li>';

							$("#"+cno).html(htmlData);

						}); // uBtn

						// 수정에서 취소버튼
						$(document).on("click",".cancelBtn",function(){
							alert("취소버튼을 클릭하셨습니다.");
							// 데이터 확인
							console.log("ui cno : "+ $(this).closest('ul').attr('id'));
							console.log("update cno : "+ cno);
							console.log("update id : "+ id);
							console.log("update cdate : "+ cdate);
							console.log("update ccontent : "+ ccontent);

							// 데이터 넣기
							let htmlData="";
							htmlData += '<li class="name">'+id+' <span>'+cdate+'</span></li>';
							htmlData += '<li class="txt">'+ccontent+'</li>';
							htmlData += '<li class="btn">';
							htmlData += '<a class="rebtn uBtn">수정</a>&nbsp';
							htmlData += '<a class="rebtn dBtn">삭제</a>';
							htmlData += '</li>';

							$("#"+cno).html(htmlData);

						}); // cancelBtn

						// 수정에서 완료버튼
						$(document).on("click",".updateBtn",function(){
							alert("수정이 완료되었습니다.")
							// 데이터 확인
							console.log("ui cno : "+ $(this).closest('ul').attr('id'));
							ccontent = $(this).closest('ul').children('.txt').children('.replyType').val();


							console.log("update cno : "+ cno);
							console.log("update id : "+ id);
							console.log("update ccontent : "+ ccontent);
							
							//수정한 내용을 controller로 전송
							$.ajax({
								url:"/customer/commentBUpdate",
								type:"post",
								data:{"cno":cno,"id":id,"ccontent":ccontent},
								dataType:"json", // 기본이 text
								success:function(data){
									alert("댓글이 수정되었습니다.");
									
									let htmlData="";
									htmlData += '<li class="name">'+data.id+' <span>['+moment(data.cdata).format("YYYY-MM-DD HH:mm:ss")+']</span></li>';
									htmlData += '<li class="txt">'+data.ccontent+'</li>';
									htmlData += '<li class="btn">';
									htmlData += '<a class="rebtn uBtn">수정</a>&nbsp';
									htmlData += '<a class="rebtn dBtn">삭제</a>';
									htmlData += '</li>';

									// 현재위치에 수정코드 입력
									$("#"+cno).html(htmlData);

								},
								error:function(){
									alert("실패");
								}


							});//ajax
							

						}); // updateBtn
					}); // jquery
					</script>

					<!-- 댓글-->
					<div class="replyWrite">
						<ul>
							<li class="in">
								<p class="txt">총 <span class="orange">${countComment }</span> 개의 댓글이 달려있습니다.</p>
								<p class="password">비밀번호&nbsp;&nbsp;<input type="password" class="replynum" /></p>
								<textarea class="replyType"></textarea>
							</li>
							<li class="btn"><a class="replyBtn" id="rBtn" >등록</a></li>
						</ul>
						<p class="ntic">※ 비밀번호를 입력하시면 댓글이 비밀글로 등록 됩니다.</p>
					</div>

					<div class="replyBox">
						<c:if test="${countComment == 0 }">
							<ul>
								댓글이 없습니다.
							</ul>
						</c:if>
						<c:if test="${countComment > 0 }">
							<c:forEach items="${list }" var="cdto">
								<ul id="${cdto.cno }">
									<li class="name">${cdto.id } <span>${cdto.cdate }</span></li>
									
									<c:if test="${sessionId != cdto.id and cdto.cpw != null }">
										<li class="txt">
											<a class="passwordBtn"><span class="orange">※ 비밀글입니다.</span></a>
										</li>
									</c:if>
									<c:if test="${sessionId != cdto.id and cdto.cpw == null }">
										<li class="txt">${cdto.ccontent }</li>
									</c:if>
									<c:if test="${sessionId == cdto.id}">
										<li class="txt">${cdto.ccontent }</li>
										<li class="btn">
											<a class="rebtn uBtn">수정</a>
											<a class="rebtn dBtn">삭제</a>
										</li>
									</c:if>
								</ul>
							</c:forEach>
						</c:if>
						
						<!-- 비밀글 폼 -->
						<!-- 
						<ul>
							<li class="name">jjabcde <span>[2014-03-04&nbsp;&nbsp;15:01:59]</span></li>
							<li class="txt">
								<a href="password.html" class="passwordBtn"><span class="orange">※ 비밀글입니다.</span></a>
							</li>
						</ul>
						 -->
						<!-- 비밀글 폼 -->
					</div>
					<!-- //댓글 -->
					

					<!-- Btn Area -->
					<div class="btnArea btline">
						<div class="bRight">
							<ul>
								<li><a href="/customer/notice" class="sbtnMini mw">목록</a></li>
							</ul>
						</div>
					</div>
					<!-- //Btn Area -->
					
				</div>
			</div>
			<!-- //contents -->


		</div>
	</div>
	<!-- //container -->

	<%@ include file="../footer/footer.jsp" %>

</div>
</div>
</body>
</html>