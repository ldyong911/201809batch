package kr.or.ddit.batch.model;

import java.util.Date;

import lombok.Data;

@Data
public class BatchVo {
	private int bid; //배치 로그번호
	private String bcd; //배치 코드(01: 일 실적 생성 배치, 02: 월 실적 통계 배치...)
	private String st; //배치 상태(01: 실행, 02: 완료, 99: 에러)
	private Date st_dt; //배치 시작일시
	private Date ed_dt; //배치 종료일시
	
	public BatchVo(){
		
	}
	
	public BatchVo(int bid, String bcd, String st){
		this.bid = bid;
		this.bcd = bcd;
		this.st = st;
	}
}