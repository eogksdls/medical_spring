<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>데이터 전송완료</title>
		<style>
			table,th,td {border: 1px solid black; border-collapse: collapse;}
			th,td{width: 200px; height:35px;}
		</style>
	</head>
	<body>
		<h2>데이터전송완료</h2>
		<table>
			<tr>
				<th>정보</th>
				<th>데이터</th>
			</tr>
			<tr>
				<td>학번</td>
				<td>${stuNo}</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${name}</td>
			</tr>
			<tr>
				<td>국어</td>
				<td>${kor}</td>
			</tr>
			<tr>
				<td>취미</td>
				<td>${hobby}</td>
			</tr>
		</table>
		
	</body>
</html>