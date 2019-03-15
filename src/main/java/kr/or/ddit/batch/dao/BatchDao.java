package kr.or.ddit.batch.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.batch.model.BatchVo;

@Repository
public class BatchDao implements IBatchDao{
	
	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public int getBatchBid() {
		return sqlSessionTemplate.selectOne("batch.getBatchBid");
	}
	
	@Override
	public int createBatch(BatchVo batchVo) {
		return sqlSessionTemplate.insert("batch.createBatch", batchVo);
	}

	@Override
	public int updateBatch(BatchVo batchVo) {
		return sqlSessionTemplate.update("batch.updateBatch", batchVo);
	}
}