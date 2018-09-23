<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- 导入jstl标签 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SignIn</title>
<style>
* {
	padding: 0px;
	margin: 0px;
}

.content {
	position: relative;
	margin: 0 auto;
	width: 400px;
	height: 200px;
}

p {
	position: absolute;
	top: 48px;
	right: 50px;
	font-size: 12px;
	cursor: pointer;
}

.tips {
	font-size: 12px;
}
</style>
</head>
<body>
	<h1>SignIn</h1>

	<c:choose>
		<c:when test="${member > 0}">
			尊敬的${userName}<span class="sex">${userSex}</span>恭喜您是第${member}个来到游虎商城的用户,赶快登录吧!
		</c:when>
	</c:choose>
	<div class="content">
		<form action="dealSignIn" method="POST">
			手机号:<input type="text" name="userPhone" /><span class="tips"></span>
			<br>密码:<input type="text" name="userPwd" /> 
			<br>验证码:<input type="text" name="testCode" />
			<p>点击|验证码</p>
			<br>
			<input type="submit" value="submit" />
		</form>
	</div>

	<!-- 导入 JHAjax.min.js 文件 -->
	<script src="../js/JHAjax.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	// 页面最最初加载的时候要显示的内容与 Java 的 jstl 标签配合使用。
	let sex = document.getElementsByClassName("sex")[0];
	console.log(sex);
	if(sex !== undefined) {
		if(sex.innerHTML === "男") {
			sex.innerHTML = "男士";
		}else {
			sex.innerHTML = "女士";
		}
	}
	
	// 在手机号表单失焦的时候判断用户是否存在
	// 获取目标元素
	let phone = document.getElementsByName("userPhone")[0];
	let tips = document.getElementsByClassName("tips")[0];
	let form = document.getElementsByTagName("form")[0];
	let p = document.getElementsByTagName("p")[0];
	let phone_status = false;
	
	phone.addEventListener('blur', function() {
		
		
		// 发送 Ajax 判断该用户是否存在
		$.Ajax({
			url: "isExists",
			data: {
				userPhone: this.value
			},
			async: true,
			dataType: "JSON",
			method: "POST",
			timeout: 6000
		}).then((res) => {
			res = eval("(" + res + ")");
			checkUtils.judgeInfo(res["state"]);
		},(error) => {
			
		});
		
		
	}, false);
	
	// 为点击 p 绑定点击事件
	p.addEventListener('click', function() {
		// 清空上一次的内容
		this.innerHTML = "";
		// 发送 Ajax 请求获取验证码
	$.Ajax({
		url: "reqRandomTestCode",
		data: {
			digit: 4
		},
		async: true,
		dataType: "JSON",
		method: "POST",
		timeout: 6000
	}).then((res) => {
		res = eval("(" + res + ")")["testCode"];
		this.innerHTML = res;
	},(error) => {
		
	});
		
		
	}, false);
	
	
	// 为 form 表单绑定提交事件
	form.addEventListener('submit', function(e) {
		
		// 获取密码表单项
		let userPwd = document.getElementsByName("userPwd")[0];
		// 获取手机表单项
		// 获取验证码表单项
		let testCode = document.getElementsByName("testCode")[0];
		
		// 获取验证码框的内容
		
		
		// 判断密码与验证码和手机 -> 不符合要求就阻止表单提交
		let bol = checkUtils.checkArgs({
			userPwd: userPwd.value,
			userPhone: phone.value,
			testCode: testCode.value
		});
		console.log(bol);
		if(!bol) {
			e.preventDefault();
		}
		
	}, false);
	
	
	let checkUtils = {
		judgeInfo(val) {
			if(val === "yes") {
				tips.innerHTML = "可以使用";
				tips.style.color = 'green';
				phone_status = true;
				
			}else if(val === "no") {
				tips.innerHTML = "该用户不存在";
				tips.style.color = 'red';
				return false;
			}else {
				tips.innerHTML = val;
				tips.style.color = 'red';
				return false;
			}
			return true;
		},
		checkArgs(obj) {
			let keys = Object.keys(obj);
			
			for(let i = 0;i < keys.length;i++) {
				let key = keys[i];
				let val = obj[keys[i]];
				switch(key) {
				case "userPwd":
					if(!(val.length === 6 && /^[a-zA-Z]\w{5}/.test(val))) {
						alert("A");
						return false;
					}
					break;
				case "userPhone": 	
					if(!(val.length === 11 && /^1[34578][0-9]\d{8}/.test(val))) {
						alert("B");
						return false;
					}
					break;
				case "testCode":
					if(val !== p.innerHTML) {
						alert("C");
						return false;
					}
					break;
				}
				
			}
			
			return true;
		}
	}
</script>
</body>
</html>