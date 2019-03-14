package kr.or.ddit.batch.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTask {
	
	private Logger logger = LoggerFactory.getLogger(SimpleTask.class);
	
	public void simpleTask(){
		logger.debug("simpleTask");
	}
}