package kr.or.ddit.yogult.model;

import lombok.Data;

//lombok 라이브러리에서 제공하는 어노테이션(getter, setter 자동 생성)
@Data
public class CycleVo {
	private int cid; //고객번호
	private int pid; //제품번호
	private int day; //요일(일:1, 월:2.....토:7)
	private int cnt; //제품수량
	
	public CycleVo(){
		this.getCid();
	}
}