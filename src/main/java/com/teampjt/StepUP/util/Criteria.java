package com.teampjt.StepUP.util;

import lombok.Data;

@Data
public class Criteria {

	//sql문에 전달될 값을 가지고 다니는 클래스
	private int page;
	private int amount;
	
	//검색 키워드 추가
	private String searchKeyword;
	
	//아무 값도 안 넘어오면 1페이지, 데이터 양 10개
	public Criteria() {
		this(1, 10);
	}
	
	//값이 넘어오면 그 값으로 초기화
	public Criteria(int page, int amount) {
		super();
		this.page = page;
		this.amount = amount;
	}
	
	
}
