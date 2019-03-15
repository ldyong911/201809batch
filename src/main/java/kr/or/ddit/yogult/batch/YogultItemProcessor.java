package kr.or.ddit.yogult.batch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.yogult.model.CycleVo;
import kr.or.ddit.yogult.model.DailyVo;
											//한건의 애음주기를 읽고 여러건의 일실적을 반환 
public class YogultItemProcessor implements ItemProcessor<CycleVo, List<DailyVo>>{
	
	private Logger logger = LoggerFactory.getLogger(YogultItemProcessor.class);
	
	//spel을 통해 jobParameter를 주입받기 위해서는 해당 bean의 스코프가 "step"
	@Value("#{jobParameters[ym]}")
	private String ym;
	
	@Override
	public List<DailyVo> process(CycleVo cycleVo) throws Exception {
		logger.debug("processor ym : {}", ym);
		//String ym = "201903";
		//해당년월의 시작일자 : 1일
		//해당년월의 종료일자 : 28일, 29일, 30일, 31일
		
		// 1번 고객이 100제품을 2(월요일)요일날 3개를 먹는다
		// 1 100 2 3
		
		// 1 100 20190304 3
		// 1 100 20190311 3
		// 1 100 20190318 3
		// 1 100 20190325 3
		
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
		
		return dailyList;
	}
}