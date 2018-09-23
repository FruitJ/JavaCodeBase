<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 导入jstl标签 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index.jsp</title>
</head>
<body>
	<div class="top-nav">
		<!-- 测试 -->
		<c:choose>
			<c:when test="${userName != 'go'}">
				<h1>${userName}</h1>
		<a href="cancelSignIn">log off</a>
				
			</c:when>
			<c:otherwise>
		<!-- 登录 -->
		<a href="toSignIn">sign in</a>
		<!-- 注册 -->
		<a href="toSignUp">sign up</a>
			</c:otherwise>
		</c:choose>
		<a href="buyGoods?goodsName=舒肤佳&price=10.0" class="goods1">foods</a>
	</div>
<!-- 导入 JHAjax.min.js 文件 -->
<script src="${pageContext.request.contextPath}/js/JHAjax.min.js"></script>
<script type="text/javascript">
	/*使用 JHAjax.min.js 在页面一开始加载的时候就向后台发送请求*/
	// 获取目标元素
	let goods1 = document.getElementsByClassName("goods1")[0];
	// 为 goods1 绑定点击事件
	goods1.addEventListener('click', function(e) {
		// 当购买商品的按钮被点击的时候
		let tag = false;
		// 执行以下操作
		
		let tempUrl = "JavaScript:void(0)";
		// 去后台判断当前用户是否处于登录状态
		$.Ajax({
			url: "isSignIn",
			data: {
				
			},
			async: true,
			dataType: "JSON",
			method: "POST",
			timeout: 6000
		}).then((res) => {
			res = eval("(" + res + ")");
			res = res["state"];
			if(res === "has level") {// 用户处于登录状态并且拥有该项权限
				// 判断
		let sUrl = goods1.href;
		let split = sUrl.split("&");
		console.log(split[1]);
		for(let i = 0;i < split.length;i++) {
			let index = split[i].indexOf("price");
			if(index != -1) {
				let price = split[i].replace("price=", "");
				console.log(price);
				if(/^\d{1,}\.\d{1,}$/.test(price)) {
					alert("到这里了 ...");
					tag = true;
					window.location.href = sUrl;
				}
			}
		}
				
			}else if(res === "no signIn") {// 用户处于非登录状态
				alert("请先登录!");
				
			}else if(res === "no level") {// 用户处于登录状态但是没有该项权限
				alert("对不起您没有该项权限");
				
			}else {
				
				throw new Error("服务器内部错误!");
			}
			alert(res);
		},(error) => {
			
		});

		if(!tag) {
			e.preventDefault();
		}
		
		e.preventDefault();
	}, false);
	
</script>
</body>
</html>