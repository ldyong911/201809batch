package kr.or.ddit.yogult.batch;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.yogult.model.CycleVo;
import kr.or.ddit.yogult.model.DailyVo;

public class DateTest {
	
	private Logger logger = LoggerFactory.getLogger(DateTest.class);

	@Test
	public void testDate() throws ParseException {
		/***Given***/
		String ym = "201903";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		/***When***/
		Date stDate = sdf.parse(ym + "01"); //시작일자 더해주기
		Calendar cal = Calendar.getInstance();
		cal.setTime(stDate);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //마지막일자 구하기
		Date edDate = sdf.parse(ym + lastDay); //마지막 일자 더해주기
		
		/***Then***/
		assertEquals("20190301", sdf.format(stDate));
		assertEquals("20190331", sdf.format(edDate));
	}
	
	@Test
	public void testDtWhile() throws ParseException{
		/***Given***/
		String ym = "201903";
		CycleVo cycleVo = new CycleVo();
		cycleVo.setCid(1);
		cycleVo.setPid(100);
		cycleVo.setDay(2);
		cycleVo.setCnt(2);
		
		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date stDate = sdf.parse(ym + "01"); //시작일 더해주기
		Calendar cal = Calendar.getInstance();
		cal.setTime(stDate); //날짜 세팅
		
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH); //마지막일
		Date edDate = sdf.parse(ym + lastDay); //마지막 날짜 더해주기
		
		List<DailyVo> dailyList = new ArrayList<DailyVo>();
		//시작일자, 종료일자
		while(cal.getTimeInMillis() <= edDate.getTime()){
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			
			//애음주기 요일이랑 cal 날짜요일이 같으면 일실적으로 생성할 대상일자
			if(dayOfWeek == cycleVo.getDay()){
				DailyVo dailyVo = new DailyVo();
				dailyVo.setCid(cycleVo.getCid());
				dailyVo.setPid(cycleVo.getPid());
				dailyVo.setDt(sdf.format(cal.getTime()));
				dailyVo.setCnt(cycleVo.getCnt());
				dailyList.add(dailyVo);
			}
			
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		logger.debug("dailyList : {}", dailyList);
		
		/***Then***/
	}

}