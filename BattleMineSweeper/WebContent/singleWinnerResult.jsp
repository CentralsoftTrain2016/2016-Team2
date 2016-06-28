<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="result" class="service.bean.ResultBean" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>機雷掃除人 リザルト</title>
</head>
<body bgcolor="#FFFF99" text="#000000" link="#0000ff" vlink="#800080" alink="#ff0000">
<br><br><br>
<center><p style="font-size:700%; color: #FF4500; font-family: 'ＭＳ ゴシック';">クリア!!やったぜ</p></center>
<center><p style="font-size:100%; color: #000000; font-family: 'ＭＳ ゴシック';"><%=result.getMyName() %> SURVIVED!</p></center>

<center>
<div style="display:inline-flex">
<form  method="POST" action="ResultGoToMyPageServlet">
	<input type="submit" value="マイページ">
</form>
</div>
</center>

</body>
</html>