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
<script type="text/JavaScript">
	var set = 0;

	//サブミットボタンを1度押したら2度目以降は押せないようにする
	function double() {
		if (set == 0) {
			set = 1;
		} else {
			return false;
		}
	}
</script>
</head>
<body>
	<H1>－難易度選択－</H1>
	<form method="POST" action="SingleDifficultyChoiceServlet">
		<input type="submit" name="difficulty" value="初　級"
			onClick="return double()"
			style="width: 500px; height: 100px; font-size: 48px; text-align: center">
		<br> <input type="submit" name="difficulty" value="上　級"
			onClick="return double()"
			style="width: 500px; height: 100px; font-size: 48px; text-align: center; margin-top: 30px">
	</form>
</body>
</html>