<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="result" class="service.bean.ResultBean" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>機雷掃除人 リザルト</title>
</head>
<body bgcolor="#000000" text="#000000" link="#0000ff" vlink="#800080" alink="#ff0000">
<br><br><br>
<center><p style="font-size:700%; color: #660000; font-family: 'ＭＳ 明朝'; ">爆弾踏んだゾ</p></center>
<center><p style="font-size:100%; color: #FF0000; font-family: 'ＭＳ 明朝'; "><%=result.getMyName() %> EXPLODED...</p></center>

<center>
<div style="display:inline-flex">
<form  method="POST" action="ResultGoToMyPageServlet">
	<input type="submit" value="マイページ">
</form>
</div>
</center>

</body>
</html>