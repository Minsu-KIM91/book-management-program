
package com.norazo.dto;

public class borrowDTO { //대여 데이터 트랜스퍼 오브젝트
	private String id;
	private int BOOK_CODE;
	private String borrow_DATE; //대여일 -> 직접 입력으로 받아
	private String return_DATE; //반납일
	
	
	public borrowDTO(){
		//이거 생략하면 BookRental에 있는 192라인 실행못함. -> 오버라이딩이었나? 뭐시기였는데..이렇게 선언해주고
		//아래처럼 매개변수 늘려서 사용하는 것. 
	}
	public borrowDTO(String id,int BOOK_CODE,String borrow_DATE,String return_DATE){
		if(id==null) id=""; //id 없으면 null
		if(BOOK_CODE==0) BOOK_CODE=0; 
		if(borrow_DATE==null) borrow_DATE="";
		if(return_DATE==null) return_DATE="";
	}
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public int getBOOK_CODE() {
		return BOOK_CODE;
	}
	public void setBOOK_CODE(int bOOK_CODE) { //bOOK_CODE로 쓰는 이유는 받는 값이랑 대입하는 변수이름이 같으면 안되기 때문임
		BOOK_CODE = bOOK_CODE;
	}
	public String getBorrow_DATE() {
		return borrow_DATE;
	}
	public void setBorrow_DATE(String borrow_DATE) {
		this.borrow_DATE = borrow_DATE;
	}
	public String getReturn_DATE() {
		return return_DATE;
	}
	public void setReturn_DATE(String return_DATE) {
		this.return_DATE = return_DATE;
	}
	
}
