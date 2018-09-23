<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
	span {
		color: red;
	}
</style>
</head>
<body>
	<h1>The Buy is Success!</h1>
	<h2>还有<span>5</span>s返回首页</h2>
<script type="text/javascript">
	// 获取目标元素
	let span = document.getElementsByTagName("span")[0];
	function foo() {
		// 获取目标元素值		
		let num = Number(span.innerHTML);
		num--;
		span.innerHTML = num--;
		if(num < 0) {
			clearInterval(num);
			window.location.href = "http://localhost:8080/test/dealAccessReq/toIndex";
		}
	}
	let timer = setInterval(foo, 1000);
	
</script>	
</body>
</html>