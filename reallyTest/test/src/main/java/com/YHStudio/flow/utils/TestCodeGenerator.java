package com.YHStudio.flow.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Component;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月22日 下午3:16:17 
* @version 1.0 
* @ClassName: TestCodeGenerator
*/
@Component(value="codeGenerator")
public class TestCodeGenerator {

	public String getTestCode(Integer digit) {
		// 定义验证码生成数据源
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789好学习天向上地玄黄宇宙洪荒纵有千古横有八五福临门财源广进";
		// 转换数据源形式
		List<String> randomSource = Arrays.asList(str.split(""));
		// 定义验证码
		String testCode = "";
	 	// 获取随机实例
		Random ran = new Random();
		for(int i = 0;i < digit;i++) {
			// 拼接随机验证码
			testCode += randomSource.get(ran.nextInt(randomSource.size()));
		}
		return testCode;
	}
}
