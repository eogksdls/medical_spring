<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>회원정보수정</title>
	</head>
	<body>
		<h2>회원정보입력</h2>
		<form action="doMember" name="mfrm" method="post">
			<label>ID</label>
			<input type="text" name="id" value="${id }"><br>
			<label>PW</label>
			<input type="text" name="pw" value="${pw }"><br>
			<label>이름</label>
			<input type="text" name="name" value="${name }"><br>
			<label>전화번호</label>
			<input type="text" name="phone" value="${phone }"><br>
			<label>성별</label><br>
			<input type="radio" name="gender" id="male" value="male">
			<label for="male">남자</label>
			<input type="radio" name="gender" id="female" value="female">
			<label for="female">여자</label>
			<br>
			<label>취미</label>
			<input type="checkbox" name="hobby" value="game" id="game">
			<label for="game">게임</label>
			<input type="checkbox" name="hobby" value="golf" id="golf">
			<label for="golf">골프</label>
			<input type="checkbox" name="hobby" value="run" id="run">
			<label for="run">조깅</label>
			<input type="checkbox" name="hobby" value="swim" id="swim">
			<label for="swim">수영</label>
			<input type="checkbox" name="hobby" value="book" id="book">
			<label for="book">독서</label>
			<br>
			<br>
			
			<input type="submit" value="확인">
		</form>
	</body>
</html>