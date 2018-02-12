package com.bos.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 定区
 */

public class Decidedzone implements java.io.Serializable {

	// Fields

	private String id;
	//	取派员，定区做为外键引用取派员一方的主键，表示一个定区可以有多个取派员
//	注意，转换成json容易造成死循环，需要在Decidedzone表里设置它的lazy属性
//	注意，staff反过来会关联定区集合，为了避免死循环，则需要排除定区集合！
//	lazy属性一定会出现在many-to-one标签里面，不能出现在set标签里面，不然会严重影响性能！
	private Staff staff;
	private String name;
	//	subareas代表分区，一个定区里有多个分区
	private Set subareas = new HashSet(0);

	// Constructors

	/**
	 * default constructor
	 */
	public Decidedzone() {
	}

	/**
	 * minimal constructor
	 */
	public Decidedzone(String id) {
		this.id = id;
	}

	/**
	 * full constructor
	 */
	public Decidedzone(String id, Staff staff, String name, Set subareas) {
		this.id = id;
		this.staff = staff;
		this.name = name;
		this.subareas = subareas;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set getSubareas() {
		return this.subareas;
	}

	public void setSubareas(Set subareas) {
		this.subareas = subareas;
	}

}