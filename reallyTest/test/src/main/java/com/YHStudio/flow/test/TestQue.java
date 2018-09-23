package com.YHStudio.flow.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Test;

/** 
* @author : 刘杰
* @date 创建时间：2018年9月21日 下午5:26:36 
* @version 1.0 
* @ClassName: TestChar
*/
public class TestQue {

	@Test
	public void testChar() {
		char a = '男';
		System.out.println(a == '男');
		
		String str = "男";
		System.out.println(str.charAt(0));
		
	}
	
	@Test
	public void testIntegerChange() {
		String str = "2s";
		System.out.println(Integer.parseInt(str));
	}
	
	@Test
	public void testObject() {
		arg("2");
	}
	
	public void arg(Object obj) {
		System.out.println(obj.toString());
	}
	
	@Test
	public void testMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map1 = null;
		System.out.println(map.isEmpty());
		System.out.println(map);
		System.out.println(map1);
	}
	
	@Test
	public void testRandomTestCode() {
		
		// 定义随机字符串
		String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789好学习天向上地玄黄宇宙洪荒纵有千古横有八五福临门财源广进";
		List<String> randomSource = Arrays.asList(str.split(""));
		String testCode = "";
	 	Random ran = new Random();
		for(int i = 0;i < 4;i++) {
			testCode += randomSource.get(ran.nextInt(randomSource.size()));
		}
		System.out.println(testCode);
		
		
	}
	
	@Test
	public void testSQLTime() {
		
		java.sql.Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
		System.out.println(ts);
	}
	
	@Test
	public void testReplace() {
		String str = "a+b+c";	
		System.out.println(str.replace("+", "."));
	}
}
