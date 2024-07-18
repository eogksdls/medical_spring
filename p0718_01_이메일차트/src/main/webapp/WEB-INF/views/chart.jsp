<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>차트 그리기</title>
		<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
		<script  src="https://code.jquery.com/jquery-latest.min.js"></script>
		<style>
			#main{border:3px solid black; width: 70%; height:550px; margin:20px auto;}
		</style>
		<script>
			$(function(){
				let ch_label;  //지역변수 선언
				let ch_data;
				
				$("#btn").click(function(){
					alert("검색을 시작합니다.");
					
					//차트 초기화
					let chartStatus = Chart.getChart("myChart"); // <canvas> id
					if (chartStatus != undefined) {
					  	chartStatus.destroy();
					  }
					
					ch_label=[];
					ch_data=[];
					
					$.ajax({
						url:"/chart_data",
						type:"post",
						data:{"data":$("#data").val()},
						dataType:"json",
						success:function(data){
							alert("성공");
							console.log("data: "+$("#data").val())
							
							if (($("#data").val()) == "region" ){
								data.forEach(function(d){
									//console.log(d.region);
									//console.log(d.amt);
									ch_label.push(d.region);
									ch_data.push(d.amt);
								});
								
							}//if
							else if (($("#data").val()) == "period"){
								data.forEach(function(d){
									//console.log(d.period);
									//console.log(d.amt);
									ch_label.push(d.period);
									ch_data.push(d.amt);
								});
								
							}//else if
							
							//차트 그리기
							const ctx = document.getElementById('myChart');
						
						    new Chart(ctx, {
							    type: 'bar',
							    data: {
							      labels: ch_label,
							      datasets: [{
							        label: '대출액 총합계',
							        data: ch_data,
							        borderWidth: 1
							      }]
							    },
							    options: {
							      scales: {
							        y: {
							          beginAtZero: true
							        }
							      }
							    }
						   
						  	});//차트
								
							
							
						}, //success
						error:function(){
							alert("실패");
							
						}//error
					});
					
					 
				});//btn
			});//jquery
		</script>
	</head>
	<body>
		<h2>차트그리기</h2>
		<label>데이터</label>
		<select name="data" id="data">
			<option value="region">지역별</option>
			<option value="period">기간별</option>
		</select>
		
		<button type="button" id="btn">검색</button>
		<br><br>
		<div id="main">
 			<canvas id="myChart"></canvas>
		</div>
	</body>
</html>