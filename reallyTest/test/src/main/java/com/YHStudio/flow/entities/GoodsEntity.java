package com.YHStudio.flow.entities;
/** 
* @author : 刘杰
* @date 创建时间：2018年9月23日 下午3:17:33 
* @version 1.0 
* @ClassName: GoodsEntity
* Goods 实体类
*/

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

@Component(value="goods")
public class GoodsEntity {
	private String cus_phone_id;
	private String goodsName;
	private java.sql.Timestamp buyTime;
	private Float price;

	// 创建无参构造器
	public GoodsEntity() {
		super();
	}

	// 有参构造器
	public GoodsEntity(String cus_phone_id, String goodsName, Timestamp buyTime, Float price) {
		super();
		this.cus_phone_id = cus_phone_id;
		this.goodsName = goodsName;
		this.buyTime = buyTime;
		this.price = price;
	}

	// setter与getter
	public String getCus_phone_id() {
		return cus_phone_id;
	}

	public void setCus_phone_id(String cus_phone_id) {
		this.cus_phone_id = cus_phone_id;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public java.sql.Timestamp getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(java.sql.Timestamp buyTime) {
		this.buyTime = buyTime;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "GoodsEntity [cus_phone_id=" + cus_phone_id + ", goodsName=" + goodsName + ", buyTime=" + buyTime
				+ ", price=" + price + "]";
	}
}
