package com.iu.qna;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.util.ListData;
import com.iu.util.PageMaker;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount = qnaDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return qnaDAO.selectList(listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return qnaDAO.selectOne(num);
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		return qnaDAO.insert(boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return qnaDAO.delete(num);
	}
	
	public int replyInsert(BoardDTO boardDTO) throws Exception{
		 qnaDAO.replyUpdate(boardDTO);
		 return qnaDAO.replyInsert(boardDTO);
	}
	
	
	

}
