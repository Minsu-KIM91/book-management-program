package com.norazo.dto;

public class MemberDTO { //회원 데이터 트랜스퍼 오브젝트 -> 테이블의 데이터를 저장하는 객체
	private String id;
	private String name;
	private String birthday;
	private String address;
	private String age;
	private String phone;
	private String email;
	
	
	public MemberDTO(){
		
	}
	public MemberDTO(String id,String name,String birthday,String address,String age,String phone,String email){
		this.id=id;
		this.name=name;
		this.birthday=birthday;
		this.address=address;
		this.age=age;
		this.phone=phone;
		this.email=email;
	}
	
	//getter, setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		if(id==null)
			id="";
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name==null)
			name="";
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		if(birthday==null)
			birthday ="";
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		if(address==null)
			address="";
		this.address = address;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String mAge) {
		this.age = mAge;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		if(phone==null)
			phone="";
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		if(email==null)
			email="";
		this.email = email;
	}
	
}
