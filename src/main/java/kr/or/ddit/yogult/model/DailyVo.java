package kr.or.ddit.yogult.model;

import lombok.Data;

@Data
public class DailyVo {
	private int cid; //고객번호
	private int pid; //제품번호
	private String dt; //실적일자
	private int cnt; //제품수량
}