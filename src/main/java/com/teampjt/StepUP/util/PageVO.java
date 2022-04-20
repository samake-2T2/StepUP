package com.teampjt.StepUP.util;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Data;

@Data
public class PageVO {
	
	//페이지네이션을 그리는 클래스
	private int start;
	private int end;
	private boolean prev;
	private boolean next;
	
	private int page;
	private int amount;
	private int total;
	
	private int realEnd;
	
	private Criteria cri;
	
	//타임리프에서는 향상된 포문을 제공하기 때문에 페이지번호 목록을 리스트에 저장
	private List<Integer> pageList;

	//생성자 - pageVO는 생성될 때, Criteria와 전체 게시글 수를 받는다.
	public PageVO(Criteria cri, int total) {
		this.page = cri.getPage();
		this.amount = cri.getAmount();
		this.total = total;
		this.cri = cri;
		
		//끝 페이지 계산하기
		this.end = (int)Math.ceil(this.page / 10.0) * 10;
		
		//첫 페이지 계산하기
		this.start = this.end - 10 + 1;
		
		//실제 끝 페이지 번호 계산
		this.realEnd = (int)Math.ceil(this.total / (double)this.amount);
		
		//실제 끝 번호를 다시 결정
		if(this.end > this.realEnd) {
			this.end = this.realEnd;
		}
		
		//이전버튼 활성화 여부
		this.prev = this.start > 1;
		
		//다음버튼 활성화 여부
		this.next = this.realEnd > this.end;
		
		//페이지리스트 처리
		this.pageList = IntStream.rangeClosed(this.start, this.end)
				                 .boxed()
				                 .collect( Collectors.toList() );
	}
	
	

}
