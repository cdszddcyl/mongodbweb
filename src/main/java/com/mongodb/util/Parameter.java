package com.mongodb.util;
/**
 * 
 * @author StoneCai
 * 2016-07-27 13:16:16
 * 添加
 * 参数容器
 */
public class Parameter {
	
	
	private String colum;
	private Object value;
	private String andOrLike;
	public String getColum() {
		return colum;
	}
	public void setColum(String colum) {
		this.colum = colum;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getAndOrLike() {
		return andOrLike;
	}
	public void setAndOrLike(String andOrLike) {
		this.andOrLike = andOrLike;
	}
	
	

}
