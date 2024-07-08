<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보수정</title>
		<style>
			table,th,td{ border:1px solid black; border-collapse:collapse; }
			th,td{ width:200px; }
		</style>
	</head>
	<body>
		<h2>member에서 넘어온 데이터</h2>
		<form action="memUpdate" method="post">
			<input type="hidden" name="id" value="${id }">
			<input type="hidden" name="pw" value="${pw }">
			<input type="hidden" name="name" value="${name }">
			<input type="hidden" name="phone" value="${phone }">
			<input type="hidden" name="gender" value="${gender }">
			<input type="hidden" name="hobby" value="${hobby }">
			<table>
				<tr>
					<th>회원정보</th>
					<th>데이터</th>
				</tr>
				<tr>
					<td>ID</td>
					<td>${id}</td>
				</tr>
				<tr>
					<td>PW</td>
					<td>${pw}</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${name}</td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td>${phone}</td>
				</tr>
				<tr>
					<td>성별</td>
					<td>${gender}</td>
				</tr>
				<tr>
					<td>취미</td>
					<td>${hobby}</td>
				</tr>
			
			</table>
		<input type="submit" value="수정">
		</form>
	</body>
</html>