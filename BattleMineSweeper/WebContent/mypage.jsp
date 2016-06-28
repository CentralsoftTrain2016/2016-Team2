<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="service.bean.UserBean" scope="request" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>マイページ</title>
</head>
<body>
<% request.setCharacterEncoding("UTF-8"); %>
<%-- 表の中に入れ込む形式で記述してます --%>
<table border=0 style="width:480px;font-size:36px;text-align:left">
 <tr ><td style="width:100px;text-align:right"><%=bean.getUserName().get()%></td><td style="width:2em;font-size:28px" >さん</td><td rowspan=3 style="text-align:center">
 <Div Align="center"><img src="logoImg/logologo.jpg" alt="機雷掃除人ロゴ" width="150" height="150" ></Div>
</td></tr>
 <tr><td style="color:#ff2020;width:100px;text-align:right"><%=bean.getWinCount().get()%></td><td style="width:2em;font-size:28px">勝</td></tr>
 <tr><td style="color:#4040ff;width:100px;text-align:right"><%=bean.getLoseCount().get()%></td><td style="width:2em;font-size:28px">敗</td></tr>
</table>
<form  method="POST" action="MypageSingleServlet" >
	<input type="submit" value="シングル" style="font-size:28px;font-weight:bold;width:120px;height:100px;margin:0px; float:left;">
</form>

<form  method="POST" action="MypageBattleServlet" >
	<input type="submit" value="対戦" style="font-size:28px;font-weight:bold;width:120px;height:100px;margin-left:15px; float:left;">
</form>

<form  method="POST" action="StartServlet">
	<input type="submit" value="タイトル"style="font-size:28px;font-weight:bold;width:120px;height:100px;margin-left:15px; float:left;">
</form>
<%--
<form  method="POST" action="MypageIdChangeServlet">
	<input type="submit" value="ID変更"style="width:200px;height:100px;margin:0px; float:left;">
</form>
--%>
</body>
</html>