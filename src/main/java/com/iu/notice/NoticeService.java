package com.iu.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.util.ListData;
import com.iu.util.PageMaker;
@Service
public class NoticeService implements BoardService {
	@Inject
	private NoticeDAO noticeDAO;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount = noticeDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return noticeDAO.selectList(listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return noticeDAO.selectOne(num);
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		return noticeDAO.insert(boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return noticeDAO.update(boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return noticeDAO.delete(num);
	}

}
