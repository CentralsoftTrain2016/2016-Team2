<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
<jsp:useBean id="bean" class="service.bean.UserBean" scope="request" />
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>機雷掃除人 難易度選択</title>
</head>
<body>
<H1>－難易度選択－</H1>
<form  method="POST" action="DifficultyChoiceServlet" >
	<input type="submit" name="difficulty" value="初級" style="width:500px;height:100px">

	<input type="submit" name="difficulty" value="上級" style="width:500px;height:100px">
</form>
</body>
</html>