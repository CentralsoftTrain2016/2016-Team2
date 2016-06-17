<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
<jsp:useBean id="bean" class="service.bean.UserBean" scope="request" />
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>マイページ</title>
</head>
<body>
<H1>マイページ</H1>
<form  method="POST" action="MypageSingleServlet" >
	<input type="submit" value="シングル" style="width:200px;height:100px;margin:0px; float:left;">
</form>

<form  method="POST" action="MypageBattleServlet" >
	<input type="submit" value="対戦" style="width:200px;height:100px;margin:0px; float:left;">
</form>

<form  method="POST" action="MypageGuideServlet">
	<input type="submit" value="図鑑"style="width:200px;height:100px;margin:0px; float:left;">
</form>

<form  method="POST" action="MypageIdChangeServlet">
	<input type="submit" value="ID変更"style="width:200px;height:100px;margin:0px; float:left;">
</form>

</body>
</html>