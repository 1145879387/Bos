package com.bos.domain;

import net.sf.json.JSONObject;

import java.util.HashSet;
import java.util.Set;

/**
 * 区域
 */

public class Region implements java.io.Serializable {
	public static void main(String[] args) {
//		在生成json的同时，底层会调用这个类的get方法生成json,而getName方法生成的字段正好是name属性
		Region region = new Region("001", "河北省", "石家庄市", "桥西区", null, null, null, null);
		String s = JSONObject.fromObject(region).toString();
		System.out.println(s);
	}
	// Fields

	private String id;
	private String province;
	private String city;
	private String district;
	private String postcode;
	private String shortcode;
	private String citycode;
	//subareas代表分区，一个区域里有多个分区
	private Set subareas = new HashSet(0);

	// Constructors

	/**
	 * default constructor
	 */
	public Region() {
	}

	public String getName() {
//		注意，方法名称不可以随意写，N必须大写，这样转换成Json的时候，才可以转换成name字段，不然会有问题
		return province + city + district;
	}

	/**
	 * minimal constructor
	 */
	public Region(String id) {
		this.id = id;
	}

	/**
	 * full constructor
	 */
	public Region(String id, String province, String city, String district,
	              String postcode, String shortcode, String citycode, Set subareas) {
		this.id = id;
		this.province = province;
		this.city = city;
		this.district = district;
		this.postcode = postcode;
		this.shortcode = shortcode;
		this.citycode = citycode;
		this.subareas = subareas;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return this.province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getShortcode() {
		return this.shortcode;
	}

	public void setShortcode(String shortcode) {
		this.shortcode = shortcode;
	}

	public String getCitycode() {
		return this.citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public Set getSubareas() {
		return this.subareas;
	}

	public void setSubareas(Set subareas) {
		this.subareas = subareas;
	}

}