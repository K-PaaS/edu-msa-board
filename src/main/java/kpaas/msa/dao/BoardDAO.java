package kpaas.msa.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import kpaas.msa.common.CommonDAO;

@Repository("boardDAO")
public class BoardDAO extends CommonDAO {
	
	public Map<String, Object> getBoardCount(Map<String, Object> paramMap) throws Exception {
		return getSqlSession().selectOne("kpaas.msa.service.impl.BoardMapper.getBoardCount", paramMap);
	}

	public List<Object> getBoardList(Map<String, Object> paramMap) throws Exception {
		return getSqlSession().selectList("kpaas.msa.service.impl.BoardMapper.getBoardList", paramMap);
	}

	public List<Object> getBoard(Map<String, Object> paramMap) throws Exception {
		return getSqlSession().selectList("kpaas.msa.service.impl.BoardMapper.getBoard", paramMap);
	}

	public int postBoard(Map<String, Object> paramMap) throws Exception {

		return getSqlSession().insert("kpaas.msa.service.impl.BoardMapper.postBoard", paramMap);
	}

	public int putBoard(Map<String, Object> paramMap) throws Exception {

		return getSqlSession().update("kpaas.msa.service.impl.BoardMapper.putBoard", paramMap);
	}

	public int deleteBoard(Map<String, Object> paramMap) throws Exception {

		return getSqlSession().delete("kpaas.msa.service.impl.BoardMapper.deleteBoard", paramMap);
	}

}
