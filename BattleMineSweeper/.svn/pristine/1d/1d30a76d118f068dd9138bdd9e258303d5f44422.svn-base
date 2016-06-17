<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bean" class="service.bean.ResultBean" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>機雷掃除人 リザルト</title>
</head>
<body bgcolor="#000000" text="#000000" link="#0000ff" vlink="#800080" alink="#ff0000">
<br><br><br>
<center><p style="font-size:700%; color: #660000; font-family: 'ＭＳ 明朝'; ">勝者：<%=bean.getEnemyName() %></p></center>
<center><p style="font-size:100%; color: #FF0000; font-family: 'ＭＳ 明朝'; "><%=bean.getMyName() %> EXPLODED...</p></center>
<center>
<div style="padding: 10px; margin-bottom: 10px; border: 5px double #333333;">
    <%=bean.getInfoDescription()  %>
</div>
</center>
<center>
<div style="display:inline-flex">
<form  method="POST" action="MyPageServlet">
	<input type="submit" value="マイページ">
</form>
<form  method="POST" action="MatchingServlet">
	<input type="submit" value="再戦する">
</form>
</div>
</center>

</body>
</html>