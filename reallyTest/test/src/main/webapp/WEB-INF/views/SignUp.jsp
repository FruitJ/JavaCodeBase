<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SignUp</title>
</head>
<body>
	<!-- 此处直接写方法映射不用谢类映射 -->
	<form action="dealSignUp" method="POST">
		用户名:<input type="text" name="userName" /><span></span>
	<br>年龄:<input type="text" name="userAge" />
	<br>性别:<input type="text" name="userSex" />
	<br>电话:<input type="text" name="userPhone" />
	<br>级别:<input type="text" name="userLevel" />
	<br>E-mail:<input type="text" name="e_mail" />
	<br>密码:<input type="text" name="userPwd" />
	<br><input type="submit" name="submit" />
	</form>
	
<script src="../js/JHAjax.min.js" type="text/javascript"></script>	
<script type="text/javascript">
	// 获取userPhone标签
	let phone = document.getElementsByName("userPhone")[0];
	let span = document.getElementsByTagName("span")[0];
	let form = document.getElementsByTagName("form")[0];
	let phone_status = false;
	
	phone.addEventListener('blur', function() {
		$.Ajax({
			url: "isExists",
			data: {
				userPhone: phone.value
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
	
	// 数据校验
	// 阻止表单跳转 ->   e.preventDefault();
	form.addEventListener('submit', function(e) {
		
		// 获取目标元素
		let userName = document.getElementsByName("userName")[0];
		let userAge = document.getElementsByName("userAge")[0];
		let userSex = document.getElementsByName("userSex")[0];
		let userPhone = document.getElementsByName("userPhone")[0];
		let userLevel = document.getElementsByName("userLevel")[0];
		let userEmail = document.getElementsByName("e_mail")[0];
		let userPwd = document.getElementsByName("userPwd")[0];
		
		let test_args = checkUtils.checkArgs({
			userName: userName.value,
			userAge: userAge.value,
			userSex: userSex.value,
			userPhone: userPhone.value,
			userLevel: userLevel.value,
			userEmail: userEmail.value,
			userPwd: userPwd.value
		});
		if(!test_args || phone_status === false) {
			e.preventDefault();
		}
		
	}, false);
	let checkUtils = {
		checkArgs(obj) {
			// 获对象中的所有键的集合
			let keys = Object.keys(obj);
			for(let i = 0;i < keys.length;i++) {
				
				let val = obj[keys[i]];
				console.log(obj[keys[i]]);
				switch(keys[i]) {
				case "userName":
					if(val.length > 0 && val.length < 10) {
						if(!/[\u4e00-\u9fa5]{1,9}/.test(val)) {
							return false;
						}
					}else {
						return false;
					}
					break;
				case "userAge":
					if(Number.isNaN(val) || !(Number(val) > 0 && Number(val) < 120)) {
						return false;
					}
					break;
				case "userSex":
					if(!(val === "男" || val === "女")) {
						return false;
					}	
					break;
				case "userPhone":
					if(val.length === 11) {
						if(!/^1[34578][0-9]\d{8}/.test(val)) {
							return false;
						}
					}else {
							return false;
					}
					break;
				case "userLevel":
					if(Number.isNaN(val) || !(Number(val) > 0 && Number(val) < 4)) {
						return false;
					}
					break;
				case "userEmail":  
					if(val.search(/^[0-9a-z]+@[0-9a-z]+\.(?:com.cn|(?:com|cn))$/) === -1) {
						return false;
					}
					break;
				case "userPwd":
					if(!(val.length === 6 && /^[a-zA-Z]\w{5}/.test(val))) {
						return false;
					}
					break;
				default:
					
					break;
				}
			}
			
			return true;
		},
		judgeInfo(val) {
			if(val === "yes") {
				span.innerHTML = "该用户已存在";
				span.style.color = 'red';
				return false;
			}else if(val === "no") {
				span.innerHTML = "可以使用";
				span.style.color = 'green';
				phone_status = true;
			}else {
				span.innerHTML = val;
				span.style.color = 'red';
				return false;
			}
			return true;
		}
			
	};
	
	
</script>	
</body>
</html>