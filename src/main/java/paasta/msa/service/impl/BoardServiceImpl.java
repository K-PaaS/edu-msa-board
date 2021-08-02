package paasta.msa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import paasta.msa.common.RestClient;
import paasta.msa.dao.BoardDAO;
import paasta.msa.service.BoardService;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Resource(name = "boardDAO")
	private BoardDAO boardDAO;

	@Resource(name = "apiProperties")
	private Properties apiProperties;

	public Map<String, Object> getBoardCount(Map<String, Object> paramMap) throws Exception {
		return boardDAO.getBoardCount(paramMap);
	}

	public List<Object> getBoardList(Map<String, Object> paramMap) throws Exception {
		return boardDAO.getBoardList(paramMap);
	}

	public List<Object> getBoard(Map<String, Object> paramMap) throws Exception {
		return boardDAO.getBoard(paramMap);
	}

	public int postBoard(Map<String, Object> paramMap) throws Exception {
		return boardDAO.postBoard(paramMap);
	}

	public int putBoard(Map<String, Object> paramMap) throws Exception {
		return boardDAO.putBoard(paramMap);
	}

	public int deleteBoard(Map<String, Object> paramMap) throws Exception {
		return boardDAO.deleteBoard(paramMap);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> getCommentList(Map<String, Object> paramMap) throws Exception {

		Map<String, String> headerMap = new HashMap<String, String>();
		Map<String, String> requestParamMap = new HashMap<String, String>();
		headerMap.put("apiKey", "Cloud Native Application with PaaS-TA");
		requestParamMap.put("boardSeq", paramMap.get("boardSeq").toString());

		String jsonStr = RestClient.get(apiProperties.getProperty("ApiEndpoint") + "/comments", headerMap, requestParamMap);
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> jsonMap = mapper.readValue(jsonStr, Map.class);

		
		return (Map<String, Object>)jsonMap.get("resultData");
	}

}
